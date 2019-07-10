package home.web.ktor

import freemarker.cache.ClassTemplateLoader
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
                call.respond(FreeMarkerContent("index.ftl", mapOf("employees" to DaoEmployees.getAllEmployees())))
            }
        }
        route("/employee") {
            get {
                val action = (call.request.queryParameters["action"] ?: "new")
                when (action) {
                    "new" -> call.respond(FreeMarkerContent("employee.ftl",
                            mapOf("action" to action)))
                    "edit" -> {
                        val id = call.request.queryParameters["id"]
                        if (id != null) {
                            val employee = DaoEmployees.getEmployee(id.toInt())
                            employee.edit = 1
                            call.respondRedirect("/")
                            /*call.respond(FreeMarkerContent("employee.ftl",
                                    mapOf("employee" to dao.getEmployee(id.toInt()),
                                            "action" to action)))*/
                        }
                    }
                }
            }
            post {
                val postParameters: Parameters = call.receiveParameters()
                when (postParameters["action"] ?: "new") {
                    "new" -> DaoEmployees.createEmployee(postParameters["name"] ?: "", postParameters["email"]
                            ?: "", postParameters["city"] ?: "")
                    "edit" -> {
                        val id = postParameters["id"]
                        if (id != null)
                            DaoEmployees.updateEmployee(id.toInt(), postParameters["name"] ?: "", postParameters["email"]
                                    ?: "", postParameters["city"] ?: "")
                    }
                }
                call.respondRedirect("/")
            }
        }
        route("/edit") {
            get {
                val id = call.request.queryParameters["id"]
                if (id != null) {
                    val employee = DaoEmployees.getEmployee(id.toInt())
                    employee.edit = 1
                    call.respondRedirect("/")
                }
            }
            post {
                val postParameters: Parameters = call.receiveParameters()
                val id = postParameters["id"]
                if (id != null) {
                    val employee = DaoEmployees.getEmployee(id.toInt())
                    employee.name = postParameters["name"] ?: ""
                    employee.email = postParameters["email"] ?: ""
                    employee.city = postParameters["city"] ?: ""
                    employee.edit = 0
                }
                call.respondRedirect("/")
            }
        }
        route("/delete") {
            get {
                val id = call.request.queryParameters["id"]
                if (id != null) {
                    DaoEmployees.deleteEmployee(id.toInt())
                    call.respondRedirect("/")
//                    call.respond(FreeMarkerContent("index.ftl", mapOf("employees" to dao.getAllEmployees())))
                }
            }

        }
    }
}