package katayunos

/**
 * http://www.solveet.com/exercises/Kata-FizzBuzz/11
 */
object SFizzBuzz extends App {
  /**
   * 1) Builds a range from 1 to 100
   * 2) Maps every value applying the closuse
   * 3) Closure matches (pattern matching) a four elements tuple (i % 3, i % 5, i.toString...)
   * 4) Each case is listed
   *
   * Easy to add, change and reorder cases
   */
  def fizzBuzz = 1 to 100 map (
    i => (i % 3, i % 5, i.toString.contains ('3'), i.toString.contains ('5')) match {
      case (0, 0, _, _) => "FizzBuzz"
      case (_, _, true, true) => "FizzBuzz"
      case (0, _, _, _) => "Fizz"
      case (_, _, true, false) => "Fizz"
      case (_, 0, _, _) => "Buzz"
      case (_, _, false, true) => "Buzz"
      case _ => i.toString
    }
  )

  fizzBuzz map (println _)
}
