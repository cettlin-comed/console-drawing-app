package inputValidatorTests

import InputValidator
import exceptions.InputValueException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LineInputValidatorTest {

    val testInputValidator = InputValidator(20, 10)

    @Test
    fun testValidLineInput() {
        val inputArgs = listOf("1", "1", "1", "10")
        val args = testInputValidator.parseAndValidateInput("L", inputArgs)
        assertEquals(args[0], Integer.parseInt(inputArgs[0]))
        assertEquals(args[1], Integer.parseInt(inputArgs[1]))
        assertEquals(args[2], Integer.parseInt(inputArgs[2]))
        assertEquals(args[3], Integer.parseInt(inputArgs[3]))
    }

    @Test
    fun testValidInvertedLineInput() {
        val inputArgs = listOf("20", "10", "1", "10")
        val args = testInputValidator.parseAndValidateInput("L", inputArgs)
        assertEquals(args[0], Integer.parseInt(inputArgs[0]))
        assertEquals(args[1], Integer.parseInt(inputArgs[1]))
        assertEquals(args[2], Integer.parseInt(inputArgs[2]))
        assertEquals(args[3], Integer.parseInt(inputArgs[3]))
    }

    @Test
    fun testInvalidLineInput() {
        val inputArgs = listOf("1", "2", "3", "4")
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateInput("L", inputArgs)
        }
    }

    @Test
    fun testMissingLineInput() {
        val inputArgs = listOf("1", "2", "3")
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateInput("L", inputArgs)
        }
    }

    @Test
    fun testOutOfBoundsLineInput() {
        val inputArgs = listOf("1", "2", "1", "100")
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateInput("L", inputArgs)
        }
    }
}