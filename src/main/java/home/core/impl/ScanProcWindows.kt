package home.core.impl

import home.core.api.ScanProc
import java.io.InputStreamReader
import java.util.*

class ScanProcWindows : ScanProc {
    private var listProcess: MutableList<String> = arrayListOf()

    override fun refreshProcess() {
        listProcess = arrayListOf()
        val process = Runtime.getRuntime().exec("tasklist.exe")
        val scanner = Scanner(InputStreamReader(process.inputStream))
        scanner.use {
            while (scanner.hasNext()) {
                listProcess.add(scanner.nextLine().toLowerCase())
            }
        }
    }

    override fun find(nameProc: String): Boolean {
        val lower = nameProc.toLowerCase()
        for (process in listProcess) {
            if (process.contains(lower)) {
                return true
            }
        }
        return false
    }
}