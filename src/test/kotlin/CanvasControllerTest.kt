import exceptions.InputValueException
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.*

class CanvasControllerTest {

    var testController = CanvasController()
    val defaultW = 10
    val defaultH = 10

    @BeforeTest
    fun resetController() {
        testController = CanvasController()
        testController.execute("C", listOf(defaultW.toString(), defaultH.toString()))
    }

    @AfterTest
    fun printCanvas() {
        testController.printCanvas()
    }

    @Test
    fun testUnknownCommand() {
        assertFailsWith<InputValueException> {
            testController.execute("S", emptyList())
        }
        val resultCanvas = readCanvas(
            """------------
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
------------
""", 10, 10
        )
        assertEquals(resultCanvas, testController.canvas)
    }

    @Test
    fun testCCommand() {
        val args = listOf("5", "6")
        testController.execute("C", args)

        val resultCanvas = readCanvas(
            """-------
|     |
|     |
|     |
|     |
|     |
|     |
-------
""", 5, 6
        )
        assertEquals(resultCanvas, testController.canvas)
    }

    @Test
    fun testInvalidCCommand() {
        val args = listOf("-1", "b")
        assertFailsWith<InputValueException>(
            block = { testController.execute("C", args) }
        )

        val resultCanvas = readCanvas(
            """------------
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
------------
""", 10, 10
        )
        assertEquals(resultCanvas, testController.canvas)
    }

    @Test
    fun testLCommand() {
        val x1 = 1
        val y1 = 1
        val x2 = 1
        val y2 = defaultH
        val args = listOf(x1.toString(), y1.toString(), x2.toString(), y2.toString())
        testController.execute("L", args)

        val resultCanvas = readCanvas(
"""------------
|x         |
|x         |
|x         |
|x         |
|x         |
|x         |
|x         |
|x         |
|x         |
|x         |
------------
""", 10, 10
        )
        assertEquals(resultCanvas, testController.canvas)
    }

    @Test
    fun testInvalidLCommand() {
        val x1 = 1
        val y1 = 2
        val x2 = 3
        val y2 = 4
        val args = listOf(x1.toString(), y1.toString(), x2.toString(), y2.toString())
        assertFailsWith<InputValueException>(
            block = { testController.execute("L", args) }
        )
        val resultCanvas = readCanvas(
            """------------
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
------------
""", 10, 10
        )
        assertEquals(resultCanvas, testController.canvas)
    }

    @Test
    fun testRCommand() {
        val x1 = 2
        val y1 = 2
        val x2 = defaultW-2
        val y2 = defaultH-2
        val args = listOf(x1.toString(), y1.toString(), x2.toString(), y2.toString())
        testController.execute("R", args)

        val resultCanvas = readCanvas(
"""------------
|          |
| xxxxxxx  |
| x     x  |
| x     x  |
| x     x  |
| x     x  |
| x     x  |
| xxxxxxx  |
|          |
|          |
------------
""", 10, 10
        )
        assertEquals(resultCanvas, testController.canvas)
    }

    @Test
    fun testInvalidRCommand() {
        val x1 = 0
        val y1 = -2
        val x2 = -5
        val y2 = 2
        val args = listOf(x1.toString(), y1.toString(), x2.toString(), y2.toString())
        assertFailsWith<InputValueException>(
            block = { testController.execute("R", args) }
        )
        val resultCanvas = readCanvas(
            """------------
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
------------
""", 10, 10
        )
        assertEquals(resultCanvas, testController.canvas)

    }

    @Test
    fun testBCommand() {
        val x = 5
        val y = 5
        val c = "c"
        val args = listOf(x.toString(), y.toString(), c)
        testController.execute("B", args)

        val resultCanvas = readCanvas(
            """------------
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
|cccccccccc|
------------
""", 10, 10
        )
        assertEquals(resultCanvas, testController.canvas)

    }

    @Test
    fun testInvalidBCommand() {
        val x = 5
        val y = 5
        val c = "test"
        val args = listOf(x.toString(), y.toString(), c)
        assertFailsWith<InputValueException>(
            block = { testController.execute("B", args) }
        )

        val resultCanvas = readCanvas(
            """------------
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
|          |
------------
""", 10, 10
        )
        assertEquals(resultCanvas, testController.canvas)
    }


}