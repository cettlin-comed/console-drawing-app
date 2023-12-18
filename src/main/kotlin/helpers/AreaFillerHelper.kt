package helpers

class AreaFillerHelper(private val newColor: Char,
                       private val canvas: Array<CharArray>) {

    private val w = canvas[0].size
    private val h = canvas.size
    private val visited: Array<Array<Boolean>> = Array(h) { Array(w) { false } }
    private var previous = ' '

    fun fillIn(x: Int, y: Int) {
        previous = canvas[y][x]
        visit(x,y)
    }

    private fun visit(x: Int, y: Int) {
        if (visited[y][x]) return
        visited[y][x] = true
        if (canvas[y][x] == previous) {
            canvas[y][x] = newColor
            if (x > 1) visit(x-1, y)
            if (y > 1) visit(x, y-1)
            if (x < w) visit(x+1, y)
            if (y < h) visit(x, y+1)
        }
    }
}