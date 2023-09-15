package completablefuture.transactionexample

class CategorizationService {

    companion object {
        fun categorizeTransaction() {
            Thread.sleep(10000)
        }
    }

}

data class Category(
    val category: String
)

data class Transaction(
    val id: Int,
    val description: String
)