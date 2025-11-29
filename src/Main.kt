data class Task(
    val title: String,
    val description: String,
    var isComplete: Boolean = false // Default tasks to not complete
)

fun addTask(taskList: MutableList<Task>) {
    println("\n--- Add New Task ---")

    // Get Task Title
    print("Enter task title: ")
    val title = readLine() ?: return

    // Get Task Description
    print("Enter task description: ")
    val description = readLine() ?: return

    // Create the new Task object
    val newTask = Task(title, description)

    // Add it to the list (Collections requirement)
    taskList.add(newTask)
    println("Task added successfully: \"$title\"")
}

fun viewTasks(taskList: List<Task>) {
    println("\n--- Current Tasks ---")

    if (taskList.isEmpty()) {
        println("No tasks currently in the list.")
        return
    }

    // Loop through the list and print each task with its status
    for ((index, task) in taskList.withIndex()) {
        val status = if (task.isComplete) "✅ COMPLETE" else "❌ PENDING"
        // Index is 0-based, so we add 1 for display purposes
        println("${index + 1}. [${status}] ${task.title}")
        println("   Description: ${task.description}")
    }
}

fun markTaskComplete(taskList: MutableList<Task>) {
    println("\n--- Mark Task Complete ---")

    viewTasks(taskList)

    if (taskList.isEmpty()) {
        return // Exit if there are no tasks
    }

    print("Enter the number of the task to mark COMPLETE: ")

    // Read the input and convert it to an integer index
    val input = readLine()
    val taskNumber = input?.toIntOrNull()

    if (taskNumber != null && taskNumber > 0 && taskNumber <= taskList.size) {
        val index = taskNumber - 1

        taskList[index].isComplete = true

        println("SUCCESS: Task #$taskNumber marked as COMPLETE.")

    } else {
        println("ERROR: Invalid number entered. Please try again.")
    }
}

fun removeTask(taskList: MutableList<Task>) {
    println("\n--- Remove Task ---")

    // First, show the current list
    viewTasks(taskList)

    if (taskList.isEmpty()) {
        return // Exit if there are no tasks
    }

    print("Enter the number of the task to REMOVE: ")

    val input = readLine()
    val taskNumber = input?.toIntOrNull()

    if (taskNumber != null && taskNumber > 0 && taskNumber <= taskList.size) {
        val index = taskNumber - 1

        // Safely remove the task from the list (Collections requirement)
        val removedTask = taskList.removeAt(index)

        println("SUCCESS: Task \"${removedTask.title}\" removed.")

    } else {
        println("ERROR: Invalid number entered. Please try again.")
    }
}

//  Main Execution Function
fun main() {
    // This mutable list stores all the Task objects (Collections Requirement).
    val taskList = mutableListOf<Task>()
    var running = true

    while (running) {
        println("\n--- Task Manager Menu ---")
        println("1. Add a new task")
        println("2. View all tasks")
        println("3. Mark a task as complete")
        println("4. Remove a task")
        println("5. Exit")
        print("Enter choice (1-5): ")

        // Read the user's input line
        val input = readLine()

        // Use 'when' to process the input (My Unique Requirement)
        when (input) {
            "1" -> addTask(taskList)
            "2" -> viewTasks(taskList)
            "3" -> markTaskComplete(taskList)
            "4" -> removeTask(taskList)
            "5" -> {
                println("Exiting Task Manager. Goodbye!")
                running = false
            }
            // Catch invalid inputs
            else -> println("Invalid choice. Please enter a number between 1 and 5.")
        }
    }
}