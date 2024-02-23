package parser

import model.Matrix
import mu.KotlinLogging

class MatrixParser {
    private val logger = KotlinLogging.logger {}

    fun createMatrixFromString(matrixString: String): Matrix {
        logger.trace { "входящая строка\n$matrixString" }

        val rows = matrixString.trim().removeSurrounding("[[", "]]").split("],[","], [")
        val numRows = rows.size
        val numCols = rows.first().split(",").size
        val matrix = Matrix(numRows, numCols)
        logger.trace { "параметры матрицы\nколичество строк: $numRows\nколичество столбцов: $numCols" }

        for ((i, row) in rows.withIndex()) {
            val elements = row.split(",").map { it.trim().toInt() }

            for ((j, elem) in elements.withIndex()) {
                matrix[i, j] = elem
            }
        }

        logger.trace { "восстановленная из входящей строки матрица\n$matrixString" }
        return matrix
    }

}