package fizzbuzz.java;

import static fizzbuzz.java.FizzBuzz.fizzBuzz;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FizzBuzzTest {

    @Test public void notDivisibleBetween3Or5ReturnsTheNumber () {
        assertEquals (fizzBuzz ().get (0), "1");
    }

    @Test public void divisibleBetween3ReturnsFizz () {
        assertEquals (fizzBuzz ().get (2), "Fizz");
    }

    @Test public void ifNumberContains3ReturnsFizz () {
        assertEquals (fizzBuzz ().get (30), "Fizz");
    }

    @Test public void divisibleBetween5ReturnsBuzz () {
        assertEquals (fizzBuzz ().get (4), "Buzz");
    }

    @Test public void ifNumberContains5ReturnsBuzz () {
        assertEquals (fizzBuzz ().get (51), "Buzz");
    }

    @Test public void ifNumberContains5And3ReturnsFizzBuzz () {
        assertEquals (fizzBuzz ().get (52), "FizzBuzz");
        assertEquals (fizzBuzz ().get (34), "FizzBuzz");
    }

    @Test public void divisibleBetween5And3ReturnsFizzBuzz () {
        assertEquals (fizzBuzz ().get (14), "FizzBuzz");
    }
}
