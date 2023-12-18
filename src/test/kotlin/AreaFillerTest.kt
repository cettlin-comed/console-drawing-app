import helpers.AreaFillerHelper
import org.junit.jupiter.api.Assertions.*
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
        val resultCanvas = readCanvas(
"""------------
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
------------
""", 10, 10)

        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testFillStraightArea() {
        testCanvas = createTestCanvas()
        val areaFiller = AreaFillerHelper('c', testCanvas.canvasArray)
        areaFiller.fillIn(1,1)
        val resultCanvas = readCanvas(
"""----------------------
|ccx                 |
|ccx xxxxxx          |
|ccx                 |
|ccx                 |
|ccx                 |
|ccx                 |
|ccx                 |
|ccx    xxxxxxxx     |
|ccx    x      x     |
|ccx    x      x     |
|ccx    x      x     |
|ccx    x      x     |
|ccx    x      x     |
|ccx    x      x     |
|ccx    xxxxxxxx     |
|ccx                 |
|ccx                 |
|ccx              xxx|
|ccx              x x|
|ccx              xxx|
----------------------
""", 20, 20)

        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testFillComplexArea() {
        testCanvas = createTestCanvas()
        val areaFiller = AreaFillerHelper('c', testCanvas.canvasArray)
        areaFiller.fillIn(5,1)
        val resultCanvas = readCanvas(
"""----------------------
|  xccccccccccccccccc|
|  xcxxxxxxcccccccccc|
|  xccccccccccccccccc|
|  xccccccccccccccccc|
|  xccccccccccccccccc|
|  xccccccccccccccccc|
|  xccccccccccccccccc|
|  xccccxxxxxxxxccccc|
|  xccccx      xccccc|
|  xccccx      xccccc|
|  xccccx      xccccc|
|  xccccx      xccccc|
|  xccccx      xccccc|
|  xccccx      xccccc|
|  xccccxxxxxxxxccccc|
|  xccccccccccccccccc|
|  xccccccccccccccccc|
|  xccccccccccccccxxx|
|  xccccccccccccccx x|
|  xccccccccccccccxxx|
----------------------
""", 20, 20)

        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testFillInRectangle() {
        testCanvas = createTestCanvas()
        val areaFiller = AreaFillerHelper('c', testCanvas.canvasArray)
        areaFiller.fillIn(19,19)
        areaFiller.fillIn(12,12)
        val resultCanvas = readCanvas(
"""----------------------
|  x                 |
|  x xxxxxx          |
|  x                 |
|  x                 |
|  x                 |
|  x                 |
|  x                 |
|  x    xxxxxxxx     |
|  x    xccccccx     |
|  x    xccccccx     |
|  x    xccccccx     |
|  x    xccccccx     |
|  x    xccccccx     |
|  x    xccccccx     |
|  x    xxxxxxxx     |
|  x                 |
|  x                 |
|  x              xxx|
|  x              xcx|
|  x              xxx|
----------------------
""", 20, 20)
    assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testFillRectangleOutline() {
        testCanvas = createTestCanvas()
        val areaFiller = AreaFillerHelper('c', testCanvas.canvasArray)
        areaFiller.fillIn(8,8)
        val resultCanvas = readCanvas(
"""----------------------
|  x                 |
|  x xxxxxx          |
|  x                 |
|  x                 |
|  x                 |
|  x                 |
|  x                 |
|  x    cccccccc     |
|  x    c      c     |
|  x    c      c     |
|  x    c      c     |
|  x    c      c     |
|  x    c      c     |
|  x    c      c     |
|  x    cccccccc     |
|  x                 |
|  x                 |
|  x              xxx|
|  x              x x|
|  x              xxx|
----------------------
""", 20, 20)
        assertEquals(resultCanvas, testCanvas)
    }
}