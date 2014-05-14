package katayunos

import scala.util.Random.nextInt

/**
 * http://www.solveet.com/exercises/Kata-CodeBreaker/14
 * TODO If 'object' extends from 'App' vals won't be initialized WTF!
 */
object SCodeBreaker {
  val CodeSize = 4
  val MaxAttempts = 15
  val CodeColors = Array ('R', 'M', 'A', 'V', 'N', 'I')
  val CompleteMatch = 'X'
  val ColorMatch = '*'

  val Code = {
    val CodeChars = (1 to CodeSize).map (_ => CodeColors (nextInt (CodeColors.length)))
    CodeChars.foldLeft ("")(_ + _)
  }

  private var mAttempts = 0

  def attempts = mAttempts

  // TODO Set as package private (only for tests)
  private[katayunos] def reset {
    mAttempts = 0
  }

  def checkCode (guess: String, code: String = Code) = {
    def checkIndex (result: String = "", index: Int = 0): String = {
      def checkCharacter =
        if (code.charAt (index) == guess.charAt (index))
          result + CompleteMatch
        else if (code.contains (guess.charAt (index)))
          result + ColorMatch
        else
          result

      if (guess == null || guess.isEmpty || guess.length != CodeSize)
        throw new IllegalArgumentException

      if (index < guess.length)
        checkIndex (checkCharacter, index + 1)
      else
        result
    }

    checkIndex ()
  }

  def check (guess: String, code: String = Code) = {
    if (mAttempts == MaxAttempts)
      throw new IllegalStateException

    val result = checkCode (guess, code)
    mAttempts = mAttempts + 1

    if (result == "XXXX")
      mAttempts = 0

    result
  }
}
