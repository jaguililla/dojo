import org.scalatest.FunSuite

class FizzBuzzTest extends FunSuite {
  val r = FizzBuzz.fizzBuzz

  test("Not divisible between 3 or 5 returns the number") {
    assert (r(0) == "1")
  }

  test("Divisible between 3 returns 'Fizz'") {
    assert (r(2) == "Fizz")
  }

  test("If number contains 3 returns 'Fizz'") {
    assert (r(30) == "Fizz")
  }

  test("Divisible between 5 returns 'Buzz'") {
    assert (r(4) == "Buzz")
  }

  test("If number contains 5 returns 'Buzz'") {
    assert (r(51) == "Buzz")
  }

  test("If number contains 5 and 3 returns 'FizzBuzz'") {
    assert (r(52) == "FizzBuzz")
    assert (r(34) == "FizzBuzz")
  }

  test("Divisible between 5 and 3 returns 'FizzBuzz'") {
    assert (r(14) == "FizzBuzz")
  }
}
