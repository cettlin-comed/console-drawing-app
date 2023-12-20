import enums.Command
import exceptions.InputValueException


class AppController(val canvas: Canvas = Canvas(0,0)) {

    fun execute(command: String, args: List<String>) {
        Command.entries.find { it.name == command }?.execute(canvas, args)
            ?: throw InputValueException("Input must start with one of the following letters: ${Command.entries}")
    }

    fun printCanvas() {
        canvas.print()
    }


}