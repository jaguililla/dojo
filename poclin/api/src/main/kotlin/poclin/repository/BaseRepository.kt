package swatlin.repository

import com.mongodb.async.SingleResultCallback
import com.mongodb.async.client.MongoClients.getDefaultCodecRegistry
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Indexes.ascending
import java.util.Objects.requireNonNull
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.slf4j.LoggerFactory.getLogger

import java.util.ArrayList

import com.mongodb.async.client.FindIterable
import com.mongodb.async.client.MongoCollection
import com.mongodb.async.client.MongoDatabase
import com.mongodb.client.model.CreateCollectionOptions
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import io.vertx.core.CompositeFuture
import io.vertx.core.Future
import org.bson.Document
import org.bson.conversions.Bson
import org.bson.types.ObjectId
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

/**
 * TODO .
 */
class BaseRepository<T : Any, in K : Any> private constructor(
    name: String?,
    type: KClass<T>,
    private val key: KProperty1<T, K>,
    mongoDatabase: MongoDatabase,
    private val generateKey: Boolean = false) {

    val name: String = name ?: type.java.simpleName

    companion object {
        private val LOGGER = getLogger(BaseRepository::class.java)
    }

    private val collection: MongoCollection<Document>
    private val typedCollection: MongoCollection<T>

    init {

        val codecProvider = JacksonCodecProvider(type, key)
        val database = mongoDatabase.withCodecRegistry(
                fromRegistries(getDefaultCodecRegistry(), fromProviders(codecProvider))
        )

        if (generateKey)
            database.createCollection(
                    this.name,
                    CreateCollectionOptions().autoIndex(false)
            ) { _, _ -> }

        collection = database.getCollection(this.name)
        typedCollection = database.getCollection(this.name, type.java)
        typedCollection.createIndex(
                ascending(key.name),
                IndexOptions().unique(true).background(true)
        ) { _, _ -> LOGGER.info("Index created for: {} with field: {}", this.name, this.key.name) }
    }

    fun deleteOne(id: K): Future<DeleteResult> {
        requireNonNull(id)
        val future = Future.future<DeleteResult>()
        val filter = eq(key.name, convertKey(id))
        typedCollection.deleteOne(filter, singleResultCallback(future))
        return future
    }

    fun <Z> singleResultCallback(future: Future<Z>): SingleResultCallback<Z> =
        SingleResultCallback { result, error ->
            if (error == null) future.complete (result)
            else future.fail (error)
        }

    fun deleteByPattern(pattern: Bson): Future<DeleteResult> {
        requireNonNull(pattern)
        val future = Future.future<DeleteResult>()
        typedCollection.deleteMany(pattern, singleResultCallback(future))
        return future
    }

    fun replaceOne(`object`: T): Future<UpdateResult> {
        requireNonNull(`object`)
        val future = Future.future<UpdateResult>()
        val filter = eq(key.name, getKey(`object`))
        typedCollection.replaceOne(filter, `object`, singleResultCallback(future))
        return future
    }

    fun updateOne(key: K, update: Bson): Future<UpdateResult> {
        requireNonNull(key)
        requireNonNull(update)
        val future = Future.future<UpdateResult>()
        val filter = eq(this.key.name, convertKey(key))
        val options = UpdateOptions()
        typedCollection.updateOne(filter, update, options, singleResultCallback(future))
        return future
    }

    protected fun updateMany(filter: Bson, update: Bson): Future<UpdateResult> {
        requireNonNull(filter)
        requireNonNull(update)
        val future = Future.future<UpdateResult>()
        val options = UpdateOptions()
        typedCollection.updateMany(filter, update, options, singleResultCallback(future))
        return future
    }

    fun replaceList(objects: List<T>): CompositeFuture {
        requireNonNull(objects)
        return CompositeFuture.join(
                objects.map { `object` ->
                    val future = Future.future<UpdateResult>()
                    val filter = eq(key.name, getKey(`object`))
                    typedCollection.replaceOne(filter, `object`, singleResultCallback(future))
                    future
                }
        )
    }

    fun insertOne(`object`: T): Future<Void> {
        requireNonNull(`object`)
        val future = Future.future<Void>()
        typedCollection.insertOne(`object`, singleResultCallback(future))
        return future
    }

    fun insertList(objects: List<T>): Future<Void> {
        requireNonNull(objects)
        val future = Future.future<Void>()
        if (!objects.isEmpty())
            typedCollection.insertMany(objects, singleResultCallback(future))
        return future
    }

    fun findOne(key: K, fields: Bson): Future<Map<String, *>> {
        val future = Future.future<Map<String, *>>()
        collection
                .find(eq(this.key.name, key))
                .projection(fields)
                .first { result, error ->
                    if (error == null)
                        future.complete(result)
                    else
                        future.fail(error)
                }

        return future
    }

    fun findOne(key: K): Future<T> {
        requireNonNull(key)
        val finalKey = convertKey(key)
        return findOne(eq(this.key.name, finalKey))
    }

    fun findOne(filter: Bson): Future<T> {
        requireNonNull(filter)
        val future = Future.future<T>()
        typedCollection.find(filter).first(singleResultCallback(future))
        return future
    }

    private fun findIterable(
            pattern: Bson?, limit: Int?, skip: Int?, projection: Bson?, sort: Bson?): FindIterable<Document> {

        val iterable = if (pattern == null)
            collection.find()
        else
            collection.find(pattern)

        if (limit != null)
            iterable.limit(limit)
        if (skip != null)
            iterable.skip(skip)

        if (projection != null)
            iterable.projection(projection)

        if (sort != null)
            iterable.sort(sort)

        return iterable
    }

    private fun findIterable(pattern: Bson?, limit: Int?, skip: Int?, sort: Bson?): FindIterable<T> {

        val iterable = if (pattern == null)
            typedCollection.find()
        else
            typedCollection.find(pattern)

        if (limit != null)
            iterable.limit(limit)
        if (skip != null)
            iterable.skip(skip)

        if (sort != null)
            iterable.sort(sort)

        return iterable
    }

//    fun findByPattern(pattern: Bson, limit: Int?, skip: Int?, sort: Bson): Future<List<T>> {
//        val future = Future.future<List<T>>()
//        val callback = singleResultCallback(future)
//        findIterable(pattern, limit, skip, sort).into(List<T>(), callback)
//        return future
//    }

    internal fun findByPattern(
            pattern: Bson, projection: Bson, limit: Int?, skip: Int?, sort: Bson): Future<List<Map<String, *>>> {

        val future = Future.future<List<Map<String, *>>>()

        findIterable(pattern, limit, skip, projection, sort)
                .into(ArrayList<Document>()) { result, exception ->
                    if (exception != null) {
                        future.fail(exception)
                    } else {
                        future.complete(result)
                    }
                }

        return future
    }

    fun count(pattern: Bson): Future<Long> {
        val future = Future.future<Long>()
        typedCollection.count(pattern, singleResultCallback(future))
        return future
    }

    private fun convertKey(`object`: K): Any {
        return if (generateKey) ObjectId(`object`.toString()) else `object`
    }

    private fun getKey(`object`: T): Any {
        val key = key.get(`object`)
        return if (generateKey) ObjectId(key.toString()) else key
    }
}
