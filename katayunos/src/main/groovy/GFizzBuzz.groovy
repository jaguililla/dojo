package katayunos

import groovy.transform.CompileStatic

/**
 * http://www.solveet.com/exercises/Kata-FizzBuzz/11
 */
class GFizzBuzz {
    @CompileStatic private static List<String> fizzBuzz () {
        (1..100).collect {
            int number = (int)it // Required to compile statically
            String strNumber = it.toString()
            boolean mod3 = number % 3 == 0, mod5 = number % 5 == 0
            boolean has3 = strNumber.contains ('3'), has5 = strNumber.contains ('5')

            if ((mod3 && mod5) || (has3 && has5)) 'FizzBuzz'
            else if ((mod3) || (has3)) 'Fizz'
            else if ((mod5) || (has5)) 'Buzz'
            else strNumber
        }
    }

    // Syntactic sugar for the tests
    def static List<String> fizzBuzz = fizzBuzz ()
}
