package home.db.entity

import home.db.dao.ProcessDays
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class ProcessDay(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProcessDay>(ProcessDays)

    var processId by ProcessDays.processId
    var day by ProcessDays.day
    var minutes by ProcessDays.minutes
}
