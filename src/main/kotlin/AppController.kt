import commands.CanvasCommand
import commands.FillCommand
import commands.LineCommand
import commands.RectangleCommand
import enums.Command
import exceptions.InputValueException
import kotlin.system.exitProcess

class AppController(var canvas: Canvas = Canvas(0,0)) {

    fun execute(command: String, args: List<String>) {
        when (command) {
            Command.Q.name -> exitProgram()
            Command.C.name -> canvas = CanvasCommand().execute(args)
            Command.L.name -> LineCommand(canvas).execute(args)
            Command.R.name -> RectangleCommand(canvas).execute(args)
            Command.B.name -> FillCommand(canvas).execute(args)
            else -> throw InputValueException("Input must start with one of the following letters: ${Command.entries}")
        }
    }

    fun printCanvas() {
        canvas.print()
    }

    private fun exitProgram() : Canvas {
        println("You are now exiting the program. Thanks for drawing!")
        exitProcess(0)
    }

}