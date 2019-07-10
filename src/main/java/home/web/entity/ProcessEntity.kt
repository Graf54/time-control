package home.web.entity

import org.joda.time.DateTime

data class ProcessEntity(
    var id: Int,
    var name: String,
    var added: DateTime,
    var timeLimit: Int,
    var timeUse: Int
)
