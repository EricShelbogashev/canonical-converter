import parser.MatrixParser
import service.MatrixConverter
import tester.IsLinearSystemSolutionTester
import tester.NonDegeneracyTester

class CanonicalConverter {
    fun toGenerative(matrix: String): String {
        return convert(matrix, false)
    }

    fun toParityCheck(matrix: String): String {
        return convert(matrix, true)
    }

    private fun convert(matrix: String, toParityCheck: Boolean): String {
        val parser = MatrixParser()
        val nonDegeneracyTester = NonDegeneracyTester()
        val isLinearSystemSolutionTester = IsLinearSystemSolutionTester()
        val from = parser.createMatrixFromString(matrix)

        require(nonDegeneracyTester.test(from)) { "матрица непригодна для преобразования" }
        val to = MatrixConverter.canonical(from)

        if (toParityCheck) {
            require(isLinearSystemSolutionTester.test(to, from)) { "полученная матрица не является проверочной" }
        } else {
            require(isLinearSystemSolutionTester.test(from, to)) { "полученная матрица не является порождающей" }
        }

        return parser.createStringFromMatrix(to)
    }
}