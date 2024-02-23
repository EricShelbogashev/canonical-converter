import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required
import java.io.File


fun main(args: Array<String>) {
    val parser = ArgParser("canonical-converter")
    val matrixArg by parser.option(
        ArgType.String,
        shortName = "m",
        description = "путь до файла или матрица для преобразования в формате \"[[...,0,...,1,...],[...],...]\""
    ).required()
    val isParityCheck by parser.option(
        ArgType.Boolean,
        shortName = "p",
        description = "задана проверочная матрица"
    ).default(false)

    parser.parse(args)

    val matrixString = if (isFilePath(matrixArg)) {
        readFile(matrixArg)
    } else {
        matrixArg
    }

    val converter = CanonicalConverter()
    val result = if (isParityCheck) {
        converter.toGenerative(matrixString)
    } else {
        converter.toParityCheck(matrixString)
    }

    println(result)
}

fun isFilePath(path: String): Boolean {
    val file = File(path)
    return file.exists() && file.isFile
}

fun readFile(filename: String): String {
    val file = File(filename)
    return file.readText()
}