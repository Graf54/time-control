package home.db.entity

import home.db.dao.Processes
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class MyProcess(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MyProcess>(Processes)

    var name by Processes.name
    var added by Processes.added
    var limitMinuteOfDay by Processes.limitMinuteOfDay
}
