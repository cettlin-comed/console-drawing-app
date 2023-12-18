package inputValidatorTests

import InputValidator
import exceptions.InputValueException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CanvasInputValidatonTest {

    val testInputValidator = InputValidator(20, 10)

    @Test
    fun testValidCanvasInput() {
        val args = testInputValidator.parseAndValidateCanvasInput(listOf("5", "10"))
        assertEquals(args[0], 5)
        assertEquals(args[1], 10)
    }

    @Test
    fun testInvalidCanvasInput() {
        assertFailsWith<InputValueException>(
            block = { testInputValidator.parseAndValidateCanvasInput(listOf("-1", "2"))}
        )
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateCanvasInput(listOf("2", "0"))
        }
    }

    @Test
    fun testMissingCanvasInput() {
        assertFailsWith<InputValueException> {
            testInputValidator.parseAndValidateCanvasInput(listOf("1"))
        }
    }
}