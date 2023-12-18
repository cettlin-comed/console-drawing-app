package inputValidatorTests

import InputValidator
import exceptions.InputValueException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RectangleInputValidatorTest {

    val testInputValidator = InputValidator(20, 10)

    @Test
    fun testValidRectangleInput() {
        val inputArgs = listOf("1", "1", "20", "10")
        val args = testInputValidator.parseAndValidateInput("R", inputArgs)
        assertEquals(args[0], Integer.parseInt(inputArgs[0]))
        assertEquals(args[1], Integer.parseInt(inputArgs[1]))
        assertEquals(args[2], Integer.parseInt(inputArgs[2]))
        assertEquals(args[3], Integer.parseInt(inputArgs[3]))
    }

    @Test
    fun testValidInvertedRectangleInput() {
        val inputArgs = listOf("20", "10", "1", "1")
        val args = testInputValidator.parseAndValidateInput("R", inputArgs)
        assertEquals(args[0], Integer.parseInt(inputArgs[0]))
        assertEquals(args[1], Integer.parseInt(inputArgs[1]))
        assertEquals(args[2], Integer.parseInt(inputArgs[2]))
        assertEquals(args[3], Integer.parseInt(inputArgs[3]))
    }

    @Test
    fun testInvalidRectangleInput() {
        val inputArgs = listOf("1", "0", "3", "-1")
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateInput("R", inputArgs)
        }
    }

    @Test
    fun testMissingRectangleInput() {
        val inputArgs = listOf("1", "2", "3")
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateInput("R", inputArgs)
        }
    }

    @Test
    fun testOutOfBoundsRectangleInput() {
        val inputArgs = listOf("1", "2", "1", "100")
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateInput("R", inputArgs)
        }
    }
}