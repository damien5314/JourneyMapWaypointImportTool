# JourneyMapWaypointImportTool
Java app that imports waypoints from Xaero's Minimap to JourneyMap (mods for Minecraft).

## Usage
**Back up your `.minecraft` folder before running anything. I am not liable for any corrupted worlds or configs as a result of running this script.**

This program requires Java installed on your machine to run. If you're playing Minecraft with these mods, that should already be the case.

Download the tool from the [releases page](https://github.com/damien5314/JourneyMapWaypointImportTool/releases). The ZIP file contains scripts for Unix and Windows.

The application requires you to specify the waypoints input file as well as the output directory for the JourneyMap imports:
1. Xaero's Minimap stores waypoints in `.minecraft/XaeroWaypoints/MyWorld/dim%0/waypoints.txt`
1. JourneyMap stores waypoints in `.minecraft/journeymap/data/sp/MyWorld/waypoints`

Copy the application for your platform to your `.minecraft` directory, then run it passing the input and output directories as arguments:

```shell
journeymapImport 'XaeroWaypoints/MyWorld/dim%0/waypoints.txt' 'journeymap/data/sp/MyWorld/waypoints'
```

If a JourneyMap waypoint file already exists for a waypoint in the Xaero's Minimap config, the existing waypoint _will not_ be overwritten. You should see a message in the command output indicating any skipped waypoints.

## Currently supported imports
- Xaero's Minimap (1.12.2) to JourneyMap (1.12.2)

## Contributing
PRs are welcome for enhancements to this tool. Support is currently limited to a very specific use case that I initially needed when writing the tool, but it should be fairly easy to adapt the code for other mods and mod versions if needed.

Run the project with the gradle wrapper:

```gradle
./gradlew run --args="'XaeroWaypoints/MyWorld/dim%0/waypoints.txt' 'journeymap/data/sp/MyWorld/waypoints'"
```

Create the executable scripts with `assemble`:

```gradle
./gradlew assemble
```
