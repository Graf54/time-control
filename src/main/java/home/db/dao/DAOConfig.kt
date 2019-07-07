package home.db.dao

import home.Properties
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DAOConfig {
    fun init() {
        Database.connect(Properties.getValue("db.url"), driver = "org.h2.Driver")
        transaction {
            SchemaUtils.create(Processes, ProcessDays)
        }
    }
}