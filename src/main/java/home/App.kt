package home

import home.core.impl.Core
import home.db.dao.DAOConfig
import home.web.launcher.Launcher

fun main() {
    DAOConfig.init()
    Launcher.start(12120)
    val core = Core(1)
    core.start()
}

