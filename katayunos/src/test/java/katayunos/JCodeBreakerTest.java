package katayunos;

import static katayunos.JCodeBreaker.CODE_COLORS;

import static katayunos.JCodeBreaker.CODE_SIZE;
import static katayunos.JCodeBreaker.MAX_ATTEMPS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * TODO Static imports do not work in Java's default package: import static JCodeBreaker.*;
 */
public class JCodeBreakerTest {
    private static final String COLORS = new String (CODE_COLORS);
    private JCodeBreaker codeBreaker = new JCodeBreaker ();

    @Test public void codeGeneratedHas4Chars () {
        assertTrue (CODE_SIZE == codeBreaker.generateCode ().length ());
    }

    @Test public void codeGeneratedHasValidCodes () {
        for (char c : codeBreaker.generateCode ().toCharArray ())
            assertTrue (COLORS.indexOf (c) >= 0);
    }

    @Test public void inputNotColor () {
        assertEquals ("", codeBreaker.check ("XXXX"));
    }

    @Test public void oneCorrectColorIncorrectPosition () {
        assertEquals ("*", codeBreaker.checkCode ("MAVN", "NRRR"));
    }

    @Test public void manyCorrectColorIncorrectPosition () {
        assertEquals ("**", codeBreaker.checkCode ("MAVN", "AMRR"));
    }

    @Test public void oneCorrectColorCorrectPosition () {
        assertEquals ("X", codeBreaker.checkCode ("MAVN", "MRRR"));
    }

    @Test public void manyCorrectColorCorrectPositions () {
        assertEquals ("XX", codeBreaker.checkCode ("MAVN", "MARR"));
    }

    @Test (expected = IllegalStateException.class) public void failInMoreThan15Times () {
        for (int ii = 0; ii < MAX_ATTEMPS + 1; ii++)
            codeBreaker.check ("XXXX");
    }

    @Test public void correctIn15Times () {
        for (int ii = 0; ii < MAX_ATTEMPS; ii++)
            codeBreaker.check ("XXXX");
    }

    @Test public void duplicatedColors () {
        assertEquals ("XXXX", codeBreaker.checkCode ("RARR", "RARR"));
    }

    @Test public void resetCounter () {
        for (int ii = 0; ii < 5; ii++)
            codeBreaker.check ("XXXX");

        assertEquals ("XXXX", codeBreaker.check (codeBreaker.code));
        assertEquals (0, codeBreaker.attemps);
    }

    @Test (expected = IllegalArgumentException.class) public void nullInput () {
        codeBreaker.check (null);
    }

    @Test (expected = IllegalArgumentException.class) public void emptyInput () {
        codeBreaker.check ("");
    }

    @Test (expected = IllegalArgumentException.class) public void longerInput () {
        codeBreaker.check ("RAMVN");
    }
}
