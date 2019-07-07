package home.core.impl

import org.junit.Test

class TimeControlProcTest {
    private val timeControl = TimeControlProc("heroes3.exe")

    @Test
    fun changeTime() {
        timeControl.changeTime(20)
        assert(timeControl.hasTime())
        timeControl.changeTime(-40)
        assert(!timeControl.hasTime())
    }

    @Test
    fun control() {
        assert(!timeControl.hasTime())
        timeControl.setTime(20)
        assert(timeControl.hasTime())
    }

    @Test
    fun hasTime() {
        assert(!timeControl.hasTime())
        timeControl.setTime(20)
        assert(timeControl.hasTime())
    }
}