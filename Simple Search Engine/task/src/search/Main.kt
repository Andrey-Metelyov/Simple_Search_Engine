package search

fun main() {
    println("Enter the number of people:")
    val number = readLine()!!.toInt()
    val db = mutableListOf<String>()
    repeat(number) {
        db.add(readLine()!!)
    }
    println("Enter the number of search queries:")
    val queries = readLine()!!.toInt()
    repeat(queries) {
        println("Enter data to search people:")
        val man = readLine()!!

        val found = db.filter { it.lowercase().contains(man.lowercase()) }
        if (found.isEmpty()) {
            println("No matching people found.")
        } else {
            println("People found:")
            for (el in found) {
                println(el)
            }
        }
    }
}
