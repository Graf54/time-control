package db

import home.db.dao.ProcessDays
import home.db.dao.Processes
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Before
import org.junit.Test

class ProcessesDaoTest {
    @Before
    fun initDB() {
        Database.connect("jdbc:h2:mem:test_mem;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")
        transaction {
            SchemaUtils.create(Processes, ProcessDays)
        }
    }

    @Test
    fun testAll() {
        val create = Processes.create("test.exe", 10)
        assert(Processes.nameExist("test.exe"))
        assert(!Processes.nameExist("yeye"))
        transaction { create.limitMinuteOfDay = 20 }
        val myProcess = Processes.getById(create.id.value)
        assert(myProcess.name == create.name && myProcess.limitMinuteOfDay == create.limitMinuteOfDay)
        assert(myProcess.limitMinuteOfDay == 20)
    }

}