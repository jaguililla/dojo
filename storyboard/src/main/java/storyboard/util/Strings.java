package storyboard.util;

import static java.lang.Character.toUpperCase;
import static java.lang.System.getProperty;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

/**
 * TODO .
 *
 * @author jam
 */
public class Strings {
    public static final String EOL = getProperty ("line.separator");
    public static final int DEFAULT_INDENT = 2;

    public static String capitalize (Object line) {
        if (line == null)
            return "";

        String result = String.valueOf (line);
        return result.length () > 1?
            toUpperCase (result.charAt (0)) + result.substring (1).toLowerCase () :
            result.toUpperCase ();
    }

    public static String replace (Map<String, Object> example, String text) {
        if (text == null)
            return "";
        if (example == null || example.isEmpty ())
            return text;

        String result = text;

        for (Entry<String, Object> e : example.entrySet ())
            result = result.replace ('<' + e.getKey () + '>', String.valueOf (e.getValue ()));

        return result;
    }

    public static String unindent (Object str) {
        StringBuilder b = new StringBuilder ();
        for (String s : str.toString ().split (EOL))
            b.append (s.trim ()).append (EOL);
        return b.toString ();
    }

    public static String indent (Object str) {
        String indent = "";
        for (int ii = 0; ii < DEFAULT_INDENT; ii++)
            indent += ' ';

        StringBuilder b = new StringBuilder ();
        for (String s : str.toString().split (EOL))
            b.append (indent).append (s).append (EOL);

        return b.toString ();
    }

    public static String indent (Object str, int spaces) {
        String indent = "";
        for (int ii = 0; ii < spaces; ii++)
            indent += ' ';

        StringBuilder b = new StringBuilder ();
        for (String s : str.toString().split (EOL))
            b.append (indent).append (s).append (EOL);

        return b.toString ();
    }

    public static String hangingIndent (Object str) {
        return hangingIndent (str, 1, DEFAULT_INDENT);
    }

    public static String hangingIndent (Object str, int spaces) {
        return hangingIndent (str, 1, spaces);
    }

    public static String hangingIndent (Object str, int lines, int spaces) {
        int eol = 0;
        for (int ii = 0; ii < lines; ii++)
            eol = str.toString().indexOf (EOL, eol) + 1;

        return str.toString().substring (0, eol) + indent (str.toString().substring (eol), spaces);
    }

    public static int longest (List<String> strings) {
        return strings
            .stream ()
            .filter (s -> s != null && !s.isEmpty ())
            .map (String::length)
            .max ((a, b) -> {
                if (a > b)
                    return 1;
                else if (a < b)
                    return -1;
                else
                    return 0;
            })
            .orElse (0);
    }

    public static String pad (String string, boolean start, char pad, int length) {
        if (string == null || length < 0)
            throw new IllegalArgumentException ();

        String padding = "";
        for (int ii = 0; ii < length - string.length (); ii++)
            padding += pad;

        return start? string + padding : padding + string;
    }

    public static String pad (String string, char pad, int length) {
        return pad (string, true, pad, length);
    }

    public static String pad (String string, int length) {
        return pad (string, true, ' ', length);
    }

    public static String padStart (String string, char pad, int length) {
        return pad (string, true, pad, length);
    }

    public static String padEnd (String string, char pad, int length) {
        return pad (string, false, pad, length);
    }

    public static String padStart (String string, int length) {
        return pad (string, true, ' ', length);
    }

    public static String padEnd (String string, int length) {
        return pad (string, false, ' ', length);
    }
}
