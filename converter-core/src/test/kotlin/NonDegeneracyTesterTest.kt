import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import tester.NonDegeneracyTester
import model.Matrix

internal class NonDegeneracyTesterTest {

    private val tester = NonDegeneracyTester()

    @Test
    fun testNonDegenerateMatrix() {
        val matrix = Matrix(2, 2)
        matrix[0, 0] = 1; matrix[0, 1] = 2
        matrix[1, 0] = 3; matrix[1, 1] = 4

        assertTrue(tester.test(matrix))
    }

    @Test
    fun testDegenerateMatrixWithZeroRow() {
        val matrix = Matrix(2, 2)
        matrix[0, 0] = 0; matrix[0, 1] = 0
        matrix[1, 0] = 3; matrix[1, 1] = 4

        assertFalse(tester.test(matrix))
    }

    @Test
    fun testDegenerateMatrixWithZeroDeterminant() {
        val matrix = Matrix(2, 2)
        matrix[0, 0] = 1; matrix[0, 1] = 2
        matrix[1, 0] = 2; matrix[1, 1] = 4

        assertFalse(tester.test(matrix))
    }
}
