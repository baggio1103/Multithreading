package completablefuture

import java.util.concurrent.CompletableFuture

fun main() {
//    val completableFuture = CompletableFuture.supplyAsync {
//        getApiCall()
//    }
//    println("Is the completable future complete? ${completableFuture.isDone}")
//    println("Result: ${completableFuture.get()}")

//    supplyAsyncExample()
    completableAsync()
}

fun getApiCall(): String {
    Thread.sleep(2000)
    return "Hello world"
}

fun supplyAsyncExample(): CompletableFuture<Void> =
    CompletableFuture.supplyAsync {
        Thread.sleep(500)
        "hello world"
    }.thenApply { it.uppercase() }
        .thenAccept { println(it) }

fun completableAsync() {
    val completableFuture = CompletableFuture.supplyAsync {
        println("Thread: ${Thread.currentThread().name} executing ")
        "Hello"
    }
    val future = completableFuture
        .thenAcceptAsync { s: String ->
            println("Thread: ${Thread.currentThread().name} executing ")
            "Thread: ${Thread.currentThread().name}: $s World"
        }
    println("Hello world")
    println(future.get())
}
