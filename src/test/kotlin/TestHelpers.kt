
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