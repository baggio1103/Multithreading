package completablefuture.chainingexample

import java.util.concurrent.CompletableFuture

fun main() {
   val futureChain = CompletableFuture.supplyAsync {
        Thread.sleep(1000)
        println("Thread: ${Thread.currentThread().name}")
        "Kotlin"
    }.thenCompose {
        println("Thread: ${Thread.currentThread().name}")
        CompletableFuture.supplyAsync { "$it is the best" }
    }.thenApply {
        println("Thread: ${Thread.currentThread().name}")
        println("Result: $it")
    }
    futureChain.join()
}