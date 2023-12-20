package commands

import Canvas

abstract class DrawCommand(val canvas: Canvas) {

    abstract fun execute(args: List<String>)

    abstract fun validate(args: List<String>) : List<Int>
}