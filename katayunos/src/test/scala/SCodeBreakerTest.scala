package katayunos

import SCodeBreaker.{ MaxAttempts, CodeSize, CodeColors }

import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith (classOf [JUnitRunner]) // Required to work in Gradle
class SCodeBreakerTest extends FunSuite {

  test ("Code Generated Has 4 Chars") {
    assert (CodeSize == SCodeBreaker.Code.length)
  }

  test ("Code Generated Has Valid Codes") {
    SCodeBreaker.Code.toCharArray.foreach { c =>
      assert (CodeColors.contains (c), s"$c is not a valid color")
    }
  }

  test ("One Correct Color Incorrect Position") {
    assert ("*" == SCodeBreaker.checkCode ("NRRR", "MAVN"))
  }

  test ("Many Correct Color Incorrect Position") {
    assert ("**" == SCodeBreaker.checkCode ("AMRR", "MAVN"))
  }

  test ("One Correct Color Correct Position") {
    assert ("X" == SCodeBreaker.checkCode ("MRRR", "MAVN"))
  }

  test ("Many Correct Color Correct Positions") {
    assert ("XX" == SCodeBreaker.checkCode ("MARR", "MAVN"))
  }

  test ("Duplicated Colors") {
    assert ("X*X*" == SCodeBreaker.checkCode ("RARR", "RRRA"))
  }

  test ("Correct Code") {
    assert ("XXXX" == SCodeBreaker.checkCode ("RARR", "RARR"))
  }

  test ("Correct In 15 Times") {
    SCodeBreaker.reset
    (1 to MaxAttempts).foreach { _ => SCodeBreaker.check ("XXXX") }
  }

  test ("Fail In More Than 15 Times") {
    SCodeBreaker.reset
    intercept [IllegalStateException] {
      (1 to MaxAttempts + 1).foreach { _ => SCodeBreaker.check ("XXXX") }
    }
  }

  test ("ResetCounter") {
    SCodeBreaker.reset
    SCodeBreaker.check ("XXXX");

    assert ("XXXX" == SCodeBreaker.check (SCodeBreaker.Code));
    assert (0 == SCodeBreaker.attempts);
  }

  test ("Input Not Color") {
    assert ("" == SCodeBreaker.checkCode ("XXXX", "RRRR"))
  }

  test ("Null Input") {
    intercept [IllegalArgumentException] {
      SCodeBreaker.check (null)
    }
  }

  test ("Empty Input") {
    intercept[IllegalArgumentException] {
      SCodeBreaker.check ("")
    }
  }

  test ("Longer Input") {
    intercept [IllegalArgumentException] {
      SCodeBreaker.check ("RAMVN")
    }
  }
}
