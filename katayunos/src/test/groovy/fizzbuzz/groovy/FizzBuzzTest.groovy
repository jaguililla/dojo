package fizzbuzz.groovy

import org.junit.Test

import static fizzbuzz.groovy.FizzBuzz.fizzBuzz

class FizzBuzzTest {
    @Test void "Not divisible between 3 or 5 returns the number" () {
        assert fizzBuzz[0] == "1"
    }

    @Test void "Divisible between 3 returns 'Fizz'" () {
        assert fizzBuzz[2] == "Fizz"
    }

    @Test void "If number contains 3 returns 'Fizz'" () {
        assert fizzBuzz[30] == "Fizz"
    }

    @Test void "Divisible between 5 returns 'Buzz'" () {
        assert fizzBuzz[4] == "Buzz"
    }

    @Test void "If number contains 5 returns 'Buzz'" () {
        assert fizzBuzz[51] == "Buzz"
    }

    @Test void "If number contains 5 and 3 returns 'FizzBuzz'" () {
        assert fizzBuzz[52] == "FizzBuzz"
        assert fizzBuzz[34] == "FizzBuzz"
    }

    @Test void "Divisible between 5 and 3 returns 'FizzBuzz'" () {
        assert fizzBuzz[14] == "FizzBuzz"
    }
}
