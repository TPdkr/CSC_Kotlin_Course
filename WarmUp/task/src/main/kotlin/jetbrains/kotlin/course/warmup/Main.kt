package jetbrains.kotlin.course.warmup

// You will use this function later
fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String) =
    "Welcome to the game! $newLineSymbol" +
            newLineSymbol +
            "Two people play this game: one chooses a word (a sequence of letters), " +
            "the other guesses it. In this version, the computer chooses the word: " +
            "a sequence of $wordLength letters (for example, $secretExample). " +
            "The user has several attempts to guess it (the max number is $maxAttemptsCount). " +
            "For each attempt, the number of complete matches (letter and position) " +
            "and partial matches (letter only) is reported. $newLineSymbol" +
            newLineSymbol +
            "For example, with $secretExample as the hidden word, the BCDF guess will " +
            "give 1 full match (C) and 1 partial match (B)."

fun generateSecret(): String ="ABCD"
fun countExactMatches(secret : String, guess: String): Int =guess.filterIndexed{ index, symbol -> secret[index] == symbol}.length
fun countAllMatches(secret : String, guess: String): Int = minOf(guess.filter{ it in secret}.length, secret.filter{ it in guess}.length)
fun countPartialMatches(secret : String, guess: String): Int = countAllMatches(secret, guess)- countExactMatches(secret, guess)
fun printRoundResults(secret: String, guess: String){
    println("Your guess has ${countExactMatches(secret, guess)} full matches and ${countPartialMatches(secret, guess)} partial matches.")
}
fun isWon(complete: Boolean, attempts: Int, maxAttemptsCount: Int)=complete && attempts<= maxAttemptsCount
fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int)=!complete && attempts> maxAttemptsCount
fun isComplete(secret : String, guess: String): Boolean = secret==guess
fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int ){
    var attempts=0
    do{
        println("Please input your guess. It should be of length $wordLength.")
        val guess= safeReadLine()
        attempts++
        printRoundResults(secret, guess)
        if (isLost(isComplete(secret, guess), attempts, maxAttemptsCount)){
            println("Sorry, you lost! :( My word is $secret")
            break
        } else if(isWon(isComplete(secret, guess), attempts, maxAttemptsCount)){
            println("Congratulations! You guessed it!")
            break
        }


    } while(!isComplete(secret, guess))

}
fun main() {
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"
    println(getGameRules(wordLength,maxAttemptsCount, secretExample))
    playGame(generateSecret(), wordLength, maxAttemptsCount)
}
