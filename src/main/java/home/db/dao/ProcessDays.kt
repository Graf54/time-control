package home.db.dao

import home.db.entity.MyProcess
import home.db.entity.ProcessDay
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime
import home.web.entity.ProcessEntity

object ProcessDays : IntIdTable() {
    val processId = reference("process_id", Processes, onDelete = ReferenceOption.CASCADE)
    val day = date("day")
    val minutes = integer("minutes")

    init {
        index(true, processId, day)
    }

    fun create(procId: Int, minute: Int): ProcessDay {
        return transaction {
            val byId = Processes.getById(procId)
            ProcessDay.new {
                processId = byId.id
                day = DateTime.now()
                minutes = minute
            }
        }
    }

    fun getAll(): List<ProcessDay> {
        return transaction {
            ProcessDay.all().toList()
        }
    }

    fun getAllByProcessId(idProc: Int, limit: Int, offset: Int): List<ProcessDay> {
        return transaction { ProcessDay.find { processId eq idProc }.limit(limit, offset).toList() }
    }

    fun exist(processEntity: ProcessEntity): Boolean {
        return transaction {
            ProcessDays.select { (processId eq processEntity.id) and (day eq DateTime.now()) }.count() > 0
        }
    }

    fun updateDay(processEntity: ProcessEntity) {
        transaction {
            if (exist(processEntity)) {
                ProcessDays.update({ (processId eq processEntity.id) and (day eq DateTime.now()) }) {
                    it[minutes] = processEntity.timeUse
                }

            } else {
                ProcessDay.new {
                    processId = MyProcess[processEntity.id].id
                    day = DateTime.now()
                    minutes = processEntity.timeUse
                }
            }
        }
    }

}