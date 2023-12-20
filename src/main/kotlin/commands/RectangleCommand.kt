package commands

import Canvas
import exceptions.InputValueException

class RectangleCommand : DrawCommand {

    companion object {
        fun execute(canvas: Canvas, args: List<String>) {
            val intArgs = validate(canvas, args)
            canvas.drawRectangle(intArgs[0], intArgs[1], intArgs[2], intArgs[3])
        }

        fun validate(canvas: Canvas, args: List<String>): List<Int> {
            if (args.size != 4) throw InputValueException("The command expects 4 integer arguments, but ${args.size} were provided.")
            val intArgs = args.map { Integer.parseInt(it) }
            if (!canvas.isInsideWidth(intArgs[0]) || !canvas.isInsideWidth(intArgs[2])) throw InputValueException("A given x value is outside the canvas width (current: ${canvas.w}), please enter values within the bounds of the current canvas or create a new canvas")
            if (!canvas.isInsideHeight(intArgs[1]) || !canvas.isInsideHeight(intArgs[3])) throw InputValueException("A given y value is outside the canvas height (current: ${canvas.h}), please enter values within the bounds of the current canvas or create a new canvas")
            return intArgs
        }
    }
}