
fun createTestCanvas() : Canvas {
    val canvas = Canvas(20,20)
    canvas.drawHorizontalLine(2, 5, 10)
    canvas.drawVerticalLine(3, 1, 20)
    canvas.drawRectangle(8,8, 15, 15)
    canvas.drawRectangle(18, 18, 20, 20)
    return canvas
}
/*Test Canvas looks like this:
----------------------
|  x                 |
|  x xxxxxx          |
|  x                 |
|  x                 |
|  x                 |
|  x                 |
|  x                 |
|  x    xxxxxxxx     |
|  x    x      x     |
|  x    x      x     |
|  x    x      x     |
|  x    x      x     |
|  x    x      x     |
|  x    x      x     |
|  x    xxxxxxxx     |
|  x                 |
|  x                 |
|  x              xxx|
|  x              x x|
|  x              xxx|
----------------------
*/

fun readCanvas(input: String, w: Int, h: Int) : Canvas {
    val canvas = Canvas(w, h)
    val sanitizedInput = input.trimIndent().replace("\n", "")
    for (i in 0..h+1) {
        for (j in 0..w+1) {
            val index = i*(w+2) + j
            canvas.canvasArray[i][j] = sanitizedInput[index]
        }
    }
    return canvas
}
