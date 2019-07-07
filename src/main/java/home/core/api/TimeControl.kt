package home.core.api

interface TimeControl {
    fun setTime(minute: Int)
    fun changeTime(minute: Int)
    fun hasTime(): Boolean
    fun restartTime()
}