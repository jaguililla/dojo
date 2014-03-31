/**
 *
 */
object FizzBuzz extends App {
  /**
   *
   * @return
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
