package commands

import Canvas
import exceptions.InputValueException

class FillCommand(canvas: Canvas) : DrawCommand(canvas) {
    override fun execute(args: List<String>) {
        val intArgs = validate(args)
        canvas.fillArea(intArgs[0], intArgs[1], args[2][0])
    }

    override fun validate(args: List<String>): List<Int> {
        if (args.size != 3) throw InputValueException("The command expects 3 arguments, but " + args.size + " were provided.")
        val intArgs = mutableListOf<Int>()
        intArgs.add(Integer.parseInt(args[0]))
        intArgs.add(Integer.parseInt(args[1]))
        if (!canvas.isInsideWidth(intArgs[0])) throw InputValueException("The given x value is outside the canvas width (current: ${canvas.w}), please enter values within the bounds of the current canvas or create a new canvas")
        if (!canvas.isInsideHeight(intArgs[1])) throw InputValueException("The given y value is outside the canvas height (current: ${canvas.h}), please enter values within the bounds of the current canvas or create a new canvas")
        if (args[2].length != 1) throw InputValueException("The B command's last argument must be a single character")
        return intArgs
    }
}