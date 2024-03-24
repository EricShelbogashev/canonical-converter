import model.Matrix
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MatrixAnyTest {
    @Test
    fun testConstructor() {
        val matrix = Matrix(2, 3)
        assertEquals(2, matrix.rows)
        assertEquals(3, matrix.cols)
    }

    @Test
    fun testEye() {
        val identity = Matrix.eye(3)
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (i == j) assertEquals(1, identity[i, j])
                else assertEquals(0, identity[i, j])
            }
        }
    }

    @Test
    fun testHstack() {
        val a = Matrix(2, 2)
        val b = Matrix(2, 2)
        a[0, 0] = 1; a[0, 1] = 2; a[1, 0] = 3; a[1, 1] = 4
        b[0, 0] = 5; b[0, 1] = 6; b[1, 0] = 7; b[1, 1] = 8

        val c = Matrix.hstack(a, b)

        assertEquals(2, c.rows)
        assertEquals(4, c.cols)
        assertEquals(1, c[0, 0])
        assertEquals(6, c[0, 3])
    }


    @Test
    fun testGaussianElimination() {
        val matrix = Matrix(arrayOf(arrayOf(2, 1, -1), arrayOf(-3, -1, 2), arrayOf(-2, 1, 2)))
        val result = matrix.gaussianElimination()

        val expected = arrayOf(
            arrayOf(1.0, 0.0, 0.0),
            arrayOf(0.0, 1.0, 0.0),
            arrayOf(0.0, 0.0, 1.0)
        )

        for (i in expected.indices) {
            assertArrayEquals(expected[i], result.row(i).map { it.toDouble() }.toTypedArray())
        }
    }

    @Test
    fun testRank() {
        val matrix = Matrix(arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9)))
        val rank = matrix.rank()
        assertEquals(2, rank) // For this specific matrix, the rank should be 2.
    }

    @Test
    fun testSubMatrix() {
        val matrix = Matrix(3, 3)
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                matrix[i, j] = i * 3 + j + 1 // Fill the matrix with numbers 1 through 9
            }
        }

        val subMatrix = matrix.subMatrix(1, 3, 1, 3)
        val expected = arrayOf(arrayOf(5, 6), arrayOf(8, 9))

        for (i in expected.indices) {
            assertArrayEquals(expected[i], subMatrix.row(i))
        }
    }

    @Test
    fun testDeterminant() {
        val matrix = Matrix(arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 10)))
        val determinant = matrix.determinant()
        assertEquals(-3, determinant) // The determinant for this matrix should be -3.
    }

    @Test
    fun testIsSquare() {
        val squareMatrix = Matrix(2, 2)
        val nonSquareMatrix = Matrix(2, 3)

        assertTrue(squareMatrix.isSquare())
        assertFalse(nonSquareMatrix.isSquare())
    }

    @Test
    fun testTranspose() {
        val matrix = Matrix(2, 3)
        matrix[0, 0] = 1; matrix[0, 1] = 2; matrix[0, 2] = 3
        matrix[1, 0] = 4; matrix[1, 1] = 5; matrix[1, 2] = 6

        val transposed = matrix.transpose()
        assertEquals(3, transposed.rows)
        assertEquals(2, transposed.cols)

        assertEquals(1, transposed[0, 0])
        assertEquals(4, transposed[0, 1])
        assertEquals(5, transposed[1, 1])
    }

}