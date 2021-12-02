import okio.Okio
import okio.source
import java.io.File

fun main(args: Array<String>) {
    aoc1()
    aoc2()
    aoc3()
    aoc4()
}

fun aoc1() {
    val values = mutableListOf<Int>()
    val bufferedSource = File("input1.txt").bufferedReader()
    while (true) {
        val line = bufferedSource.readLine() ?: break
        values.add(line.toInt())
    }
    var previous: Int? = null
    var increases = 0
    values.onEach {
        if (previous != null) {
            if (it > previous!!) {
                increases++
            }
        }
        previous = it
    }

    println("$increases")
}

fun aoc2() {
    val values = mutableListOf<Int>()
    val bufferedSource = File("input1.txt").bufferedReader()
    while (true) {
        val line = bufferedSource.readLine() ?: break
        values.add(line.toInt())
    }
    var previous: Int? = null
    var increases = 0
    values.onEachIndexed { index, it ->
        if (index > 1) {
            val sum = it + values[index - 1] + values[index - 2]

            if (previous != null && sum > previous!!) {
                increases++
            }

            previous = sum
        }
    }

    println("$increases")
}

fun aoc3() {
    val values = mutableListOf<String>()
    val bufferedSource = File("input2.txt").bufferedReader()
    while (true) {
        val line = bufferedSource.readLine() ?: break
        values.add(line)
    }

    var depth = 0
    var horizontal = 0

    val matcher = Regex("(\\w*) (\\w)")

    values.onEach {
        matcher.matchEntire(it)?.let {
            val direction = it.groups[1]!!.value
            val value = it.groups[2]!!.value.toInt()

            when (direction) {
                "forward" -> horizontal += value
                "up" -> depth -= value
                "down" -> depth += value
            }
        }
    }

    println("depth: $depth, horizontal: $horizontal, result = ${depth * horizontal}")
}

fun aoc4() {
    val values = mutableListOf<String>()
    val bufferedSource = File("input2.txt").bufferedReader()
    while (true) {
        val line = bufferedSource.readLine() ?: break
        values.add(line)
    }

    var aim = 0
    var depth = 0
    var horizontal = 0

    val matcher = Regex("(\\w*) (\\w)")

    values.onEach {
        matcher.matchEntire(it)?.let {
            val direction = it.groups[1]!!.value
            val value = it.groups[2]!!.value.toInt()

            when (direction) {
                "forward" -> {
                    horizontal += value
                    depth += aim * value
                }
                "up" -> aim -= value
                "down" -> aim += value
            }
        }
    }

    println("depth: $depth, horizontal: $horizontal, result = ${depth * horizontal}")
}