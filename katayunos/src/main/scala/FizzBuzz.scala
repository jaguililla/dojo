/**
 * Created by jam on 29/03/14.
 */
object FizzBuzz {
  def fizzBuzz = for (i <- 1 to 100) yield
   if ((i % 5 == 0 && i % 3 == 0)  ||
       (i.toString.contains(5) && i.toString.contains(3))) "FizzBuzz"
    else if (i % 3 == 0 || i.toString.contains('3')) "Fizz"
    else if (i % 5 == 0 || i.toString.contains('5')) "Buzz"
    else i.toString
}
