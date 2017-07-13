package swatlin

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod.*
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.logging.LoggerFactory.LOGGER_DELEGATE_FACTORY_CLASS_NAME
import io.vertx.core.logging.SLF4JLogDelegateFactory
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.CorsHandler
import io.vertx.ext.web.handler.ErrorHandler
import io.vertx.ext.web.handler.sockjs.BridgeOptions
import io.vertx.ext.web.handler.sockjs.SockJSHandler
import io.vertx.kotlin.ext.web.handler.sockjs.PermittedOptions
import org.slf4j.LoggerFactory
import java.lang.System.setProperty

fun createCorsHandler() = CorsHandler.create("*")
    .allowCredentials(true)
    .allowedMethod(GET)
    .allowedMethod(POST)
    .allowedMethod(PUT)
    .allowedMethod(DELETE)
    .allowedMethod(OPTIONS)
    .allowedMethod(CONNECT)
    .allowedHeader("Access-Control-Allow-Method")
    .allowedHeader("Access-Control-Allow-Origin")
    .allowedHeader("authorization")
    .allowedHeader("accept")
    .allowedHeader("content-type")
    .allowedHeader("xsfr")
    .exposedHeader("location")
    .exposedHeader("x-subject-token")
    .exposedHeader("x-error-code")
    .exposedHeader("x-error-message")
    .maxAgeSeconds(3600)

fun createEventbusHandler(vertx: Vertx, vararg addressRegex: String): SockJSHandler {
    val options = BridgeOptions()
    addressRegex.map { PermittedOptions(addressRegex = it) }.forEach {
        options.addInboundPermitted(it)
        options.addOutboundPermitted(it)
    }

    return SockJSHandler.create(vertx).bridge(options) {
        it.socket().webUser().principal()
        Log.info(it.toString())
        it.complete(true)
    }
}

fun setupLogback() {
    setProperty (LOGGER_DELEGATE_FACTORY_CLASS_NAME, SLF4JLogDelegateFactory::class.java.name)
    LoggerFactory.getLogger (LoggerFactory::class.java) // Required for Logback to work in Vertx
}

fun Router.route(path: String, handler: (RoutingContext) -> Unit): Route =
    this.route(path).handler(handler)

abstract class HttpVerticle : AbstractVerticle() {
    private companion object : CompanionLogger(HttpVerticle::class)

    private val httpServer: HttpServer by lazy { vertx.createHttpServer() }

    abstract fun handle(request: HttpServerRequest)

    override fun start(future: Future<Void>) {
        val bindPort = config().getInteger("bindPort", 3210)
        val bindHost = config().getString("bindHost", "127.0.0.1")

        httpServer.requestHandler { handle(it) }.listen(bindPort, bindHost) {
            if (it.succeeded()) {
                future.complete()
                info("HTTP server listening on: $bindHost:$bindPort")
            }
            else {
                val cause = it.cause()
                future.fail(cause)
                err("HTTP server failed!", cause)
            }
        }
    }

    override fun stop(future: Future<Void>) { httpServer.close() }
}

abstract class RouterVerticle : HttpVerticle() {
    private companion object : CompanionLogger(HttpVerticle::class)

    val router: Router by lazy {
        Router.router(vertx).also {
            it.router()
            it.route().failureHandler(ErrorHandler.create())
        }
    }

    abstract fun Router.router()

    override fun handle(request: HttpServerRequest) { router.accept(request) }
}
