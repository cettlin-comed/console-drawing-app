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
        val inputArgs = listOf("1", "2", "c")
        val args = FillCommand.validate(testCanvas, inputArgs)
        assertEquals(args[0], Integer.parseInt(inputArgs[0]))
        assertEquals(args[1], Integer.parseInt(inputArgs[1]))
        assertEquals(args.size, 2)
        assertEquals('c', inputArgs[2][0])
        FillCommand.execute(testCanvas, inputArgs)
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
        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testInvalidFillInput() {
        val inputArgs = listOf("1", "2", "3", "4")
        assertFailsWith<InputValueException> {
            FillCommand.execute(testCanvas, inputArgs)
        }
    }

    @Test
    fun testMissingFillInput() {
        val inputArgs = listOf("1", "2")
        assertFailsWith<InputValueException> {
            FillCommand.execute(testCanvas, inputArgs)
        }
        val inputArgs2 = listOf("1", "c")
        assertFailsWith<InputValueException> {
            FillCommand.execute(testCanvas, inputArgs2)
        }
    }

    @Test
    fun testOutOfBoundsFillInput() {
        val inputArgs = listOf("1", "200", "c")
        assertFailsWith<InputValueException> {
            FillCommand.execute(testCanvas, inputArgs)
        }
        val inputArgs2 = listOf("0", "1", "c")
        assertFailsWith<InputValueException> {
            FillCommand.execute(testCanvas, inputArgs2)
        }
    }

    @Test
    fun testStringFillInput() {
        val inputArgs = listOf("1", "2", "test")
        assertFailsWith<InputValueException> {
            FillCommand.execute(testCanvas, inputArgs)
        }
    }
}