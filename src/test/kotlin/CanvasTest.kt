import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.AfterTest

class CanvasTest {

    val emptyCanvasW = 10
    val emptyCanvasH = 10

    var testCanvas: Canvas = Canvas(emptyCanvasW, emptyCanvasH)

    @AfterTest
    fun printCanvas() {
        testCanvas.print()
    }

    @Test
    fun testDrawVerticalLine() {
        testCanvas = Canvas(emptyCanvasW, emptyCanvasH)
        val x1 = 2
        val x2 = 2
        val y1 = 2
        val y2 = emptyCanvasH -1
        testCanvas.drawLine(x1,y1,x2,y2)
        for (i in 0..emptyCanvasH + 1) {
            for (j in 1..emptyCanvasW + 1) {
                if (i in y1..y2 && j == x1) assertTrue(testCanvas.canvasArray[i][j] == 'x')
                else assertFalse(testCanvas.canvasArray[i][j] == 'x')
            }
        }
    }

    @Test
    fun testDrawHorizontalLine() {
        testCanvas = Canvas(emptyCanvasW, emptyCanvasH)
        val x1 = 2
        val x2 = emptyCanvasW - 1
        val y1 = 2
        val y2 = 2
        testCanvas.drawLine(x1,y1,x2,y2)
        for (i in 0..emptyCanvasH + 1) {
            for (j in 0..emptyCanvasW + 1) {
                if (j in x1..x2 && i == y1) assertTrue(testCanvas.canvasArray[i][j] == 'x')
                else assertFalse(testCanvas.canvasArray[i][j] == 'x')
            }
        }
    }

    @Test
    fun testDrawRectangle() {
        testCanvas = Canvas(emptyCanvasW, emptyCanvasH)
        val x1 = 2
        val x2 = emptyCanvasW - 1
        val y1 = 2
        val y2 = emptyCanvasH -1
        testCanvas.drawRectangle(x1,y1,x2,y2)
        for (i in 0..emptyCanvasH + 1) {
            for (j in 0..emptyCanvasW + 1) {
                if ((i == y1 || i == y2) && j in x1..x2) assertTrue(testCanvas.canvasArray[i][j] == 'x')
                else if ((j == x1 || j == x2) && i in y1..y2) assertTrue(testCanvas.canvasArray[i][j] == 'x')
                else assertFalse(testCanvas.canvasArray[i][j] == 'x')
            }
        }
    }


}