package search

val db = mutableListOf<String>()

fun main() {
    fillDb()

    while (true) {
        when (menu()) {
            1 -> findPerson()
            2 -> printAll()
            0 -> break
        }
    }

    println("Bye!")

//    println("Enter the number of search queries:")
//    val queries = readLine()!!.toInt()
//    repeat(queries) {
//        println("Enter data to search people:")
//        val man = readLine()!!
//
//    }
}

fun printAll() {
    println("=== List of people ===")
    for (record in db) {
        println(record)
    }
}

fun findPerson() {
    println("Enter a name or email to search all suitable people.")
    val input = readLine()!!
    val found = db.filter { it.lowercase().contains(input.lowercase()) }
    if (found.isEmpty()) {
        println("No matching people found.")
    } else {
//        println("People found:")
        for (el in found) {
            println(el)
        }
    }
}

private fun menu(): Int {
    val options = arrayOf("1", "2", "0")
    while (true) {
        println(
            "=== Menu ===\n" +
                    "1. Find a person\n" +
                    "2. Print all people\n" +
                    "0. Exit"
        )
        val choice = readLine()!!
        if (choice !in options) {
            println("Incorrect option! Try again.")
        } else {
            return choice.toInt()
        }
    }
}

private fun fillDb() {
    println("Enter the number of people:")
    val number = readLine()!!.toInt()
    repeat(number) {
        db.add(readLine()!!)
    }
}
