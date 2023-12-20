package commandTests

import Canvas
import commands.FillCommand
import exceptions.InputValueException
import readCanvas
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FillCommandTest {

    var testCanvas = Canvas(20, 10)

    @BeforeTest
    fun resetCanvas() {
        testCanvas = Canvas(20, 10)
    }

    @Test
    fun testValidFillInput() {
        val command = FillCommand(testCanvas)
        val inputArgs = listOf("1", "2", "c")
        val args = command.validate(inputArgs)
        assertEquals(args[0], Integer.parseInt(inputArgs[0]))
        assertEquals(args[1], Integer.parseInt(inputArgs[1]))
        assertEquals(args.size, 2)
        assertEquals('c', inputArgs[2][0])
        command.execute(inputArgs)
        val resultCanvas = readCanvas(
            """
----------------------
|cccccccccccccccccccc|
|cccccccccccccccccccc|
|cccccccccccccccccccc|
|cccccccccccccccccccc|
|cccccccccccccccccccc|
|cccccccccccccccccccc|
|cccccccccccccccccccc|
|cccccccccccccccccccc|
|cccccccccccccccccccc|
|cccccccccccccccccccc|
----------------------
""", 20, 10
        )
        assertEquals(resultCanvas, command.canvas)
    }

    @Test
    fun testInvalidFillInput() {
        val command = FillCommand(testCanvas)
        val inputArgs = listOf("1", "2", "3", "4")
        assertFailsWith<InputValueException> {
            command.execute(inputArgs)
        }
    }

    @Test
    fun testMissingFillInput() {
        val command = FillCommand(testCanvas)
        val inputArgs = listOf("1", "2")
        assertFailsWith<InputValueException> {
            command.execute(inputArgs)
        }
        val inputArgs2 = listOf("1", "c")
        assertFailsWith<InputValueException> {
            command.execute(inputArgs2)
        }
    }

    @Test
    fun testOutOfBoundsFillInput() {
        val command = FillCommand(testCanvas)
        val inputArgs = listOf("1", "2", "1", "100")
        assertFailsWith<InputValueException> {
            command.execute(inputArgs)
        }
    }

    @Test
    fun testStringFillInput() {
        val command = FillCommand(testCanvas)
        val inputArgs = listOf("1", "2", "test")
        assertFailsWith<InputValueException> {
            command.execute(inputArgs)
        }
    }
}