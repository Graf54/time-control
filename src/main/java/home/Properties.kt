package home

import java.util.Properties

object Properties {
    val prop = Properties()

    fun getValue(key: String): String {
        if (prop.isEmpty) {
            prop.load(ClassLoader.getSystemResourceAsStream("application.properties"))
        }
        return prop.getProperty(key)
    }
}