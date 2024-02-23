import model.Matrix
import parser.MatrixParser
import service.MatrixConverter
import tester.IsLinearSystemSolutionTester
import tester.NonDegeneracyTester

class CanonicalConverter {
    fun toGenerative(matrix: String): Matrix {
        return convert(matrix, false)
    }

    fun toParityCheck(matrix: String): Matrix {
        return convert(matrix, true)
    }

    private fun convert(matrix: String, toParityCheck: Boolean): Matrix {
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

        return to
    }
}