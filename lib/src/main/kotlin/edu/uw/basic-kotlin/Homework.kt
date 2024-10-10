package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    return when (arg) {
        is String -> when(arg) {
            "Hello" -> "world"
            else -> "Say what?"
        }
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Int -> "a number"
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int {
    return lhs + rhs
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int {
    return lhs - rhs
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int {
    return op(lhs, rhs)
}


// write a class "Person" with first name, last name and age
class Person(var firstName: String, var lastName: String, var age: Int) {
    val debugString: String 
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money"
class Money(var amount: Int, var currency: String) {
    init {
        require(amount >= 0)
        require(currency in listOf("USD", "EUR", "CAN", "GBP"))
    }

    fun convert(newCurrency: String) : Money {
        val newAmount = conversionAmount(newCurrency)
        return Money(newAmount, newCurrency)
    }

    fun conversionAmount(newCurrency: String) : Int {
        return when (currency) {
            "USD" -> when (newCurrency) {
                "GBP" -> (amount * 0.5).toInt()
                "EUR" -> (amount * 1.5).toInt()
                "CAN" -> (amount * 1.25).toInt()
                else -> amount
            }
            "GBP" -> when (newCurrency) {
                "USD" -> (amount * 2.0).toInt() 
                "EUR" -> (amount * 3.0).toInt()
                "CAN" -> (amount * 2.5).toInt() 
                else -> amount 
            }
            "EUR" -> when (newCurrency) {
                "USD" -> (amount * (2.0 / 3.0)).toInt()
                "GBP" -> (amount * (1.0 / 3.0)).toInt() 
                "CAN" -> (amount * (5.0 / 6.0)).toInt() 
                else -> amount 
            }
            "CAN" -> when (newCurrency) {
                "USD" -> (amount * (4.0 / 5.0)).toInt() 
                "GBP" -> (amount * (2.0 / 5.0)).toInt() 
                "EUR" -> (amount * (6.0 / 5.0)).toInt() 
                else -> amount 
            }
            else -> throw IllegalArgumentException()
        }
    }

    operator fun plus(toAdd: Money): Money {
        val newToAdd = toAdd.convert(this.currency)
        return Money(this.amount + newToAdd.amount, this.currency)
    }
}