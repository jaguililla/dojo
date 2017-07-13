package swatlin

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.net.URL
import kotlin.reflect.KClass

/**
 * Ease the logger definition and usage. Note the logger is fetched in each call.
 *
 * TODO Check performance penalty of fetching the logger instead storing it.
 */
interface Loggable {
    private companion object {
        private const val FLARE_PREFIX = ">>>>>>"
    }

    fun logger(): Logger = getLogger(this::class.java)

    fun trace (message: String) = logger().trace(message)
    fun debug (message: String) = logger().debug(message)
    fun info (message: String) = logger().info(message)
    fun warn (message: String) = logger().warn(message)
    fun err(message: String) = logger().error(message)
    fun warn (message: String, exception: Throwable) = logger().warn(message, exception)
    fun err(message: String, exception: Throwable) = logger().error(message, exception)
    fun flare (message: String = "") = logger().trace("$FLARE_PREFIX $message")
}

/**
 * Ease the logger definition and usage. Note the logger is fetched in each call.
 */
open class CompanionLogger (type: KClass<*>) : Loggable {
    val logger: Logger by lazy { getLogger(type.java) }

    override fun logger(): Logger = logger
}

object Log : Loggable

val systemClassLoader: ClassLoader = ClassLoader.getSystemClassLoader()

fun resource(path: String): URL = systemClassLoader.getResource(path)
