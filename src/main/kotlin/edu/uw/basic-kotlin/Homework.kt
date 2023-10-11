package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

fun main () {
    val pers = Person("me", "he", 45)
    println(pers.debugString)
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(args: Any): String {
    val res = when (args) {
        "Hello" -> "world"
        "Bonjour", "Howdy"-> "Say what?"
        is String -> "I don't understand"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Int -> "a number"
        else -> "I don't understand"
    }
    return res
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(val1: Int, val2: Int): Int {
    return val1 + val2
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(val1: Int, val2: Int): Int {
    return val1 - val2
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(val1: Int, val2:Int, op: (Int, Int) -> Int):Int {
    return op(val1, val2)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String,
             val lastName: String,
             var age: Int,
             val debugString: String = "[Person firstName:${firstName} lastName:${lastName} age:${age}]")


// write a class "Money" with amount and currency, and define a convert() method and the "+" operator

enum class Currency {USD, EUR, CAN, GBP}

class Money(val amount: Int, val currency: String) {
    init {
        if (amount < 0) {
            throw IllegalArgumentException("amount cant be neg")
        }
        if (currency !== "USD" && currency !== "EUR" &&
            currency !== "CAN" && currency !== "GBP") {
                throw IllegalArgumentException("Currency must be USD/EUR/CAD/GBP")
        }
    }

    fun convert(convertTo: String): Money {
        var conversion = when (currency) {  // convert to USD
            "GBP" -> amount * 2
            "EUR" -> amount * 2/3
            "CAN" -> amount * 4/5
            else -> amount
        }
        conversion = when (convertTo) {
            "GBP" -> conversion * 1/2
            "EUR" -> conversion * 3/2
            "CAN" -> conversion * 5/4
            else -> conversion
        }
        return Money(conversion, convertTo)
    }

    operator fun plus(other: Money):Money {
        val otherConverted = if (this.currency !== other.currency) other.convert(this.currency) else other
        return Money(this.amount + otherConverted.amount, this.currency)
    }
}