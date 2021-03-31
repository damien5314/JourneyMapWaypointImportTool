package ddiehl.minecraft.xtjm

import java.io.File
import kotlin.system.exitProcess

/**
 *
 * Reads the contents of a Xaero's Minimap waypoints file and exports them
 * to the JourneyMap waypoints directory. This is used to transfer waypoints
 * between the mods when switching from Xaero's Minimap to JourneyMap.
 *
 * Steps
 * Back up existing JourneyMap waypoints to a zip file just in case something goes wrong (necessary for publishing externally)
 * Read each line of Xaero waypoint file in a loop
 * Parse attributes from Xaero waypoint into variables
 *  Name
 *  X, Y, Z coordinates
 *  Enabled or Disables
 *  Generate random color for JourneyMap
 *  Write out JSON file in JourneyMap format
 *  Add ability to specify .minecraft directory
 *  Optional: Check if JourneyMap waypoints directory exists and require a -f option to merge those files
 */
class XaeroToJourneyMapConverter {

    fun convert(inputDir: String, outputDir: String) {
        val inputDirFile = File(inputDir)
        val outputDirFile = File(outputDir)

        if (!inputDirFile.exists()) {
            System.err.println("Input file directory does not exist: $inputDir")
            exitProcess(ExitCode.INPUT_DIR_DOES_NOT_EXIST.code)
        }

        convertInternal(inputDirFile, outputDirFile)
    }

    private fun convertInternal(inputFile: File, outputDir: File) {
        val xaeroWaypointParser = XaeroWaypointParser()
        val waypoints = xaeroWaypointParser.parse(inputFile)
        val journeyMapWaypointWriter = JourneyMapWaypointWriter()
        journeyMapWaypointWriter.writeWaypointsToDirectory(waypoints, outputDir)
    }
}

fun main(args: Array<String>) {
    if (args.size != 2) {
        System.err.println("Incorrect number of arguments supplied: ${args.size}")
        exitProcess(ExitCode.PROGRAM_ARGUMENTS.code)
    }

    val converter = XaeroToJourneyMapConverter()
    converter.convert(args[0], args[1])
}
