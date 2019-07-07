package home.web.entity

import org.joda.time.DateTime

data class ProcessEntity(
    var id: Int,
    var name: String,
    var added: DateTime,
    var timeLimit: Int,
    var timeUse: Int,
    var editCode: Int = 0,
    var dbHasName: Int = 0
) {
    constructor() : this(0, "", DateTime.now(), 0, 0, 0)
}
