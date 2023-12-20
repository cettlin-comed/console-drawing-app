

class Canvas(val w: Int, val h: Int) {

    private val canvasArray: Array<CharArray> = initializeCanvas(w, h)

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
            setPixel(x, i, 'x')
        }
    }

    fun drawHorizontalLine(y: Int, x1: Int, x2: Int) {
        val start = x1.coerceAtMost(x2)
        val end = x1.coerceAtLeast(x2)
        for (i in start..end) {
            setPixel(i, y, 'x')
        }
    }

    fun drawRectangle(x1: Int, y1: Int, x2: Int, y2: Int) {
        drawVerticalLine(x1, y1, y2)
        drawVerticalLine(x2, y1, y2)
        drawHorizontalLine(y1, x1, x2)
        drawHorizontalLine(y2, x1, x2)
    }

    fun fillArea(x: Int, y: Int, newColor: Char) {
        val previous = canvasArray[y][x]
        val q: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
        q.add(Pair(x,y))
        while (!q.isEmpty()) {
            val p = q.removeFirst()
            if (getPixel(p.first, p.second) == previous) {
                setPixel(p.first, p.second, newColor)
                if (p.second > 1) q.add(Pair(p.first, p.second-1))
                if (p.first > 1) q.add(Pair(p.first-1, p.second))
                if (p.first < w) q.add(Pair(p.first+1, p.second))
                if (p.second < h) q.add(Pair(p.first, p.second+1))
            }
        }
    }

    fun getPixel(x: Int, y: Int) : Char = canvasArray[y][x]

    fun setPixel(x: Int, y: Int, c: Char) {
        canvasArray[y][x] = c
    }

    fun isInsideWidth(x: Int) : Boolean {
        return x in 1..w
    }

    fun isInsideHeight(y: Int) : Boolean {
        return y in 1..h
    }

    override fun equals(other: Any?): Boolean =
        (other is Canvas) && canvasArray.contentDeepEquals(other.canvasArray)

    override fun toString(): String {
       var s = ""
        for (line in canvasArray) {
            s += String(line) + "\n"
        }
        return s
    }
}