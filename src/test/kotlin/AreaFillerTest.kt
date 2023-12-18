import helpers.AreaFillerHelper
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.AfterTest


class AreaFillerTest {

    val emptyCanvasW = 10
    val emptyCanvasH = 10

    var testCanvas: Canvas = Canvas(emptyCanvasW, emptyCanvasH)

    @AfterTest
    fun printCanvas() {
        testCanvas.print()
    }

    @Test
    fun testFillInEmptyCanvas() {
        testCanvas = Canvas(emptyCanvasW, emptyCanvasH)
        val emptyAreaFiller = AreaFillerHelper('c', testCanvas.canvasArray)
        emptyAreaFiller.fillIn(1,1)
        for (i in 1..emptyCanvasW) {
            for (j in 1..emptyCanvasH) {
                assertTrue(testCanvas.canvasArray[i][j] == 'c')
            }
        }
        for (i in 1..emptyCanvasW) {
            assertFalse(testCanvas.canvasArray[0][i] == 'c')
            assertFalse(testCanvas.canvasArray[emptyCanvasH+1][i] == 'c')
        }
    }

    @Test
    fun testFillStraightArea() {
        testCanvas = createTestCanvas()
        val areaFiller = AreaFillerHelper('c', testCanvas.canvasArray)
        areaFiller.fillIn(1,1)
        for (i in 1..20) {
            assertTrue(testCanvas.canvasArray[i][1] == 'c')
            assertTrue(testCanvas.canvasArray[i][2] == 'c')
            assertFalse(testCanvas.canvasArray[i][0] == 'c')
            for (j in 3..20) {
                assertFalse(testCanvas.canvasArray[i][j] == 'c')
            }
        }
    }

    @Test
    fun testFillComplexArea() {
        testCanvas = createTestCanvas()
        val areaFiller = AreaFillerHelper('c', testCanvas.canvasArray)
        areaFiller.fillIn(5,1)
        for (i in 0..21) {
            for (j in 0..3) {
                assertFalse(testCanvas.canvasArray[i][j] == 'c')
            }
            assertFalse(testCanvas.canvasArray[i][21] == 'c')
        }

        for (i in 5..10) {
            assertFalse(testCanvas.canvasArray[2][i] == 'c')
            assertTrue(testCanvas.canvasArray[3][i] == 'c')
            assertTrue(testCanvas.canvasArray[1][i] == 'c')
        }

        for (i in 4..7) {
            for (j in 4..20) {
                assertTrue(testCanvas.canvasArray[i][j] == 'c')
            }
        }

        for (i in 8..15) {
            for (j in 4..7) {
                assertTrue(testCanvas.canvasArray[i][j] == 'c')
            }
            for (j in 8..15) {
                assertFalse(testCanvas.canvasArray[i][j] == 'c')
            }
            for (j in 16..20) {
                assertTrue(testCanvas.canvasArray[i][j] == 'c')
            }
        }

        for (i in 18..20) {
            for (j in 4..17) {
                assertTrue(testCanvas.canvasArray[i][j] == 'c')
            }
            for (j in 18..21) {
                assertFalse(testCanvas.canvasArray[i][j] == 'c')
            }
        }
    }

    @Test
    fun testFillInRectangle() {
        testCanvas = createTestCanvas()
        val areaFiller = AreaFillerHelper('c', testCanvas.canvasArray)
        areaFiller.fillIn(19,19)
        areaFiller.fillIn(12,12)
        for (i in 0..8) {
            for (j in 0..20) {
                assertFalse(testCanvas.canvasArray[i][j] == 'c')
            }
        }
        for (i in 9..14) {
            for (j in 0..8) {
                assertFalse(testCanvas.canvasArray[i][j] == 'c')
            }
            for (j in 9..14) {
                assertTrue(testCanvas.canvasArray[i][j] == 'c')
            }
            for (j in 15..21) {
                assertFalse(testCanvas.canvasArray[i][j] == 'c')
            }
        }
        for (i in 15..21) {
            for (j in 0..20) {
                if (j == 19 && i == 19) assertTrue(testCanvas.canvasArray[i][j] == 'c')
                else assertFalse(testCanvas.canvasArray[i][j] == 'c')
            }
        }
    }

    @Test
    fun testFillRectangleOutline() {
        testCanvas = createTestCanvas()
        val areaFiller = AreaFillerHelper('c', testCanvas.canvasArray)
        areaFiller.fillIn(8,8)
        for (i in 0..8) {
            for (j in 0..21) {
                if (i == 8 && j >= 8 && j <= 15) assertTrue(testCanvas.canvasArray[i][j] == 'c')
                else assertFalse(testCanvas.canvasArray[i][j] == 'c')
            }
        }
        for (i in 9..14) {
            for (j in 0..21) {
                if (j == 8 || j == 15) assertTrue(testCanvas.canvasArray[i][j] == 'c')
                else assertFalse(testCanvas.canvasArray[i][j] == 'c')
            }
        }
        for (i in 15..21) {
            for (j in 0..21) {
                if (i == 15 && j >= 8 && j <= 15) assertTrue(testCanvas.canvasArray[i][j] == 'c')
                else assertFalse(testCanvas.canvasArray[i][j] == 'c')
            }
        }
    }
}