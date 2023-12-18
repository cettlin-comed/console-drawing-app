import enums.Command
import exceptions.InputValueException
import kotlin.system.exitProcess

class CanvasController(
    var canvas: Canvas = Canvas(0,0),
    var inputValidator: InputValidator = InputValidator(0,0)) {

    fun execute(c: String, args: List<String>) {
        when (c) {
            Command.Q.name -> exitProgramm()
            Command.C.name -> executeCanvasCommand(args)
            Command.L.name -> executeLineCommand(args)
            Command.R.name -> executeRectangleCommand(args)
            Command.B.name -> executeFillCommand(args)
            else -> throw InputValueException("Input must start with one of the following letters: ${Command.entries}")
        }
    }

    fun printCanvas() {
        canvas.print()
    }

    private fun exitProgramm() {
        println("You are now exiting the program. Thanks for drawing!")
        exitProcess(0)
    }

    private fun executeCanvasCommand(args: List<String>) {
        val intArgs = inputValidator.parseAndValidateCanvasInput(args)
        val w = intArgs[0]
        val h = intArgs[1]
        canvas = Canvas(w,h)
        inputValidator.setCanvasSize(w,h)
    }

    private fun executeLineCommand(args: List<String>) {
        val intArgs = inputValidator.parseAndValidateLineInput(args)
        canvas.drawLine(intArgs[0], intArgs[1], intArgs[2], intArgs[3])
    }

    private fun executeRectangleCommand(args: List<String>) {
        val intArgs = inputValidator.parseAndValidateRectangleInput(args)
        canvas.drawRectangle(intArgs[0], intArgs[1], intArgs[2], intArgs[3])
    }

    private fun executeFillCommand(args: List<String>) {
        val intArgs = inputValidator.parseAndValidateFillInput(args)
        canvas.fillArea(intArgs[0], intArgs[1], args[2][0])
    }
}