package swatlin.repository

import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY
import com.fasterxml.jackson.core.JsonParser.Feature.*
import com.fasterxml.jackson.core.util.DefaultIndenter.SYSTEM_LINEFEED_INSTANCE
import com.fasterxml.jackson.databind.DeserializationFeature.*
import com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS

import java.io.IOException

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.vertx.core.json.Json
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.Document
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import org.bson.codecs.configuration.CodecProvider
import org.bson.codecs.configuration.CodecRegistry
import org.bson.types.ObjectId
import kotlin.reflect.KProperty1
import kotlin.reflect.KClass

/**
 * Provide codecs that use Jackson Object Mapper for all Java classes.
 */
class JacksonCodecProvider<T : Any, K : Any> internal constructor(
    private val type: KClass<T>,
    private val key: KProperty1<T, K>) :
        CodecProvider {

    companion object {
        val MAPPER: ObjectMapper = Json.mapper
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(FAIL_ON_EMPTY_BEANS, false)
            .configure(ALLOW_UNQUOTED_FIELD_NAMES, true)
            .configure(ALLOW_COMMENTS, true)
            .configure(ALLOW_SINGLE_QUOTES, true)
            .configure(WRAP_EXCEPTIONS, false)
            .configure(FAIL_ON_MISSING_CREATOR_PROPERTIES, false)
            .setSerializationInclusion(NON_EMPTY)
            .registerModule(Jdk8Module())
            .registerModule(JavaTimeModule())
            .registerModule(SimpleModule("ObjectIds", Version.unknownVersion())
                .addSerializer(ObjectId::class.java, ToStringSerializer())
                .addDeserializer(ObjectId::class.java, object : JsonDeserializer<ObjectId>() {
                    @Throws(IOException::class)
                    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ObjectId {
                        return ObjectId(p.readValueAs(String::class.java))
                    }
                })
            )

        val WRITER = MAPPER.writer(
            DefaultPrettyPrinter().withArrayIndenter(SYSTEM_LINEFEED_INSTANCE)
        )
    }

    /** {@inheritDoc}  */
    override fun <TC> get(clazz: Class<TC>, registry: CodecRegistry): Codec<TC> {
        return object : Codec<TC> {
            internal var documentCodec = registry.get(Document::class.java)

            /** {@inheritDoc}  */
            override fun encode(
                    writer: BsonWriter, value: TC, encoderContext: EncoderContext) {

                var map = MAPPER.convertValue(value, Map::class.java) as Map<String, Any>

                val generateKey = false//TODO Pass as parameter
                if (generateKey) {
                    val id = if (map[key.name] == null)
                        ObjectId()
                    else
                        ObjectId(map[key.name].toString())
                    if (clazz == type)
                        map += key.name to id
                }

                documentCodec.encode(writer, Document(map), encoderContext)
            }

            /** {@inheritDoc}  */
            override fun decode(reader: BsonReader, decoderContext: DecoderContext): TC {
                val document = documentCodec.decode(reader, decoderContext)

                val generateKey = false//TODO entity.generateKey

                if (generateKey)
                    document.computeIfPresent(key.name) { _, value ->
                        (value as ObjectId).toHexString()
                    }

                return MAPPER.convertValue(document, clazz)
            }

            /** {@inheritDoc}  */
            override fun getEncoderClass(): Class<TC> {
                return clazz
            }
        }
    }
}
