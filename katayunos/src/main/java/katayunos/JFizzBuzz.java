package katayunos;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.solveet.com/exercises/Kata-FizzBuzz/11
 */
public class JFizzBuzz {
    public static List<String> fizzBuzz () {
        List<String> result = new ArrayList<String> (100);

        for (int ii = 1; ii <= 100; ii++) {
            String strNumber = String.valueOf (ii);
            boolean mod3 = ii % 3 == 0, mod5 = ii % 5 == 0;
            boolean has3 = strNumber.contains ("3"), has5 = strNumber.contains ("5");

            if ((mod3 && mod5) || (has3 && has5))
                result.add ("FizzBuzz");
            else if ((mod3) || (has3))
                result.add ("Fizz");
            else if ((mod5) || (has5))
                result.add ("Buzz");
            else
                result.add (strNumber);
        }

        return result;
    }
}
