package home.core.impl

import home.core.api.KillProc

class KillProcImpl : KillProc {

    override fun off(procName: String) {
        Runtime.getRuntime().exec("taskkill /F /IM $procName")
    }
}