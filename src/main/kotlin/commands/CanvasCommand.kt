package commands

import Canvas
import exceptions.InputValueException

class CanvasCommand : DrawCommand {

    companion object {
        fun execute(canvas: Canvas, args: List<String>) {
            val intArgs = validate(args)
            val w = intArgs[0]
            val h = intArgs[1]
            canvas.drawCanvas(w, h)
        }

        fun validate(args: List<String>) : List<Int> {
            if (args.size != 2) throw InputValueException("The C command expects 2 integer arguments, but " + args.size + " were provided.")
            val intArgs = args.map {
                val intArg = Integer.parseInt(it)
                if (intArg < 1) { throw InputValueException("Canvas must have width and height of at least 1.") }
                intArg
            }
            return intArgs
        }
    }
}