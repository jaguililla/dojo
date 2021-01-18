package katayunos

import groovy.transform.CompileStatic

import static java.lang.System.nanoTime

/**
 * http://www.solveet.com/exercises/Kata-CodeBreaker/14
 *
 * TODO Extract accumulator closure to pass it to inject both times
 * def accum = { String acc, String val -> acc + val }
 */
class GCodeBreaker {
    static final char [] CODE_COLORS = [ 'R', 'M', 'A', 'V', 'N', 'I' ]
    static final int CODE_SIZE = 4, MAX_ATTEMPS = 15
    static final char COMPLETE_MATCH = 'X', COLOR_MATCH = '*'

    final String code = generateCode ()
    int attemps

    @CompileStatic String generateCode () {
        Random random = new Random (nanoTime())

        (0..<CODE_SIZE)
            .collect { CODE_COLORS [random.nextInt (CODE_COLORS.length)].toString () }
            .inject { String acc, String val -> acc + val }
    }

    @CompileStatic String checkCode (String code, String input) {
        if (input == null || input.length () != CODE_SIZE)
            throw new IllegalArgumentException ()

        (0..<CODE_SIZE).collect {
            int ii = (int)it; // Required to compile statically
            def inputChar = input.charAt (ii)
            if (code.contains (inputChar.toString ()))
                (code.charAt (ii) == inputChar? COMPLETE_MATCH : COLOR_MATCH).toString ()
            else ""
        }.inject { String acc, String val -> acc + val }
    }

    @CompileStatic String check (String input) {
        if (attemps == MAX_ATTEMPS)
            throw new IllegalStateException ()

        attemps++

        String result = checkCode code, input

        if (result.toString ().equals ("XXXX"))
            attemps = 0

        result
    }
}
