package ddiehl.minecraft.jmwit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File

internal class JourneyMapWaypointWriter {

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    fun writeWaypointsToDirectory(waypoints: List<Waypoint>, outputDir: File) {
        waypoints.forEach { waypoint ->
            writeWaypointToDirectory(waypoint, outputDir)
        }
    }

    private fun writeWaypointToDirectory(waypoint: Waypoint, outputDir: File) {
        val encodedId = encodeIdForFileName(waypoint.id)
        val outputFile = outputDir.toPath().resolve("$encodedId.json").toFile()
        if (outputFile.exists()) {
            System.err.println("Output file already exists for Waypoint, skipping: ${outputFile.canonicalPath}")
            return
        }
        val json = moshi.adapter(JourneyMapWaypointJson::class.java)
            .indent("  ")
            .toJson(
                JourneyMapWaypointJson(
                    id = waypoint.id,
                    name = waypoint.name,
                    icon = waypoint.iconPath,
                    x = waypoint.x,
                    y = waypoint.y,
                    z = waypoint.z,
                    r = waypoint.color.red,
                    g = waypoint.color.green,
                    b = waypoint.color.blue,
                    enable = waypoint.enable,
                    type = waypoint.type,
                    origin = waypoint.origin,
                    // TODO: Figure out what this is for
                    dimensions = arrayOf(0),
                    persistent = waypoint.persistent,
                )
            )
        outputFile.writeText(json)
    }

    private fun encodeIdForFileName(waypointId: String): String {
        // File name cannot include slashes or it won't be found
        return waypointId.replace(oldValue = "/", newValue = "%2F")
    }
}
