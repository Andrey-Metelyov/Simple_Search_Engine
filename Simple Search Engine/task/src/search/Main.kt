package search

import java.io.File

val db = mutableListOf<String>()
val index = mutableMapOf<String, MutableList<Int>>()

fun main(args: Array<String>) {
    fillDb(args)
    System.err.println(db)
    System.err.println(index)
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
    println("Select a matching strategy: ALL, ANY, NONE")
    val strategy = readLine()!!
    println("Enter a name or email to search all suitable people.")
    val input = readLine()!!.lowercase().split(" ")
    var found = when (strategy) {
        "ANY" -> {
            val set = mutableSetOf<Int>()
            for (el in input) {
                val res = index[el]
                if (res != null && res.isNotEmpty()) {
                    set.addAll(res)
                }
            }
            set
        }
        "ALL" -> {
            var andSet = mutableSetOf<Int>()
            for (i in 0 until db.size) andSet.add(i)
            for (el in input) {
                val res = index[el]
                if (res != null && res.isNotEmpty()) {
                    andSet = andSet.intersect(res) as MutableSet<Int>
                }
            }
            andSet
        }
        "NONE" -> {
            val noneSet = mutableSetOf<Int>()
            val allSet = mutableSetOf<Int>()
            for (i in 0 until db.size) allSet.add(i)
//            System.err.println(allSet)
            for (el in input) {
                val res = index[el]
                if (res != null && res.isNotEmpty()) {
                    System.err.println("$el found in $res")
                    noneSet.addAll(res)
                }
            }
            allSet.subtract(noneSet)
        }
        else -> emptySet<Int>()
    }
    if (found.isEmpty()) {
        println("Nothing found")
    } else {
        println("${found.size} persons found:")
        for (el in found) {
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
        val words = line.lowercase().split(" ")
        for (word in words) {
            if (index.containsKey(word)) {
                index[word]!!.add(n)
            } else {
                index[word] = mutableListOf(n)
            }
        }
    }
}
