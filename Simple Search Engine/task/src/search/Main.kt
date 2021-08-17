package search

fun main() {
    val words = readLine()!!.split(" ")
    val i = words.indexOf(readLine()!!)
    if (i != -1) {
        println(i + 1)
    } else {
        println("Not found")
    }
}
