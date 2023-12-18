import helpers.AreaFillerHelper

class Canvas(w: Int, h: Int) {

    val canvasArray: Array<CharArray> = initializeCanvas(w, h)

    private fun initializeCanvas(w: Int, h: Int) : Array<CharArray> {
        val canvasArray = Array(h + 2) { CharArray(w + 2) { ' ' } }
        canvasArray[0] = CharArray(w + 2) { '-' }
        for (i in 1..h) {
            canvasArray[i][0] = '|'
            canvasArray[i][w + 1] = '|'
        }
        canvasArray[h + 1] = CharArray(w + 2) { '-' }
        return canvasArray
    }

    fun print() {
        for (line in canvasArray) {
            println(String(line))
        }
    }

    fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int) {
        if (x1 == x2) {
            drawVerticalLine(x1, y1, y2)
        } else if (y1 == y2) {
            drawHorizontalLine(y1, x1, x2)
        }
    }

    fun drawVerticalLine(x: Int, y1: Int, y2: Int) {
        val start = y1.coerceAtMost(y2)
        val end = y1.coerceAtLeast(y2)
        for (i in start..end) {
            canvasArray[i][x] = 'x'
        }
    }

    fun drawHorizontalLine(y: Int, x1: Int, x2: Int) {
        val start = x1.coerceAtMost(x2)
        val end = x1.coerceAtLeast(x2)
        for (i in start..end) {
            canvasArray[y][i] = 'x'
        }
    }

    fun drawRectangle(x1: Int, y1: Int, x2: Int, y2: Int) {
        drawVerticalLine(x1, y1, y2)
        drawVerticalLine(x2, y1, y2)
        drawHorizontalLine(y1, x1, x2)
        drawHorizontalLine(y2, x1, x2)
    }

    fun fillArea(x: Int, y: Int, newColor: Char) {
        val areaFiller = AreaFillerHelper(newColor, canvasArray)
        areaFiller.fillIn(x,y)
    }

}