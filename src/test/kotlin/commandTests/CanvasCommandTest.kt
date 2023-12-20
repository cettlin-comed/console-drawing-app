package commandTests

import Canvas
import commands.CanvasCommand
import exceptions.InputValueException
import readCanvas
import kotlin.test.*

class CanvasCommandTest {

    var testCanvas = Canvas(20, 10)

    @BeforeTest
    fun resetCanvas() {
        testCanvas = Canvas(20, 10)
    }

    @Test
    fun testValidCanvasInput() {
        val inputArgs = listOf("5", "10")
        val args = CanvasCommand.validate(inputArgs)
        assertEquals(args[0], 5)
        assertEquals(args[1], 10)
        CanvasCommand.execute(testCanvas, inputArgs)
        val resultCanvas = readCanvas(
            """
-------
|     |
|     |
|     |
|     |
|     |
|     |
|     |
|     |
|     |
|     |
-------
""", 5, 10
        )
        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testInvalidCanvasInput() {
        assertFailsWith<InputValueException>(
            block = { CanvasCommand.execute(testCanvas, listOf("-1", "2"))}
        )
        assertFailsWith<InputValueException> {
            CanvasCommand.execute(testCanvas, listOf("2", "0"))
        }
    }

    @Test
    fun testMissingCanvasInput() {
        assertFailsWith<InputValueException> {
            CanvasCommand.execute(testCanvas, listOf("1"))
        }
    }
}