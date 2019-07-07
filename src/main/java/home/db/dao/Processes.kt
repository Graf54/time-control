package home.db.dao

import home.db.entity.MyProcess
import home.web.entity.ProcessEntity
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime

object Processes : IntIdTable() {
    val name = varchar("name", 100).uniqueIndex()
    val added = datetime("added")
    val limitMinuteOfDay = integer("limit_minute_of_day")

    fun create(nameProcess: String, limitMinute: Int): MyProcess {
        return transaction {
            MyProcess.new {
                name = nameProcess
                added = DateTime.now()
                limitMinuteOfDay = limitMinute
            }
        }
    }

    fun delete(id: Int) {
        transaction {
            Processes.deleteWhere { Processes.id eq id }
        }
    }

    fun update(entity: ProcessEntity) {
        transaction {
            Processes.update({ id eq entity.id }) {
                it[name] = entity.name
                it[limitMinuteOfDay] = entity.timeLimit
            }
        }
    }

    fun nameExist(name: String): Boolean {
        return transaction {
            Processes.select { Processes.name eq name }.count() > 0
        }
    }

    fun anotherNameExist(name: String, id: Int): Boolean {
        return transaction {
            Processes.select { (Processes.name eq name) and (Processes.id.neq(EntityID(id, Processes))) }.count() > 0
        }
    }

    fun getById(id: Int): MyProcess {
        return transaction { MyProcess[id] }
    }

}