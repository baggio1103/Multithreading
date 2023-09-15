package completablefuture.parallelchain

import java.util.concurrent.CompletableFuture

fun main() {
    val kotlin = CompletableFuture.supplyAsync {
        Thread.sleep(1000)
        "Kotlin"
    }
    val java = CompletableFuture.supplyAsync {
        Thread.sleep(1000)
        "Java"
    }
    val spring = CompletableFuture.supplyAsync {
        Thread.sleep(1000)
//        throw RuntimeException("")
        "Spring"
    }
    val allOf = CompletableFuture.allOf(kotlin, java, spring)
    allOf.join()
    println("${kotlin.get()} ${java.get()} ${spring.get()}")
}