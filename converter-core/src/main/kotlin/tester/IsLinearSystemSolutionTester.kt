package tester

import model.Matrix

class IsLinearSystemSolutionTester : MatrixTester {
    override fun test(vararg matrices: Matrix): Boolean {
        require(matrices.size == 2) {
            "количество матриц не соответствует двум: матрица коэффициентов и матрица решений"
        }
        val (a: Matrix, x: Matrix) = matrices
        return (a * x.transpose() % 2).isZero()
    }
}