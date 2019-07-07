package home.core.impl

import org.junit.Test

class ScanProcWindowsTest {
    val scanProcWindows = ScanProcWindows()
    @Test
    fun find() {
        assert(scanProcWindows.find("java.exe"))
        assert(!scanProcWindows.find("javaasdf.exe"))
    }
}