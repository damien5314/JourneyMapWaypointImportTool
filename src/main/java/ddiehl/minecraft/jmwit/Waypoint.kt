package ddiehl.minecraft.jmwit

import java.awt.Color

internal data class Waypoint(
    val id: String,
    val name: String,
    val iconPath: String = "waypoint-normal.png",
    val x: Int,
    val y: Int,
    val z: Int,
    val color: Color = Color(0, 0, 0),
    val enable: Boolean,
    val type: String = "Normal",
    val origin: String = "xaero_import",
    val persistent: Boolean = true,
)
