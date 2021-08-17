package search

import java.io.File

val db = mutableListOf<String>()
val index = mutableMapOf<String, MutableList<Int>>()

fun main(args: Array<String>) {
    fillDb(args)

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
    val found = index[input]
    if (found == null || found.isEmpty()) {
        println("No matching people found.")
    } else {
        for (el in found!!) {
            println(db[el])
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

private fun fillDb(args: Array<String>) {
//    println("Enter the number of people:")
//    val number = readLine()!!.toInt()

//    repeat(number) {
//        db.add(readLine()!!)
//    }
    val file = File(args[1])
    for ((n, line) in file.readLines().withIndex()) {
        db.add(line)
        val words = line.split(" ")
        for (word in words) {
            if (index.containsKey(word)) {
                index[word]!!.add(n)
            } else {
                index[word] = mutableListOf(n)
            }
        }
    }
}
