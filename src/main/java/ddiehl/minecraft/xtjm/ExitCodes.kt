package ddiehl.minecraft.xtjm

internal enum class ExitCode(val code: Int) {
    PROGRAM_ARGUMENTS(1),
    INPUT_DIR_DOES_NOT_EXIST(2),
    XAERO_FORMAT_CHANGED(3),
    ;
}
