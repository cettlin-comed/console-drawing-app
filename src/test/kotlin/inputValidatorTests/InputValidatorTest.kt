package inputValidatorTests

import InputValidator
import exceptions.InputValueException
import kotlin.test.assertFailsWith
import kotlin.test.Test
import kotlin.test.assertEquals

class InputValidatorTest {

    val testInputValidator = InputValidator(20, 10)

    @Test
    fun testEmptyInput() {
        assertFailsWith<InputValueException>(
            block = { testInputValidator.parseAndValidateInput("", emptyList()) }
        )
    }

    @Test
    fun testUnknownCommand() {
        assertFailsWith<InputValueException>(
            block = { testInputValidator.parseAndValidateInput("X", listOf("a")) }
        )
    }

    @Test
    fun testProgramExit() {
        val args = testInputValidator.parseAndValidateInput("Q", emptyList())
        assertEquals(args, emptyList())
    }

    @Test
    fun testProgramExitIgnoresInputs() {
        val args = testInputValidator.parseAndValidateInput("Q", listOf("a", "b", "c"))
        assertEquals(args, emptyList())
    }

}