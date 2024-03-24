import model.Matrix
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import tester.IsLinearSystemSolutionTester

internal class IsLinearSystemSolutionTesterAnyTest {

    private val tester = IsLinearSystemSolutionTester()

    @Test
    fun testValidSolution() {
        // Setup a simple linear system and its solution
        val a = Matrix(2, 2)
        a[0, 0] = 1; a[0, 1] = 1
        a[1, 0] = 2; a[1, 1] = 4

        val x = Matrix(1, 2) // Solution matrix (considering as a row vector here)
        x[0, 0] = 1; x[0, 1] = 1 // A solution for demonstration; adjust based on your system

        assertTrue(tester.test(a, x), "Expected x to be a valid solution for the linear system represented by matrix a")
    }

    @Test
    fun testSolutionWithModulo() {
        // Example where solution is valid under modulo 2 arithmetic
        val a = Matrix(2, 2)
        a[0, 0] = 2; a[0, 1] = 4
        a[1, 0] = 6; a[1, 1] = 8

        val x = Matrix(1, 2) // Solution that works under modulo 2
        x[0, 0] = 1; x[0, 1] = 1

        assertTrue(tester.test(a, x), "Expected x to be a valid solution under modulo 2 for the linear system represented by matrix a")
    }
}
