import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import parser.MatrixParser

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConverterTest {
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
