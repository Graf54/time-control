package home.core.api

interface ScanProc {
    fun find(nameProc: String): Boolean

    fun refreshProcess()
}