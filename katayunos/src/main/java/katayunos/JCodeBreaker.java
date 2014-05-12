package katayunos;

import static java.lang.System.nanoTime;

import java.util.Random;

/**
 * http://www.solveet.com/exercises/Kata-CodeBreaker/14
 */
public class JCodeBreaker {
    static final char [] CODE_COLORS = { 'R', 'M', 'A', 'V', 'N', 'I' };
    static final int CODE_SIZE = 4, MAX_ATTEMPS = 15;
    static final char COMPLETE_MATCH = 'X', COLOR_MATCH = '*';

    final String code = generateCode ();
    int attemps;

    String generateCode () {
        StringBuilder result = new StringBuilder ();
        Random random = new Random (nanoTime ());

        for (int ii = 0; ii < CODE_SIZE; ii++)
            result.append (CODE_COLORS [random.nextInt (CODE_COLORS.length)]);

        return result.toString ();
    }

    String checkCode (String code, String input) {
        if (input == null || input.length () != CODE_SIZE)
            throw new IllegalArgumentException ();

        StringBuilder result = new StringBuilder ();

        for (int ii = 0; ii < CODE_SIZE; ii++)
            if (code.contains (String.valueOf (input.charAt (ii))))
                result.append (
                    code.charAt (ii) == input.charAt (ii)? COMPLETE_MATCH : COLOR_MATCH);

        return result.toString ();
    }

    String check (String input) {
        if (attemps == MAX_ATTEMPS)
            throw new IllegalStateException ();

        attemps++;

        String result = checkCode (code, input);

        if (result.toString ().equals ("XXXX"))
            attemps = 0;

        return result;
    }
}
