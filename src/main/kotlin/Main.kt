import exceptions.InputValueException

fun main(args: Array<String>) {
    val controller = AppController()
    runApplication(controller)
}

fun runApplication(controller: AppController) {
    println("Welcome to the Console drawing program, please enter an input command")
    print("enter command: ")

    while(true) {
        val input = readln().split(' ')
        try {
            val command = input[0]
            val intArgs = input.subList(1, input.size)

            controller.execute(command, intArgs)

            controller.printCanvas()

        } catch (e: InputValueException) {
            println("Error: " + e.message)
        }

        print("enter command: ")
    }
}