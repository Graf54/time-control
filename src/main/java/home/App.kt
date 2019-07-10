package home

import home.core.impl.Core
import home.db.dao.DAOConfig
import home.web.ktor.ServerKtor

fun main() {
    DAOConfig.init()
    val core = Core(1)
    core.start()
    ServerKtor().start(14000)
}

