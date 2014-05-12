package katayunos

import org.junit.Test

import static GCodeBreaker.CODE_COLORS
import static GCodeBreaker.MAX_ATTEMPS
import static GCodeBreaker.CODE_SIZE

class GCodeBreakerTest {
    private katayunos.GCodeBreaker codeBreaker = new katayunos.GCodeBreaker ()

    @Test void "Code Generated Has 4 Chars" () {
        assert CODE_SIZE == codeBreaker.generateCode ().length ()
    }

    @Test void "Code Generated Has Valid Codes" () {
        codeBreaker.generateCode ().each { assert CODE_COLORS.contains (it) }
    }

    @Test void "Input Not Color" () {
        assert "" == codeBreaker.check ("XXXX")
    }

    @Test void "One Correct Color Incorrect Position" () {
        assert "*" == codeBreaker.checkCode ("MAVN", "NRRR")
    }

    @Test void "Many Correct Color Incorrect Position" () {
        assert "**" == codeBreaker.checkCode ("MAVN", "AMRR")
    }

    @Test void "One Correct Color Correct Position" () {
        assert "X" == codeBreaker.checkCode ("MAVN", "MRRR")
    }

    @Test void "Many Correct Color Correct Positions" () {
        assert "XX" == codeBreaker.checkCode ("MAVN", "MARR")
    }

    @Test (expected = IllegalStateException.class)
    public void "Fail In More Than 15 Times" () {
        (0..MAX_ATTEMPS).each { codeBreaker.check "XXXX" }
    }

    @Test void "Correct In 15 Times" () {
        (0..<MAX_ATTEMPS).each { codeBreaker.check "XXXX" }
    }

    @Test void "Duplicated Colors" () {
        assert "XXXX" == codeBreaker.checkCode ("RARR", "RARR")
    }

    @Test void "Reset Counter" () {
        (0..5).each { codeBreaker.check "XXXX" }

        assert "XXXX" == codeBreaker.check (codeBreaker.code)
        assert 0 == codeBreaker.attemps
    }

    @Test (expected = IllegalArgumentException.class) void "Null Input" () {
        codeBreaker.check null
    }

    @Test (expected = IllegalArgumentException.class) void "Empty Input" () {
        codeBreaker.check ""
    }

    @Test (expected = IllegalArgumentException.class) void "Longer Input" () {
        codeBreaker.check "RAMVN"
    }
}
