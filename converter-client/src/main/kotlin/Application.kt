import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required


fun main(args: Array<String>) {
    val parser = ArgParser("canonical-converter")
    val matrix by parser.option(
        ArgType.String,
        shortName = "m",
        description = "матрица для преобразования в формате \"[[...,0,...,1,...],[...],...]\""
    ).required()
    val isParityCheck by parser.option(
        ArgType.Boolean,
        shortName = "p",
        description = "задана проверочная матрица"
    ).default(false)

    parser.parse(args)

    val converter = CanonicalConverter()
    val result = if (isParityCheck) {
        converter.toGenerative(matrix)
    } else {
        converter.toParityCheck(matrix)
    }

    println(result)
}
