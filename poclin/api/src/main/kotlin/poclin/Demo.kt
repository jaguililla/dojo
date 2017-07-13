package swatlin

import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.auth.jwt.JWTOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.JWTAuthHandler
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.kotlin.core.DeploymentOptions
import swatlin.Alias.Companion.CODE_REGEX

internal class SwatlinDemo : RouterVerticle() {
    val eventBus: Router = with(Router.router(vertx)) {
        val jwtSettings = config().getJsonObject ("jwtSettings", JsonObject ())
        val jwt = JWTAuth.create(vertx, jwtSettings)

        route("/eventbus/*").handler (JWTAuthHandler.create(jwt))
        route("/eventbus/*").handler(createEventbusHandler(vertx, "messages\\.$CODE_REGEX"))

        route("/token/:user") {
            val token = jwt.generateToken(JsonObject(), JWTOptions())
            it.response().headers().add("Authorization", "Bearer $token")
            it.response().end(token)
        }

        route().handler(StaticHandler.create().setCachingEnabled(false))
        this
    }

    val templates: Router = with(Router.router(vertx)) {
        val jwtSettings = config().getJsonObject ("jwtSettings", JsonObject ())
        val jwt = JWTAuth.create(vertx, jwtSettings)

        route("/eventbus/*").handler (JWTAuthHandler.create(jwt))
        route("/eventbus/*").handler(createEventbusHandler(vertx, "messages\\.$CODE_REGEX"))

        route("/token/:user") {
            val token = jwt.generateToken(JsonObject(), JWTOptions())
            it.response().headers().add("Authorization", "Bearer $token")
            it.response().end(token)
        }

        route().handler(StaticHandler.create().setCachingEnabled(false))
        this
    }

    override fun Router.router() {

        mountSubRouter("/", eventBus)
        mountSubRouter("/templates", templates)

        route().handler(StaticHandler.create().setCachingEnabled(false))
    }
}

/**
 * API Main method.
 *
 * TODO Check Vert.x config module: http://vertx.io/docs/vertx-config/kotlin
 * TODO Check deployment of many verticle instances (like with a class name)
 *
 * @param args Command line arguments.
 */
internal fun main(vararg args: String) {
    setupLogback()

    val vertx = Vertx.vertx()
    val config = JsonObject(resource("swatlin.json").readText())
    vertx.deployVerticle(SwatlinDemo (), DeploymentOptions(config = config))
}
