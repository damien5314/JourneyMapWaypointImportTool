package ddiehl.minecraft.jmwit

internal class JourneyMapWaypointJson(
    val id: String,
    val name: String,
    val icon: String,
    val x: Int,
    val y: Int,
    val z: Int,
    val r: Int,
    val g: Int,
    val b: Int,
    val enable: Boolean,
    val type: String,
    val origin: String,
    val dimensions: Array<Int>,
    val persistent: Boolean,
)
