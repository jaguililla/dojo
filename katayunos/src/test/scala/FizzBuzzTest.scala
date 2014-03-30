import org.scalatest.FunSuite

/**
 * Created by jam on 29/03/14.
 */
class FizzBuzzTest extends FunSuite {

  test("Not divisible between 3 or 5 returns the number") {
    val r = FizzBuzz.fizzBuzz
    assert (r(0) == "1")
    assert (r(1) == "2")
  }

  test("Divisible between 3 returns 'Fizz'") {
    val r = FizzBuzz.fizzBuzz
    assert (r(2) == "Fizz")
  }

  def t (n: Int, r: String) {
    val r = FizzBuzz.fizzBuzz
    assert(r(n - 1) == r)
  }

  test("If number contains 3 returns 'Fizz'") {
    val r = FizzBuzz.fizzBuzz
    assert (r(30) == "Fizz")
  }

  test("Divisible between 5 returns 'Buzz'") {
    val r = FizzBuzz.fizzBuzz
    assert (r(4) == "Buzz")
  }

  test("If number contains 5 returns 'Buzz'") {
    val r = FizzBuzz.fizzBuzz
    println (r(51))
    assert (r(51) == "Buzz")
  }

  test("If number contains 5 and 3 returns 'FizzBuzz'") {
    val r = FizzBuzz.fizzBuzz
    println (r(51))
    assert (r(52) == "FizzBuzz")
  }

  test("Divisible between 5 and 3 returns 'FizzBuzz'") {
    val r = FizzBuzz.fizzBuzz
    assert (r(14) == "FizzBuzz")
  }
}
