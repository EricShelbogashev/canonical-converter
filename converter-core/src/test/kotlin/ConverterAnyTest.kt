import model.Matrix
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import parser.MatrixParser
import service.MatrixConverter
import kotlin.math.absoluteValue
import kotlin.math.min
import kotlin.random.Random
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConverterAnyTest {
    @ParameterizedTest
    @MethodSource("matrices")
    fun toGenerative(parityCheck: String, generative: String) {
        val converter = CanonicalConverter()
        val parser = MatrixParser()

        val generativeActualString = converter.toGenerative(parityCheck)

        val generativeActual = parser.createMatrixFromString(generativeActualString)
        val generativeExpectedMatrix = parser.createMatrixFromString(generative)
        assert(generativeActual.isTransformableTo(generativeExpectedMatrix))
    }

    @ParameterizedTest
    @MethodSource("matrices")
    fun toParityCheck(parityCheck: String, generative: String) {
        val converter = CanonicalConverter()
        val parser = MatrixParser()

        val parityCheckActualString = converter.toParityCheck(generative)

        val parityCheckActual = parser.createMatrixFromString(parityCheckActualString)
        val parityCheckExpected = parser.createMatrixFromString(parityCheck)

        assert(parityCheckActual.isTransformableTo(parityCheckExpected))
    }

    @ParameterizedTest
    @MethodSource("singularMatrices")
    fun singularMatricesThrow(matrix: String) {
        val converter = CanonicalConverter()

        assertThrows<IllegalArgumentException> {
            converter.toGenerative(matrix)
        }
        assertThrows<IllegalArgumentException> {
            converter.toParityCheck(matrix)
        }
    }

    @Test
    fun testToRowEchelonFormMod2() {
        val matrix = Matrix(arrayOf(arrayOf(1, 0, 1), arrayOf(1, 1, 1)))
        val expected = Matrix(arrayOf(arrayOf(1, 0, 1), arrayOf(0, 1, 0)))
        val actual = matrix.toRowEchelonForm(2)
        assertEquals(expected, actual.first)
    }

    private fun transpositionAndCombination(matrix: Matrix): Matrix {
        // Assuming the submatrix is the entire matrix for simplicity
        val transposed = matrix.transpose()

        // Creating an identity matrix of the same size as the transposed matrix
        val identityMatrix = Matrix.eye(transposed.rows)

        // Combining the transposed matrix with the identity matrix horizontally
        return Matrix.hstack(transposed, identityMatrix)
    }

    @Test
    fun testTranspositionAndCombination() {
        val matrix = Matrix(arrayOf(arrayOf(1, 0), arrayOf(0, 1))) // A simple 2x2 identity matrix for simplicity
        val expected = Matrix.hstack(matrix.transpose(), Matrix.eye(2))
        val actual = transpositionAndCombination(matrix) // This method needs to be accessible for testing

        assertEquals(expected, actual)
    }

    private fun restoreAndApplyModulus(generativeMatrix: Matrix, pivotColumns: List<Int>): Matrix {
        val restoredMatrix = Matrix(generativeMatrix.rows, generativeMatrix.cols)

        // Восстановление исходной матрицы G
        pivotColumns.forEachIndexed { index, pivot ->
            for (row in 0 until generativeMatrix.rows) {
                restoredMatrix[row, pivot] = generativeMatrix[row, index]
            }
        }
        (0 until generativeMatrix.cols).filterNot { pivotColumns.contains(it) }.forEachIndexed { index, col ->
            for (row in 0 until generativeMatrix.rows) {
                restoredMatrix[row, col] = generativeMatrix[row, index + pivotColumns.size]
            }
        }

        // Applying modulus operation to each element in the matrix
        val modulusMatrix = restoredMatrix % 2 // Assuming % operator is defined for modulus operation

        return modulusMatrix
    }

    @Test
    fun testRestorationAndModulus() {
        val generativeMatrix = Matrix(arrayOf(arrayOf(1, 0, 0, 1), arrayOf(0, 1, 1, 0)))
        val pivotColumns = listOf(2, 3)
        val expected = Matrix(arrayOf(arrayOf(0, 1, 1, 0), arrayOf(1, 0, 0, 1)))
        val actual = restoreAndApplyModulus(generativeMatrix, pivotColumns) // This method needs to be accessible for testing

        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    fun test() {
        val generativeMatrix = Matrix(200, 2000) // G = k=200, n=2000 -> H = (2000-200, 2000)
        for (i in 0..< generativeMatrix.cols) {
            for (j in 1..3) {
                val rowIndex = ((Random.nextInt() + j) % generativeMatrix.rows).absoluteValue
                generativeMatrix[rowIndex, i] = 1
            }
        }
        val n = 2000
        val k = 200
        val check = MatrixConverter.canonical(generativeMatrix)
        assertEquals(min(n-k, n), check.rank())
    }

    private fun singularMatrices(): Array<Array<Any>> = arrayOf(
        arrayOf("[[0,1,1,1,0,0],[0,1,1,1,0,0],[1,1,0,0,0,1]]"),
        arrayOf("[[1,0,0,0,0,0,0,0],[0,1,1,1,1,1,1,0],[0,0,0,0,0,0,0,1],[1,1,1,1,1,1,1,1]]"),
        arrayOf("[[1,2],[2,4]]"),
        arrayOf("[[0,0,0],[0,0,0],[0,0,0]]"),
        arrayOf("[[1,2,3],[2,4,6],[3,6,9]]"),
        arrayOf("[[0,1,0],[0,2,0],[0,3,0]]")
    )

    private fun matrices(): Array<Array<Any>> = arrayOf(
        arrayOf(
            "[[0,1,1,1,0,0],[1,0,1,0,1,0],[1,1,0,0,0,1]]",
            "[[1,0,0,0,1,1],[0,1,0,1,0,1],[0,0,1,1,1,0]]"
        ),
        arrayOf(
            "[[0,1,1,1,0,0],[1,1,1,0,1,0],[1,0,1,0,0,1]]",
            "[[1,0,0,0,1,1],[0,1,0,1,1,0],[0,0,1,1,1,1]]"
        ),
        arrayOf(
            "[[0,1,1,0,1],[0,0,0,1,1],[1,1,0,0,1]]",
            "[[1,0,1,1,1],[1,1,1,0,0]]"
        ),
        arrayOf(
            "[[1,1,0,0,1,1,1,1,1,0,1,1],[0,0,1,1,1,1,0,0,0,1,1,0],[1,1,1,1,0,1,1,1,1,0,0,0],[0,0,0,0,1,0,1,0,0,1,0,1]]",
            "[[1,1,0,0,0,0,0,0,0,0,0,0],[0,0,1,0,1,1,0,0,0,1,0,0],[0,0,1,0,1,0,1,0,0,0,0,0],[1,0,0,0,0,0,0,1,0,0,0,0],[1,0,0,0,0,0,0,0,1,0,0,0],[0,0,1,1,0,0,0,0,0,0,0,0],[1,0,1,0,0,0,0,0,0,0,1,0],[1,0,1,0,0,0,0,0,0,1,0,1]]"
        ),
        arrayOf(
            "[[0,1,1,1,1,0,0,0],[1,0,1,1,0,1,0,0],[1,1,0,1,0,0,1,0],[1,1,1,0,0,0,0,1]]",
            "[[1,0,1,0,1,0,1,0],[0,1,1,0,0,1,1,0],[0,0,0,1,1,1,1,0],[1,1,1,1,1,1,1,1]]"
        )
    )
}
