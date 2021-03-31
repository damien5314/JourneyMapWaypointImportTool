package ddiehl.minecraft.jmwit.xaero

import ddiehl.minecraft.jmwit.ExitCode
import ddiehl.minecraft.jmwit.Waypoint
import java.awt.Color
import java.io.File
import java.io.FileReader
import kotlin.system.exitProcess

internal class XaeroWaypointParser {

    companion object {
        private const val EXPECTED_ELEMENTS = 12
    }

    fun parse(inputFile: File): List<Waypoint> {
        val reader = FileReader(inputFile)
        return reader.useLines { sequence ->
            sequence.asIterable()
                .map { parseLine(it).also { println(it) } }
        }
    }

    private fun parseLine(line: String): Waypoint {
        val elements = line.split(delimiters = arrayOf(":"))
        if (elements.size != EXPECTED_ELEMENTS) {
            System.err.println("Waypoint format has changed; expected $EXPECTED_ELEMENTS elements, parsed ${elements.size}")
            exitProcess(ExitCode.XAERO_FORMAT_CHANGED.code)
        }
        return Waypoint(
            id = formatIdForWaypoint(
                name = elements[1],
                x = elements[3].toInt(),
                y = elements[4].toInt(),
                z = elements[5].toInt(),
            ),
            name = elements[1],
            x = elements[3].toInt(),
            y = elements[4].toInt(),
            z = elements[5].toInt(),
            // TODO: Add mappings from Xaero colors to RGB
            color = Color(0, 0, 0),
            enable = !elements[7].toBoolean(),
        )
    }

    private fun formatIdForWaypoint(name: String, x: Int, y: Int, z: Int): String {
        return "${name}_${x},${y},${z}"
    }
}
