package codebreaker.java;

import static codebreaker.java.CodeBreaker.CODE_COLORS;
import static codebreaker.java.CodeBreaker.CODE_SIZE;
import static codebreaker.java.CodeBreaker.MAX_ATTEMPS;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class CodeBreakerTest {
    private CodeBreaker codeBreaker = new CodeBreaker ();

    @Test public void testCodeGeneratedHas4Chars () {
        Assert.assertTrue (CODE_SIZE == codeBreaker.generateCode ().length ());
    }

    @Test public void testCodeGeneratedHasValidCodes () {
        for (char c : codeBreaker.generateCode ().toCharArray ())
            assertTrue (asList (CODE_COLORS).contains (c));
    }

    @Test public void testInputNotColor () {
        String input = "XXXX";
        String code = codeBreaker.check (input);
        assertEquals ("", code);
    }

    @Test public void testOneCorrectColorIncorrectPosition () {
        assertEquals ("*", codeBreaker.checkCode ("MAVN", "NRRR"));
    }

    @Test public void testManyCorrectColorIncorrectPosition () {
        assertEquals ("**", codeBreaker.checkCode ("MAVN", "AMRR"));
    }

    @Test public void testOneCorrectColorCorrectPosition () {
        assertEquals ("X", codeBreaker.checkCode ("MAVN", "MRRR"));
    }

    @Test public void testManyCorrectColorCorrectPositions () {
        assertEquals ("XX", codeBreaker.checkCode ("MAVN", "MARR"));
    }

    @Test (expected = IllegalStateException.class) public void testFailInMoreThan15Times () {
        for (int ii = 0; ii < MAX_ATTEMPS + 1; ii++)
            codeBreaker.check ("XXXX");
    }

    @Test public void testCorrectIn15Times () {
        for (int ii = 0; ii < MAX_ATTEMPS; ii++)
            codeBreaker.check ("XXXX");
    }

    @Test public void testDuplicatedColors () {
        assertEquals ("XXXX", codeBreaker.checkCode ("RARR", "RARR"));
    }

    @Test public void testResetCounter () {
        for (int ii = 0; ii < 5; ii++)
            codeBreaker.check ("XXXX");

        assertEquals ("XXXX", codeBreaker.check (codeBreaker.code));
        assertEquals (0, codeBreaker.attemps);
    }

    @Test (expected = IllegalArgumentException.class) public void testNullInput () {
        codeBreaker.check (null);
    }

    @Test (expected = IllegalArgumentException.class) public void testEmptyInput () {
        codeBreaker.check ("");
    }

    @Test (expected = IllegalArgumentException.class) public void testLongerInput () {
        codeBreaker.check ("RAMVN");
    }
}
