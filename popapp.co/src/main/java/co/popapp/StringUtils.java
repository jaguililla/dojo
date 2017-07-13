// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////

import static java.util.Arrays.asList;

import java.util.*;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class StringUtils {
    public static String join (String start, String middle, String end, List<String> lines) {
        StringJoiner joiner = new StringJoiner (start, middle, end);
        lines.forEach (joiner::add);
        return joiner.toString ();
    }

    public static String join (String start, String middle, String end, String... lines) {
        return join (start, middle, end, asList(lines));
    }

    public static String repeat (String str, int times) {
        StringBuilder sb = new StringBuilder (str.length () * times);

        for (int ii = 0; ii < times; ii++)
            sb.append (str);

        return sb.toString ();
    }

    /**
     *
     * @param sourceString
     * @param expandString
     * @return Valor.
     */
    public static String expand (final String sourceString, final String expandString) {
        StringBuffer result = new StringBuffer(sourceString.charAt(0));
        for (int ii = 1; ii < sourceString.length(); ii++) {
            result.append(expandString);
            result.append(sourceString.charAt(ii));
        }
        return result.toString();
    }

    /**
     * Indenta todas las líneas de una cadena de texto usando una cadena de
     * texto como relleno el número de veces que se indique.
     * @param sourceString Cadena cuyas líneas serán indentadas.
     * @param padString Cadena que se añadirá al principio de cada línea.
     * @param times Número de veces que se añadirá la cadena de relleno.
     * @return Cadena con todas sus líneas indentadas.
     */
    public static String indent (final String sourceString, final String padString, final int times) {
        StringTokenizer lineTokenizer = new StringTokenizer(sourceString, "\n");
        StringBuffer result = new StringBuffer();
        String appendString = repeat (padString, times);
        while (lineTokenizer.hasMoreTokens()) {
            result.append(appendString);
            result.append(lineTokenizer.nextToken());
            result.append('\n');
        }
        return result.toString();
    }

    /**
     * <p>Splits the provided text into an array of strings of equal size (except the last one,
     * which may be shorter). If the "cutting length" is larger than the string lenght only a
     * single element array will be returned containing the whole text.</p>
     *
     * <p>The last string doesn't fill to match the length.</p>
     *
     * <pre>
     * StringUtils.split("Test String", 2) = ["Te", "st", " S", "tr", "in", "g"]
     * StringUtils.split(null, *)          = IllegalArgumentException
     * StringUtils.split("", *)            = [""]
     * StringUtils.split(*, < 1)           = IllegalArgumentException
     * </pre>
     *
     * TODO Documentar
     *
     * @param sourceString The string to cut. Can't be <code>null<code>.
     * @param length The lenght of the resulting elements. Can't be negative neither zero.
     * @return Array with the resulting elements.
     */
    public static String [] split (final String sourceString, final int length) {
        List strings = new ArrayList();
        int begin = 0;
        int end = sourceString.length() % length;
        while (end != -1) {
            strings.add(sourceString.substring(begin, end));
            begin = end;
            end = ((sourceString.length() - begin) % length) + begin;
        }
        String [] resultStrings = new String [strings.size()];
        strings.toArray(resultStrings);
        return resultStrings;
    }

    /**
     * Trocea una cadena por las posiciones indicadas.
     * TODO Controlar la longitud de la cadena y las de los campos para evitar desbordamientos.
     * @param source
     * @param positions Array con las posiciones de los campos.
     * @return Array con los valores.
     */
    public static String [] split (String source, int [] positions) {
        Validate.notNull (source, "The source string can't be 'null'");
        Validate.notNull (positions, "The positions array can't be 'null'");

        int total = 0;
        for (int ii = 0; ii < positions.length; ii++) {
            total += positions[ii];
        }
        if (total > source.length()) {
            throw new IllegalArgumentException ("La longitud de los campos '" + total + "' sobrepasa la de la cadena '" + source + "' [" + source.length() + "]");
        }

        String [] result = new String [positions.length];
        for (int ii = 0, index = 0; ii < positions.length; ii++) {
            result[ii] = source.substring (index, index + positions[ii]);
            index += positions[ii];
        }

        return result;
    }

    /**
     * .
     * @param source
     * @param positions
     * @param keys
     * @return Valor
     */
    public static Map split (String source, int [] positions, String [] keys) {
        Map result = new HashMap ();
        String [] values = split (source, positions);
        for (int ii = 0; ii < values.length; ii++) {
            result.put (keys[ii], values[ii]);
        }
        return result;
    }

    /**
     *
     * @param sourceString
     * @param padString
     * @param times
     * @return Valor.
     */
    public static String unindent (final String sourceString, final String padString, final int times) {
        StringTokenizer lineTokenizer = new StringTokenizer(sourceString, "\n");
        StringBuffer result = new StringBuffer();
        while (lineTokenizer.hasMoreTokens()) {
            StringBuffer currentLine = new StringBuffer(lineTokenizer.nextToken());
            for (int ii = 0; ii < times && currentLine.toString().startsWith(padString); ii++) {
                currentLine.delete(0, padString.length());
            }
            result.append(currentLine);
        }
        return result.toString();
    }

    private StringUtils () {
        throw new IllegalStateException ();
    }

    public static String padStart (String line, int max, char padChar) {
        throw new UnsupportedOperationException();
    }
}
// E O F //////////////////////////////////////////////////////////////////////////////////////
