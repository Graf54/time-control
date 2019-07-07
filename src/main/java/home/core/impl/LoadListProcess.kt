package home.core.impl

import mu.KotlinLogging
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class LoadListProcess {
    private val logger = KotlinLogging.logger {}

    fun loadList(): MutableList<TimeControlProc> {
        val list = mutableListOf<TimeControlProc>()

        /*val reader = BufferedReader(FileReader(File("program.txt")))
        while (reader.ready()) {
            val readLine = reader.readLine()
            try {
                addProcess(list, readLine)
            } catch (e: Exception) {
                logger.warn("Can't parse string: $readLine")
            }
        }

        logger.info("List app loaded. Size = ${list.size}")*/
        return list
    }

    private fun addProcess(list: MutableList<TimeControlProc>, string: String) {
        val split = string.split("=")
        val timeControlProc = TimeControlProc(split[0])
        timeControlProc.setTime(split[1].toInt())
        list.add(timeControlProc)
    }
}