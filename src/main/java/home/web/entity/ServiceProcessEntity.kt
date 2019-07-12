package home.web.entity

import home.db.dao.ProcessDays
import home.db.dao.Processes
import home.db.entity.MyProcess
import home.db.entity.ProcessDay
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.util.*

object ServiceProcessEntity {
    val list = ArrayList<ProcessEntity>()

    fun getList(): List<ProcessEntity> {
        if (list.size == 0) {
            loadList()
        }
        return list
    }

    private fun loadList() {
        transaction {
            val all = MyProcess.all().iterator()
            while (all.hasNext()) {
                val next = all.next()
                val findFirst =
                        ProcessDay.find { ProcessDays.day eq DateTime.now() and (ProcessDays.day eq DateTime.now()) }
                                .firstOrNull()
                val minuteUse = findFirst?.minutes ?: 0
                list.add(
                        ProcessEntity(
                                next.id.value,
                                next.name,
                                next.added,
                                next.limitMinuteOfDay,
                                minuteUse
                        )
                )
            }
        }

    }

    fun add(entity: ProcessEntity) {
        val create = Processes.create(entity.name, entity.timeLimit)
        entity.id = create.id.value
        entity.added = create.added
        entity.timeUse = 0
        list.add(entity)
    }

    fun getProcById(id: Int): ProcessEntity {
        return list.stream()
                .filter { (id1) -> id1 == id }
                .findFirst()
                .orElse(null)
    }

    fun update(entity: ProcessEntity) {
        val procFromList = getProcById(entity.id)
        Processes.update(entity)
        procFromList.name = entity.name
        procFromList.timeLimit = entity.timeLimit
        procFromList.timeUse = entity.timeUse
    }

    fun delete(id: Int) {
        Processes.delete(id)
        list.remove(getProcById(id))
    }

    fun getHistory(procId: Int, limit: Int, offset: Int): List<ProcessDay> {
        return ProcessDays.getAllByProcessId(procId, limit, offset)
    }

    fun getCountHistory(procId: Int): Int {
        return ProcessDays.getCountByProcessId(procId)
    }


}
