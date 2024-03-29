package home.web.ktor

import freemarker.cache.ClassTemplateLoader
import home.web.entity.ProcessEntity
import home.web.entity.ServiceProcessEntity
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
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
import kotlin.system.exitProcess

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
                try {
                    ServiceProcessEntity.add(
                            ProcessEntity(
                                    0,
                                    postParameters["name"] ?: "",
                                    DateTime.now(),
                                    postParameters["timeLimit"]?.toInt() ?: 0,
                                    0)
                    )
                } catch (e: Exception) {
                    //todo add message
                }
                call.respondRedirect("/")
            }
        }
        route("/edit") {
            get {
                val id = call.request.queryParameters["id"]
                if (id != null) {
                    //todo check if name exist
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
        route("/close") {
            get {
                //todo make normal page
                call.respond("<b>Programm is close</b>")

                log.info("Close program from web")
                exitProcess(0)
            }
        }
        route("/history") {
            get {
                val id = call.request.queryParameters["id"]
                if (id != null) {
                    val procId = id.toInt()
                    val entity = ServiceProcessEntity.getProcById(procId)
                    val history = ServiceProcessEntity.getHistory(procId, 100, 0)
                    val count = ServiceProcessEntity.getCountHistory(procId)
                    //todo add leaf
                    call.respond(FreeMarkerContent("history.ftl", mapOf("name" to entity.name, "history" to history, "count" to count)))

                }
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