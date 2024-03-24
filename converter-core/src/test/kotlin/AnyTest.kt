import org.junit.jupiter.api.Test
import parser.MatrixParser
import service.MatrixConverter
import kotlin.test.assertTrue

class AnyTest {
    @Test
    fun test1() {
        val H1s = MatrixParser().createMatrixFromString(
            "[[0,0,0,1,1,1,1,0],[0,1,1,0,0,1,1,0],[1,0,1,0,1,0,1,0]]"
        )

        val H1 = MatrixConverter.canonical(H1s)
        println(H1)
        val H2 =
            MatrixParser().createMatrixFromString("[[1,1,1,0,0,0,0,0],[1,0,0,1,1,0,0,0],[0,1,0,1,0,1,0,0],[1,1,0,1,0,0,1,0],[0,0,0,0,0,0,0,1]]")
        assertTrue { H2.isTransformableTo(H1) }
    }

    @Test
    fun test2() {
        val H1s = MatrixParser().createMatrixFromString(
            "[[0,0,1,0,1,1,1,0],[0,1,0,1,1,0,1,0],[1,0,0,1,0,1,1,0]]"
        )

        val H1 = MatrixConverter.canonical(H1s)
        val H2 =
            MatrixParser().createMatrixFromString("[[1,1,0,1,0,0,0,0],[0,1,1,0,1,0,0,0],[1,0,1,0,0,1,0,0],[1,1,1,0,0,0,1,0],[0,0,0,0,0,0,0,1]]")
        println((H2 * H1s.transpose()) % 2)
        assertTrue { H2.isTransformableTo(H1) }
    }

    @Test
    fun test3() {
        val h = MatrixParser().createMatrixFromString(
            "[[0, 0, 1, 0, 1, 1, 1, 0],[0, 1, 0, 1, 1, 0, 1, 0],[1, 0, 0, 1, 0, 1, 1, 0]]"
        )
        println(h.transpose())
    }

    @Test
    fun test4() {
        val parser = MatrixParser()
        //11
        //111
        //1101
        //1011
        //11001
        //10011
        //101001
        //100101
        //111101
        //111011
        //110111
        //101111
        val list = listOf(
//            parser.createMatrixFromString(
//                "[[1,1,0,1,0,0,0]," +
//                        "[0,1,1,0,1,0,0]," +
//                        "[0,0,1,1,0,1,0]," +
//                        "[0,0,0,1,1,0,1]]"
//            ),
//            parser.createMatrixFromString(
//                "[[0,0,0,0,3,3,1,1]," +
//                        "[3,0,1,0,0,0,0,0]," +
//                        "[0,3,0,1,3,0,1,0]," +
//                        "[0,1,0,4,3,1,2,0]," +
//                        "[0,3,3,3,1,1,1,0]," +
//                        "[0,3,0,1,0,0,0,0]]"
//            ),
            parser.createMatrixFromString(
                "[[1, 1, 1, 0, 0, 0, 0, 0, 0]," +
                        "[0, 1, 1, 1, 0, 0, 0, 0, 0]," +
                        "[0, 0, 1, 1, 1, 0, 0, 0, 0]," +
                        "[0, 0, 0, 1, 1, 1, 0, 0, 0]," +
                        "[0, 0, 0, 0, 1, 1, 1, 0, 0]," +
                        "[0, 0, 0, 0, 0, 1, 1, 1, 0]," +
                        "[1, 1, 1, 0, 1, 1, 0, 1, 1]]"
            )
        )
        for (matrix in list) {
            println(matrix)
            val g1 = matrix.toRowEchelonForm(2).first
            val g2 = matrix.toRowEchelonForm(2).first
            println()
            println(g1)
            println(g2)
        }
    }
}