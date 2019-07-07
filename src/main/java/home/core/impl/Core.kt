package home.core.impl

import home.db.dao.ProcessDays
import home.web.entity.ProcessEntity
import home.web.entity.ServiceProcessEntity
import mu.KotlinLogging
import java.util.*

class Core(val timeOfCheck: Int) {
    private val logger = KotlinLogging.logger {}
    private val scanProc = ScanProcWindows()
    private val killProc = KillProcImpl()
    private val millis = timeOfCheck * 60 * 1000L
    private var day = Calendar.getInstance()

    fun start() {
        Thread(startCore()).start()
        logger.info("Start app")
    }

    private fun startCore(): Runnable {
        return Runnable {
            while (true) {
                findProcess()
                checkDay()
                Thread.sleep(millis)
            }
        }

    }


    private fun findProcess() {
        scanProc.refreshProcess()
        for (processEntity in ServiceProcessEntity.getList()) {
            if (scanProc.find(processEntity.name)) {
                processEntity.timeUse += timeOfCheck
                ProcessDays.updateDay(processEntity)
                logger.info("${processEntity.name} running")
                changeProcess(processEntity)
            }
        }
    }

    private fun changeProcess(processEntity: ProcessEntity) {
        if (processEntity.timeLimit < processEntity.timeUse) {
            killProc.off(processEntity.name)
            logger.info("process ${processEntity.name} no have time: kill")
        }

    }

    private fun checkDay() {
        if (day.get(Calendar.DAY_OF_MONTH) != Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
            restartTimeList()
            day = Calendar.getInstance()
            logger.info("New day found")
        }
    }

    private fun restartTimeList() {
        for (processEntity in ServiceProcessEntity.getList()) {
            processEntity.timeUse = 0
            logger.info("process ${processEntity.name} time restart")
        }
    }
}