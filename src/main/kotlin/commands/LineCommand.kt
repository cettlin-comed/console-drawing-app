package commands

import Canvas
import exceptions.InputValueException

class LineCommand(canvas: Canvas) : DrawCommand(canvas) {
    override fun execute(args: List<String>) {
        val intArgs = validate(args)
        canvas.drawLine(intArgs[0], intArgs[1], intArgs[2], intArgs[3])
    }

    override fun validate(args: List<String>): List<Int> {
        if (args.size != 4) throw InputValueException("The command expects 4 integer arguments, but ${args.size} were provided.")
        val intArgs = args.map { Integer.parseInt(it) }
        if (intArgs[0] != intArgs[2] && intArgs[1] != intArgs[3]) {
            throw InputValueException(String.format("Cannot draw a horizontal or vertical line from (%d, %d) to (%d, %d). \nHint: For a line to be drawn, either x1 and x2 or y1 and y2 must be identical.", intArgs[0], intArgs[2], intArgs[1], intArgs[3]))
        }
        if (!canvas.isInsideWidth(intArgs[0]) || !canvas.isInsideWidth(intArgs[2])) throw InputValueException("A given x value is outside the canvas width (current: ${canvas.w}), please enter values within the bounds of the current canvas or create a new canvas")
        if (!canvas.isInsideHeight(intArgs[1]) || !canvas.isInsideHeight(intArgs[3])) throw InputValueException("A given y value is outside the canvas height (current: ${canvas.h}), please enter values within the bounds of the current canvas or create a new canvas")
        return intArgs
    }
}