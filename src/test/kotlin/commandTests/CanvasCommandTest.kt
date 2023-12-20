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
        val command = CanvasCommand()
        val inputArgs = listOf("5", "10")
        val args = command.validate(inputArgs)
        assertEquals(args[0], 5)
        assertEquals(args[1], 10)
        val computedCanvas = command.execute(inputArgs)
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
        assertEquals(resultCanvas, computedCanvas)
    }

    @Test
    fun testInvalidCanvasInput() {
        val command = CanvasCommand()
        assertFailsWith<InputValueException>(
            block = { command.execute(listOf("-1", "2"))}
        )
        assertFailsWith<InputValueException> {
            command.execute(listOf("2", "0"))
        }
    }

    @Test
    fun testMissingCanvasInput() {
        val command = CanvasCommand()
        assertFailsWith<InputValueException> {
            command.execute(listOf("1"))
        }
    }
}