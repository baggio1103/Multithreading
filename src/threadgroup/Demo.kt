package threadgroup

fun main() {

    val threadGroup = ThreadGroup("db-threads")
    val thread = Thread(
        threadGroup,
        {
            println("Starting")
            for (i in 0 .. 5) {
                Thread.sleep(100)
                println("Looping $i time")
            }
            println("Finishing")
        },
        "con-1"
    )
    thread.start()
    println("Main is going further")
    threadGroup.suspend()
}