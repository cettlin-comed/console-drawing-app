package enums

import Canvas
import commands.CanvasCommand
import commands.FillCommand
import commands.LineCommand
import commands.RectangleCommand
import kotlin.system.exitProcess

enum class Command {
    C {
        override fun execute(canvas: Canvas, args: List<String>) {
            CanvasCommand.execute(canvas, args)
        }
    },
    Q {
        override fun execute(canvas: Canvas, args: List<String>) {
            println("You are now exiting the program. Thanks for drawing!")
            exitProcess(0)
        }
    },
    L {
        override fun execute(canvas: Canvas, args: List<String>) {
            LineCommand.execute(canvas, args)
        }
    },
    R {
        override fun execute(canvas: Canvas, args: List<String>) {
            RectangleCommand.execute(canvas, args)
        }
    },
    B {
        override fun execute(canvas: Canvas, args: List<String>) {
            FillCommand.execute(canvas, args)
        }
    };

    abstract fun execute(canvas: Canvas, args: List<String>)

}