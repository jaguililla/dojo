package fizzbuzz.groovy

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith (classOf [JUnitRunner]) // Required to work in Gradle
class FizzBuzzTest extends FunSuite {

   test("Not divisible between 3 or 5 returns the number") {
     assert (fizzBuzz(0) == "1")
   }

   test("Divisible between 3 returns 'Fizz'") {
     assert (fizzBuzz(2) == "Fizz")
   }

   test("If number contains 3 returns 'Fizz'") {
     assert (fizzBuzz(30) == "Fizz")
   }

   test("Divisible between 5 returns 'Buzz'") {
     assert (fizzBuzz(4) == "Buzz")
   }

   test("If number contains 5 returns 'Buzz'") {
     assert (fizzBuzz(51) == "Buzz")
   }

   test("If number contains 5 and 3 returns 'FizzBuzz'") {
     assert (fizzBuzz(52) == "FizzBuzz")
     assert (fizzBuzz(34) == "FizzBuzz")
   }

   test("Divisible between 5 and 3 returns 'FizzBuzz'") {
     assert (fizzBuzz(14) == "FizzBuzz")
   }
 }
