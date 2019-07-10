package home.web.ktor

import freemarker.cache.ClassTemplateLoader
import home.web.entity.ProcessEntity
import home.web.entity.ServiceProcessEntity
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.joda.time.DateTime

fun main() {
    ServerKtor().start(14000)
}

class ServerKtor {
    fun start(port: Int) {
        embeddedServer(Netty, watchPaths = listOf("solutions/exercise4"), port = port, module = Application::module).start(wait = true)
    }


}

fun Application.module() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    routing {
        route("/") {
            get {
                call.respond(FreeMarkerContent("index.ftl", mapOf("processes" to ServiceProcessEntity.getList(), "idEdit" to -1)))
            }
        }
        route("/new") {
            post {
                val postParameters: Parameters = call.receiveParameters()
                ServiceProcessEntity.add(
                        ProcessEntity(
                                0,
                                postParameters["name"] ?: "",
                                DateTime.now(),
                                postParameters["limit"]?.toInt() ?: 0,
                                0)
                )
                call.respondRedirect("/")
            }
        }
        route("/edit") {
            get {
                val id = call.request.queryParameters["id"]
                if (id != null) {
                    call.respond(FreeMarkerContent("index.ftl", mapOf("processes" to ServiceProcessEntity.getList(), "idEdit" to id.toInt())))
                }
            }
            post {
                val postParameters: Parameters = call.receiveParameters()
                val id = postParameters["id"]
                if (id != null) {
                    val entity = ServiceProcessEntity.getProcById(id.toInt())
                    entity.name = postParameters["name"] ?: ""
                    entity.timeLimit = postParameters["timeLimit"]?.toInt() ?: 0
                    ServiceProcessEntity.update(entity)
                }
                call.respondRedirect("/")
            }
        }
        route("/delete") {
            get {
                val id = call.request.queryParameters["id"]
                if (id != null) {
                    ServiceProcessEntity.delete(id.toInt())
                    call.respondRedirect("/")
//                    call.respond(FreeMarkerContent("index.ftl", mapOf("employees" to dao.getAllEmployees())))
                }
            }

        }
    }
}