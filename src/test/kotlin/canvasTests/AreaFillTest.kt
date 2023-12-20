package canvasTests

import Canvas
import createTestCanvas
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import readCanvas
import kotlin.test.AfterTest


class AreaFillTest {

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
        testCanvas.fillArea(1,1, 'c')
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
""", 10, 10
        )

        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testFillStraightArea() {
        testCanvas = createTestCanvas()
        testCanvas.fillArea(1,1, 'c')
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
""", 20, 20
        )

        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testFillComplexArea() {
        testCanvas = createTestCanvas()
        testCanvas.fillArea(5,1, 'c')
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
""", 20, 20
        )

        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testFillInRectangle() {
        testCanvas = createTestCanvas()
        testCanvas.fillArea(19,19, 'c')
        testCanvas.fillArea(12,12, 'c')
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
""", 20, 20
        )
    assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testFillRectangleOutline() {
        testCanvas = createTestCanvas()
        testCanvas.fillArea(8,8, 'c')
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
""", 20, 20
        )
        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun fillAlreadyColored() {
        testCanvas = createTestCanvas()
        testCanvas.fillArea(3,1, 'x')
        val resultCanvas = readCanvas(
"""
----------------------
|  x                 |
|  x xxxxxx          |
|  x                 |
|  x                 |
|  x                 |
|  x                 |
|  x                 |
|  x    xxxxxxxx     |
|  x    x      x     |
|  x    x      x     |
|  x    x      x     |
|  x    x      x     |
|  x    x      x     |
|  x    x      x     |
|  x    xxxxxxxx     |
|  x                 |
|  x                 |
|  x              xxx|
|  x              x x|
|  x              xxx|
----------------------
""", 20, 20
            )
            assertEquals(resultCanvas, testCanvas)

    }
}