package fizzbuzz.groovy

/**
 * http://www.solveet.com/exercises/Kata-FizzBuzz/11
 */
class FizzBuzz {
    def static List<String> fizzBuzz = (1..100).collect {
        String strNumber = it.toString()
        boolean mod3 = it % 3 == 0, mod5 = it % 5 == 0
        boolean has3 = strNumber.contains ('3'), has5 = strNumber.contains ('5')

        if ((mod3 && mod5) || (has3 && has5))
            'FizzBuzz'
        else if ((mod3) || (has3))
            'Fizz'
        else if ((mod5) || (has5))
            'Buzz'
        else
            strNumber
    }
}
