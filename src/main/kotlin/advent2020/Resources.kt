package advent2020

import java.io.File

internal object Resources {

    fun resourceAsList(fileName : String) : List<String> =
        File(Resources.javaClass.classLoader.getResource(fileName).toURI())
            .readLines()

    fun resourceAsString(fileName : String) : String =
        File(Resources.javaClass.classLoader.getResource(fileName).toURI())
            .readText()
}