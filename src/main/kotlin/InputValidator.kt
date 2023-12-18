import exceptions.InputValueException
import java.lang.Integer.parseInt

class InputValidator(private var w: Int, private var h: Int) {

    fun parseAndValidateCanvasInput(args: List<String>) : List<Int> {
        if (args.size != 2) throw InputValueException("The C command expects 2 integer arguments, but " + args.size + " were provided.")
        val intArgs = args.map {
                val intArg = parseInt(it)
                if (intArg < 1) { throw InputValueException("Canvas must have width and height of at least 1.") }
                intArg
        }
        return intArgs
    }

    fun parseAndValidateLineInput(args: List<String>) : List<Int> {
        if (args.size != 4) throw InputValueException("The command expects 4 integer arguments, but ${args.size} were provided.")
        val intArgs = args.map { parseInt(it) }
        if (intArgs[0] != intArgs[2] && intArgs[1] != intArgs[3]) {
            throw InputValueException(String.format("Cannot draw a horizontal or vertical line from (%d, %d) to (%d, %d). \nHint: For a line to be drawn, either x1 and x2 or y1 and y2 must be identical.", intArgs[0], intArgs[2], intArgs[1], intArgs[3]))
        }
        if (outsideCanvasWidth(intArgs[0]) || outsideCanvasWidth(intArgs[2])) throw InputValueException("A given x value is outside the canvas width (current: $w), please enter values within the bounds of the current canvas or create a new canvas")
        if (outsideCanvasHeight(intArgs[1]) || outsideCanvasHeight(intArgs[3])) throw InputValueException("A given y value is outside the canvas height (current: $h), please enter values within the bounds of the current canvas or create a new canvas")
        return intArgs
    }

    fun parseAndValidateRectangleInput(args: List<String>) : List<Int> {
        if (args.size != 4) throw InputValueException("The command expects 4 integer arguments, but ${args.size} were provided.")
        val intArgs = args.map { parseInt(it) }
        if (outsideCanvasWidth(intArgs[0]) || outsideCanvasWidth(intArgs[2])) throw InputValueException("A given x value is outside the canvas width (current: $w), please enter values within the bounds of the current canvas or create a new canvas")
        if (outsideCanvasHeight(intArgs[1]) || outsideCanvasHeight(intArgs[3])) throw InputValueException("A given y value is outside the canvas height (current: $h), please enter values within the bounds of the current canvas or create a new canvas")
        return intArgs
    }

    fun parseAndValidateFillInput(args: List<String>) : List<Int> {
        if (args.size != 3) throw InputValueException("The command expects 3 arguments, but " + args.size + " were provided.")
        val intArgs = mutableListOf<Int>()
        intArgs.add(parseInt(args[0]))
        intArgs.add(parseInt(args[1]))
        if (outsideCanvasWidth(intArgs[0])) throw InputValueException("The given x value is outside the canvas width (current: $w), please enter values within the bounds of the current canvas or create a new canvas")
        if (outsideCanvasHeight(intArgs[1])) throw InputValueException("The given y value is outside the canvas height (current: $h), please enter values within the bounds of the current canvas or create a new canvas")
        if (args[2].length != 1) throw InputValueException("The B command's last argument must be a single character")
        return intArgs
    }

    private fun outsideCanvasWidth(intArg: Int) : Boolean {
            return intArg < 1 || intArg > w
    }

    private fun outsideCanvasHeight(intArg: Int) : Boolean {
        return intArg < 1 || intArg > h
    }

    fun setCanvasSize(w: Int, h: Int) {
        this.w = w
        this.h = h
    }
}