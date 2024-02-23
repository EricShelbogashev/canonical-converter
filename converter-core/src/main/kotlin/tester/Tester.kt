package tester

import model.Matrix

interface MatrixTester {
    fun test(vararg matrices: Matrix): Boolean
}