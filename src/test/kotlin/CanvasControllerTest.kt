import exceptions.InputValueException
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.*

class CanvasControllerTest {

    var testController = CanvasController()
    val defaultW = 10
    val defaultH = 10
    val defaultSize = 12 * 12

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
        assertEquals(defaultSize, getCanvasSize())
    }

    @Test
    fun testCCommand() {
        val args = listOf("5", "6")
        testController.execute("C", args)

        assertEquals(7*8, getCanvasSize())
    }

    @Test
    fun testInvalidCCommand() {
        val args = listOf("-1", "b")
        assertFailsWith<InputValueException>(
            block = { testController.execute("C", args) }
        )
    }

    @Test
    fun testLCommand() {
        val x1 = 1
        val y1 = 1
        val x2 = 1
        val y2 = defaultH
        val args = listOf(x1.toString(), y1.toString(), x2.toString(), y2.toString())
        testController.execute("L", args)

        assertEquals(defaultSize, getCanvasSize())

        for (i in 0..defaultH) {
            for (j in 0..defaultW) {
                if (j == x1 && i in y1..y2) assertTrue(testController.canvas.canvasArray[i][j] == 'x')
                else assertFalse(testController.canvas.canvasArray[i][j] == 'x')
            }
        }
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
        assertEquals(defaultSize, getCanvasSize())
    }

    @Test
    fun testRCommand() {
        val x1 = 2
        val y1 = 2
        val x2 = defaultW-2
        val y2 = defaultH-2
        val args = listOf(x1.toString(), y1.toString(), x2.toString(), y2.toString())
        testController.execute("R", args)

        assertEquals(defaultSize, getCanvasSize())

        for (i in 0..defaultH) {
            for (j in 0..defaultW) {
                if ((j == x1 || j == x2) && i in y1..y2) assertTrue(testController.canvas.canvasArray[i][j] == 'x')
                else if ((i == y1 || i == y2) && j in x1..x2) assertTrue(testController.canvas.canvasArray[i][j] == 'x')
                else assertFalse(testController.canvas.canvasArray[i][j] == 'x')
            }
        }
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
        assertEquals(defaultSize, getCanvasSize())

    }

    @Test
    fun testBCommand() {
        val x = 5
        val y = 5
        val c = "c"
        val args = listOf(x.toString(), y.toString(), c)
        testController.execute("B", args)

        assertEquals(defaultSize, getCanvasSize())

        for (i in 1..defaultH) {
            for (j in 1..defaultW) {
                assertTrue(testController.canvas.canvasArray[i][j] == 'c')
            }
            assertFalse(testController.canvas.canvasArray[0][0] == 'c')
            assertFalse(testController.canvas.canvasArray[defaultH+1][defaultW+1] == 'c')
        }

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
    }

    private fun getCanvasSize() : Int {
        var totalSize = 0
        for (line in testController.canvas.canvasArray) {
            totalSize += line.size
        }
        return totalSize
    }

}