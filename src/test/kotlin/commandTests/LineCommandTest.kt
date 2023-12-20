package commandTests

import Canvas
import commands.LineCommand
import exceptions.InputValueException
import readCanvas
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LineCommandTest {

    var testCanvas = Canvas(20, 10)

    @BeforeTest
    fun resetCanvas() {
        testCanvas = Canvas(20, 10)
    }

    @Test
    fun testValidLineInput() {
        val inputArgs = listOf("1", "1", "1", "10")
        val args = LineCommand.validate(testCanvas, inputArgs)
        assertEquals(args[0], Integer.parseInt(inputArgs[0]))
        assertEquals(args[1], Integer.parseInt(inputArgs[1]))
        assertEquals(args[2], Integer.parseInt(inputArgs[2]))
        assertEquals(args[3], Integer.parseInt(inputArgs[3]))
        LineCommand.execute(testCanvas, inputArgs)
        val resultCanvas = readCanvas(
            """
----------------------
|x                   |
|x                   |
|x                   |
|x                   |
|x                   |
|x                   |
|x                   |
|x                   |
|x                   |
|x                   |
----------------------
""", 20,10
        )
        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testValidInvertedLineInput() {
        val inputArgs = listOf("20", "5", "1", "5")
        val args = LineCommand.validate(testCanvas, inputArgs)
        assertEquals(args[0], Integer.parseInt(inputArgs[0]))
        assertEquals(args[1], Integer.parseInt(inputArgs[1]))
        assertEquals(args[2], Integer.parseInt(inputArgs[2]))
        assertEquals(args[3], Integer.parseInt(inputArgs[3]))
        LineCommand.execute(testCanvas, inputArgs)
        val resultCanvas = readCanvas(
            """
----------------------
|                    |
|                    |
|                    |
|                    |
|xxxxxxxxxxxxxxxxxxxx|
|                    |
|                    |
|                    |
|                    |
|                    |
----------------------
""", 20, 10
        )
        assertEquals(resultCanvas, testCanvas)
    }

    @Test
    fun testInvalidLineInput() {
        val inputArgs = listOf("1", "2", "3", "4")
        assertFailsWith<InputValueException> {
            LineCommand.execute(testCanvas, inputArgs)
        }
    }

    @Test
    fun testMissingLineInput() {
        val inputArgs = listOf("1", "2", "3")
        assertFailsWith<InputValueException> {
            LineCommand.execute(testCanvas, inputArgs)
        }
    }

    @Test
    fun testOutOfBoundsLineInput() {
        val inputArgs = listOf("1", "2", "1", "100")
        assertFailsWith<InputValueException> {
            LineCommand.execute(testCanvas, inputArgs)
        }
    }
}