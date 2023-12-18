package inputValidatorTests

import InputValidator
import exceptions.InputValueException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FillInputValidatorTest {

    val testInputValidator = InputValidator(20, 10)

    @Test
    fun testValidFillInput() {
        val inputArgs = listOf("1", "2", "c")
        val args = testInputValidator.parseAndValidateInput("B", inputArgs)
        assertEquals(args[0], Integer.parseInt(inputArgs[0]))
        assertEquals(args[1], Integer.parseInt(inputArgs[1]))
        assertEquals(args.size, 2)
        assertEquals('c', inputArgs[2][0])
    }

    @Test
    fun testInvalidFillInput() {
        val inputArgs = listOf("1", "2", "3", "4")
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateInput("B", inputArgs)
        }
    }

    @Test
    fun testMissingFillInput() {
        val inputArgs = listOf("1", "2")
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateInput("B", inputArgs)
        }
        val inputArgs2 = listOf("1", "c")
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateInput("B", inputArgs2)
        }
    }

    @Test
    fun testOutOfBoundsFillInput() {
        val inputArgs = listOf("1", "2", "1", "100")
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateInput("B", inputArgs)
        }
    }

    @Test
    fun testStringFillInput() {
        val inputArgs = listOf("1", "2", "test")
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateInput("B", inputArgs)
        }
    }
}