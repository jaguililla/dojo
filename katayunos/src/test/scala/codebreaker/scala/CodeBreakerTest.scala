package codebreaker.scala

import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

import CodeBreaker.{ CodeSize, CodeColors, MaxAttempts }

@RunWith (classOf [JUnitRunner]) // Required to work in Gradle
class CodeBreakerTest extends FunSuite {

  test ("Code Generated Has 4 Chars") {
    assert (CodeSize == CodeBreaker.Code.length)
  }

  test ("Code Generated Has Valid Codes") {
    CodeBreaker.Code.toCharArray.foreach { c =>
      assert (CodeColors.contains (c), s"$c is not a valid color")
    }
  }

  test ("One Correct Color Incorrect Position") {
    assert ("*" == CodeBreaker.checkCode ("NRRR", "MAVN"))
  }

  test ("Many Correct Color Incorrect Position") {
    assert ("**" == CodeBreaker.checkCode ("AMRR", "MAVN"))
  }

  test ("One Correct Color Correct Position") {
    assert ("X" == CodeBreaker.checkCode ("MRRR", "MAVN"))
  }

  test ("Many Correct Color Correct Positions") {
    assert ("XX" == CodeBreaker.checkCode ("MARR", "MAVN"))
  }

  test ("Duplicated Colors") {
    assert ("X*X*" == CodeBreaker.checkCode ("RARR", "RRRA"))
  }

  test ("Correct Code") {
    assert ("XXXX" == CodeBreaker.checkCode ("RARR", "RARR"))
  }

  test ("Correct In 15 Times") {
    CodeBreaker.reset
    (1 to MaxAttempts).foreach { _ => CodeBreaker.check ("XXXX") }
  }

  test ("Fail In More Than 15 Times") {
    CodeBreaker.reset
    intercept [IllegalStateException] {
      (1 to MaxAttempts + 1).foreach { _ => CodeBreaker.check ("XXXX") }
    }
  }

  test ("ResetCounter") {
    CodeBreaker.reset
    CodeBreaker.check ("XXXX");

    assert ("XXXX" == CodeBreaker.check (CodeBreaker.Code));
    assert (0 == CodeBreaker.attempts);
  }

  test ("Input Not Color") {
    assert ("" == CodeBreaker.checkCode ("XXXX", "RRRR"))
  }

  test ("Null Input") {
    intercept [IllegalArgumentException] {
      CodeBreaker.check (null)
    }
  }

  test ("Empty Input") {
    intercept[IllegalArgumentException] {
      CodeBreaker.check ("")
    }
  }

  test ("Longer Input") {
    intercept [IllegalArgumentException] {
      CodeBreaker.check ("RAMVN")
    }
  }
}
