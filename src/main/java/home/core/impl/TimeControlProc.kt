package home.core.impl

class TimeControlProc(val name: String) {

    private var timeTotal: Int = 0
    private var timeLeft: Int = 0

    fun restartTime() {
        timeLeft = timeTotal
    }

    fun changeTime(minute: Int) {
        timeLeft += minute
    }

    fun setTime(minute: Int) {
        timeTotal = minute
        restartTime()
    }


    fun hasTime(): Boolean {
        return timeLeft > 0
    }
}