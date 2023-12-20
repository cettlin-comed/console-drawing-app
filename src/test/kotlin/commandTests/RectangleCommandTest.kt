package commandTests

import Canvas
import commands.RectangleCommand
import exceptions.InputValueException
import readCanvas
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RectangleCommandTest {

    var testCanvas = Canvas(20, 10)

    @BeforeTest
    fun resetCanvas() {
        testCanvas = Canvas(20, 10)
    }

    @Test
    fun testValidRectangleInput() {
        val command = RectangleCommand(testCanvas)
        val inputArgs = listOf("1", "1", "20", "10")
        val args = command.validate(inputArgs)
        assertEquals(args[0], Integer.parseInt(inputArgs[0]))
        assertEquals(args[1], Integer.parseInt(inputArgs[1]))
        assertEquals(args[2], Integer.parseInt(inputArgs[2]))
        assertEquals(args[3], Integer.parseInt(inputArgs[3]))
        command.execute(inputArgs)
        val resultCanvas = readCanvas(
            """
----------------------
|xxxxxxxxxxxxxxxxxxxx|
|x                  x|
|x                  x|
|x                  x|
|x                  x|
|x                  x|
|x                  x|
|x                  x|
|x                  x|
|xxxxxxxxxxxxxxxxxxxx|
----------------------
""", 20, 10
        )
        assertEquals(resultCanvas, command.canvas)
    }

    @Test
    fun testValidInvertedRectangleInput() {
        val command = RectangleCommand(testCanvas)
        val inputArgs = listOf("20", "10", "1", "1")
        val args = command.validate(inputArgs)
        assertEquals(args[0], Integer.parseInt(inputArgs[0]))
        assertEquals(args[1], Integer.parseInt(inputArgs[1]))
        assertEquals(args[2], Integer.parseInt(inputArgs[2]))
        assertEquals(args[3], Integer.parseInt(inputArgs[3]))
        command.execute(inputArgs)
        val resultCanvas = readCanvas(
            """
----------------------
|xxxxxxxxxxxxxxxxxxxx|
|x                  x|
|x                  x|
|x                  x|
|x                  x|
|x                  x|
|x                  x|
|x                  x|
|x                  x|
|xxxxxxxxxxxxxxxxxxxx|
----------------------
""", 20, 10
        )
        assertEquals(resultCanvas, command.canvas)
    }

    @Test
    fun testInvalidRectangleInput() {
        val command = RectangleCommand(testCanvas)
        val inputArgs = listOf("1", "0", "3", "-1")
        assertFailsWith<InputValueException> {
            command.execute(inputArgs)
        }
    }

    @Test
    fun testMissingRectangleInput() {
        val command = RectangleCommand(testCanvas)
        val inputArgs = listOf("1", "2", "3")
        assertFailsWith<InputValueException> {
            command.execute(inputArgs)
        }
    }

    @Test
    fun testOutOfBoundsRectangleInput() {
        val command = RectangleCommand(testCanvas)
        val inputArgs = listOf("1", "2", "1", "100")
        assertFailsWith<InputValueException> {
            command.execute(inputArgs)
        }
    }
}