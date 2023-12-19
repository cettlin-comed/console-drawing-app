package CanvasTests

import Canvas
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import readCanvas
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
        val resultCanvas = readCanvas(
            """------------
|          |
| x        |
| x        |
| x        |
| x        |
| x        |
| x        |
| x        |
| x        |
|          |
------------
""", 10, 10
        )

        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testDrawHorizontalLine() {
        testCanvas = Canvas(emptyCanvasW, emptyCanvasH)
        val x1 = 2
        val x2 = emptyCanvasW - 1
        val y1 = 2
        val y2 = 2
        testCanvas.drawLine(x1,y1,x2,y2)
        val resultCanvas = readCanvas(
"""------------
|          |
| xxxxxxxx |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
------------
""", 10, 10
        )

        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testDrawMultipleLines() {
        testCanvas = Canvas(emptyCanvasW, emptyCanvasH)
        testCanvas.drawLine(2, 2, 4, 2)
        testCanvas.drawLine(7, 7, 9, 7)
        testCanvas.drawLine(3, 3, 3, 10)
        testCanvas.drawLine(3, 8, 10, 8)
        val resultCanvas = readCanvas(
"""------------
|          |
| xxx      |
|  x       |
|  x       |
|  x       |
|  x       |
|  x   xxx |
|  xxxxxxxx|
|  x       |
|  x       |
------------
""", 10, 10
        )

        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testDrawRectangle() {
        testCanvas = Canvas(emptyCanvasW, emptyCanvasH)
        val x1 = 2
        val x2 = emptyCanvasW - 1
        val y1 = 2
        val y2 = emptyCanvasH -1
        testCanvas.drawRectangle(x1,y1,x2,y2)
        val resultCanvas = readCanvas(
"""------------
|          |
| xxxxxxxx |
| x      x |
| x      x |
| x      x |
| x      x |
| x      x |
| x      x |
| xxxxxxxx |
|          |
------------
""", 10, 10
        )

        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testDrawMultipleRectangles() {
        testCanvas = Canvas(emptyCanvasW, emptyCanvasH)
        testCanvas.drawRectangle(2,3, 7,5)
        testCanvas.drawRectangle(1,1,1,1)
        testCanvas.drawRectangle(4, 1, 9, 9)
        val resultCanvas = readCanvas(
"""------------
|x  xxxxxx |
|   x    x |
| xxxxxx x |
| x x  x x |
| xxxxxx x |
|   x    x |
|   x    x |
|   x    x |
|   xxxxxx |
|          |
------------
""", 10, 10
        )

        assertEquals(resultCanvas, testCanvas)
    }


}