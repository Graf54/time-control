package home

import home.core.impl.Core
import home.db.dao.DAOConfig

fun main() {
    DAOConfig.init()
    val core = Core(1)
    core.start()
}

