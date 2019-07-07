package home.core.impl

import org.junit.Test

class KillProcImplTest {
    @Test
    fun off() {
        val killProcImpl = KillProcImpl()
        val scanProcWindows = ScanProcWindows()
        val proc = "notepad.exe"
        Runtime.getRuntime().exec(proc)
        killProcImpl.off(proc)
        Thread.sleep(500)
        assert(!scanProcWindows.find(proc))
    }
}