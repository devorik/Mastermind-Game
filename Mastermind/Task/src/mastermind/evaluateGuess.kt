package mastermind

import java.util.*
import kotlin.math.min


data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val rightPosition = getRightPosition(secret,guess)
    val charOccurrencesOfSecret = occurrenceOfCharInString(secret).values;
    val charOccurrencesOfGuess = occurrenceOfCharInString(guess).values;
    val wrongPosition = getWrongPosition(charOccurrencesOfSecret,charOccurrencesOfGuess,rightPosition);
    return Evaluation(rightPosition,wrongPosition)
}

fun getRightPosition(secret:String,guess:String):Int {
    val zipped = secret zip guess
    var rightPosition = 0;
    for ((a,b) in zipped) {
        if(a==b) {
            rightPosition++;
        }
    }
    return rightPosition
}

fun getWrongPosition(secretCharOccurrencesValues:Collection<Int>, guessCharOccurrencesValues:Collection<Int>, rightPosition: Int):Int {
    var commonLetters = 0;
    for(i in 0 until 6) {
        if(secretCharOccurrencesValues.elementAt(i) <guessCharOccurrencesValues.elementAt(i)) {
            commonLetters+= secretCharOccurrencesValues.elementAt(i)
        }else {
            commonLetters+= guessCharOccurrencesValues.elementAt(i)
        }
    }
    return commonLetters - rightPosition
}

fun occurrenceOfCharInString(str : String):Map<Char,Int> {
    val charArr = str.toCharArray()
    val charCountMap = mutableMapOf(
            'A' to 0,
            'B' to 0,
            'C' to 0,
            'D' to 0,
            'E' to 0,
            'F' to 0
    )
    for (c in charArr) {
        if (charCountMap.containsKey(c)) charCountMap.put(c, charCountMap.get(c)!!.plus(1))
        else charCountMap.put(c, 1)
    }
    return charCountMap
}
