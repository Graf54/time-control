package home.web.launcher


import mu.KotlinLogging
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

/**
 * Starts jetty-server on the specified port
 */
object Launcher {
    private val logger = KotlinLogging.logger {}
    fun start(port: Int) {
        Thread(getRun(port)).start()
    }

    private fun getRun(port: Int): Runnable {
        return Runnable {
            logger.info("Server port:{$port}")
            val server = Server(port)

            val domain = Launcher::class.java.protectionDomain
            val location = domain.codeSource.location

            val webapp = WebAppContext()
            webapp.contextPath = "/"
            webapp.war = location.toExternalForm()

            server.handler = webapp
            server.start()
            server.join()
        }
    }
}