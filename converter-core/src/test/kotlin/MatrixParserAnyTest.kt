import model.Matrix
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import parser.MatrixParser

internal class MatrixParserAnyTest {
    
    private val matrixParser = MatrixParser()

    @Test
    fun testCreateMatrixFromString() {
        val inputString = "[[1, 2, 3],\n[4, 5, 6],\n[7, 8, 9]]"
        val expectedMatrix = Matrix(3, 3)
        expectedMatrix[0, 0] = 1; expectedMatrix[0, 1] = 2; expectedMatrix[0, 2] = 3
        expectedMatrix[1, 0] = 4; expectedMatrix[1, 1] = 5; expectedMatrix[1, 2] = 6
        expectedMatrix[2, 0] = 7; expectedMatrix[2, 1] = 8; expectedMatrix[2, 2] = 9

        val resultMatrix = matrixParser.createMatrixFromString(inputString)

        for (i in 0 until expectedMatrix.rows) {
            for (j in 0 until expectedMatrix.cols) {
                assertEquals(expectedMatrix[i, j], resultMatrix[i, j], "Mismatch at position [$i, $j]")
            }
        }
    }

    @Test
    fun testCreateStringFromMatrix() {
        val matrix = Matrix(2, 2)
        matrix[0, 0] = 1; matrix[0, 1] = 2
        matrix[1, 0] = 3; matrix[1, 1] = 4
        val expectedString = "[[1, 2],\n[3, 4]]"

        val resultString = matrixParser.createStringFromMatrix(matrix)

        assertEquals(expectedString, resultString)
    }
}