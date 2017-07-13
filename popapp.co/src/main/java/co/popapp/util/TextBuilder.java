/*
 * StringRectangle.java
 * Creado en: 08-jun-2005 8:13:10 por: juanjo
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.util;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////

import co.popapp.StringUtils;
import co.popapp.Validate;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * Texto con forma rectangular donde poder insertar cadenas (no incluye retornos de carro).
 * TODO Pendiente de documentar
 * @author jamming
 */
public class TextBuilder {
    /** TODO Pendiente de documentar. */
    private static final char DEFAULT_FILL_CHARACTER = ' ';
    /**
     * .
     * @param tableData
     */
    protected static void checkTableData (final String [][] tableData) {
        if (tableData == null || tableData.length == 0) {
            throw new IllegalArgumentException("");
        }
        int columns = tableData[0].length;
        for (int ii = 0; ii < tableData.length; ii++) {
            if (tableData[ii].length != columns) {
                throw new IllegalArgumentException("");
            }
        }
    }
    /**
     *
     * @param tableData
     * @return Valor.
     */
    protected static int [] columnsWidth (final String [][] tableData) {
        int [] result = new int [tableData[0].length];
        // Columnas
        for (int ii = 0; ii < tableData[0].length; ii++) {
            // Filas
            for (int jj = 0; jj < tableData.length; jj++) {
                if (result[ii] < tableData[jj][ii].length()) {
                    result[ii] = tableData[jj][ii].length();
                }
            }
        }
        return result;
    }

    /**
     * PENDIENTE !!!
     * @param lineData
     * @param widths
     * @param separator
     * @return Valor.
     */
    protected static String tableLine (final String [] lineData, final int [] widths, final String separator) {
        StringBuffer result = new StringBuffer(StringUtils.padStart (lineData[0], widths[0], ' '));
        for (int ii = 1; ii < lineData.length; ii++) {
            result.append(separator);
            result.append(StringUtils.padStart (lineData[ii], widths[ii], ' '));
        }
        return result.toString();
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param sourceString
     * @param horizontal
     * @param vertical
     * @param padChar
     * @return Valor.
     */
    public static String boxText (final String sourceString, final String horizontal, final String vertical, final char padChar) {
        String [] lines = StringUtils.split (sourceString, '\n');
        int max = 0;
        for (int ii = 0; ii < lines.length; ii++) {
            if (lines[ii].length() > max) {
                max = lines[ii].length();
            }
        }
        for (int ii = 0; ii < lines.length; ii++) {
            lines[ii] = vertical + StringUtils.padStart (lines[ii], max, padChar) + vertical;
        }
        String horizontalBorder = StringUtils.repeat(horizontal, lines[0].length()
            / horizontal.length()).substring(0, lines[0].length());

        StringBuffer result = new StringBuffer(horizontalBorder);
        result.append(StringUtils.join("\n", "\n", "\n", lines)).append(horizontalBorder);
        return result.toString();
    }

    /**
     * Punto de entrada a la aplicación (primer método en ejecutarse). El valor
     * de retorno devuelto al SO se determina con 'System.exit(<<codigo>>)'.
     * @param args Argumentos de la línea de comandos. Pasados por el SO.
     */
    public static void main (String [] args) {
        TextBuilder t = new TextBuilder (10, 10);
        t.insert(0, 0, "Hola\nprueba\n1");
        System.out.println(t.toString() + "*");
        System.out.println("0123456789");
    }

    /**
     *
     * @param tableData
     * @param columnSeparator
     * @param headerSeparator
     * @return Valor.
     */
    public static String textTable (final String [][] tableData, final String columnSeparator, final String headerSeparator) {
        checkTableData(tableData);
        StringBuffer result = new StringBuffer();
        int [] widths = columnsWidth(tableData);
        String theColumnSeparator = (columnSeparator == null)? "" : new String(columnSeparator);
        result.append(tableLine(tableData[0], widths, theColumnSeparator));
        if (headerSeparator != null && !headerSeparator.equals("")) {
            result.append('\n' + StringUtils.repeat(headerSeparator, result.length()).substring(0, result.length()));
        }
        for (int ii = 1; ii < tableData.length; ii++) {
            result.append('\n');
            result.append(tableLine(tableData[ii], widths, theColumnSeparator));
        }
        return result.toString();
    }

    /** TODO Pendiente de documentar. */
    private StringBuffer mBuffer = new StringBuffer();

    /** TODO Pendiente de documentar. */
    private int mColumnCount = 0;

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aColumns
     * @param aRows
     */
    public TextBuilder (int aColumns, int aRows) {
        this (aColumns, aRows, DEFAULT_FILL_CHARACTER);
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aColumns
     * @param aRows
     * @param aFillCharacter
     */
    public TextBuilder (int aColumns, int aRows, char aFillCharacter) {
        super();
        Validate.greaterThan (aColumns, 0, "The number of columns must be greater than zero");
        Validate.greaterThan(aRows, 0, "The must exist one row at least");
        mColumnCount = aColumns;
        for (int ii = 0; ii < (aColumns * aRows); ii++) {
            mBuffer.append(aFillCharacter);
        }
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aColumns
     * @param aSourceString
     */
    public TextBuilder (int aColumns, String aSourceString) {
        this (aColumns, aSourceString, DEFAULT_FILL_CHARACTER);
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aColumns
     * @param aSourceString
     * @param aFillCharacter
     */
    public TextBuilder (int aColumns, String aSourceString, char aFillCharacter) {
        super();
        Validate.greaterThan(aColumns, 0, "The number of columns must be greater than zero");
        Validate.notNull (
            aSourceString, "The sourceString can't be 'null'");

        mColumnCount = aColumns;
        mBuffer.append(aSourceString);
        int missing = aColumns - (aSourceString.length() % aColumns);
        for (int ii = 0; ii < missing; ii++) {
            mBuffer.append(aFillCharacter);
        }
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aX
     * @param aY
     * @return Indice
     */
    private int offset (int aX, int aY) {
        return (mColumnCount * aY) + aX;
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void clear () {
        clear (DEFAULT_FILL_CHARACTER);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aFillCharacter
     */
    public void clear (char aFillCharacter) {
        for (int ii = 0; ii < mBuffer.length(); ii++) {
            mBuffer.setCharAt(ii, aFillCharacter);
        }
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aX
     * @param aY
     * @return char
     */
    public char getChar (int aX, int aY) {
        return mBuffer.charAt(offset (aX, aY));
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aColumnIndex
     * @return String
     */
    public String getColumn (int aColumnIndex) {
        Validate.inRange(aColumnIndex, mColumnCount, 0,
            "Column index out of bounds [" + aColumnIndex + "]. Must be 0 - " + mColumnCount);
        StringBuffer result = new StringBuffer ();
        int rowCount = getRowCount();
        for (int ii = 0, jj = aColumnIndex; ii < rowCount; ii++, jj += mColumnCount) {
            result.append (mBuffer.charAt(jj));
        }
        return result.toString();
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return Valor.
     */
    public int getColumnCount () {
        return mColumnCount;
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return String[]
     */
    public String [] getColumns () {
        String [] result = new String [mColumnCount];
        for (int ii = 0; ii < getColumnCount(); ii++) {
            result [ii] = getColumn(ii);
        }
        return result;
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aRowIndex
     * @return String
     */
    public String getRow (int aRowIndex) {
        int rowCount = getRowCount();
        Validate.inRange(aRowIndex, rowCount, 0,
            "Column index out of bounds [" + aRowIndex + "]. Must be 0 - " + rowCount);
        int begin = offset (0, aRowIndex);
        return mBuffer.substring(begin, begin + mColumnCount);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return Valor.
     */
    public int getRowCount () {
        return mBuffer.length() / mColumnCount;
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return String[]
     */
    public String [] getRows () {
        int rowCount = getRowCount();
        String [] result = new String [rowCount];
        for (int ii = 0; ii < rowCount; ii++) {
            result [ii] = getRow (ii);
        }
        return result;
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aX
     * @param aY
     * @param aW
     * @param aH
     * @return TextBuilder
     */
    public TextBuilder getText (int aX, int aY, int aW, int aH) {
        return null;
    }

    /**
     * .
     * TODO Pendiente de documentar
     * TODO Controlar los parámetros, los retornos de carro, que la cadena no se salga de los bordes...
     * @param aX
     * @param aY
     * @param aString
     * @return Valor
     */
    public TextBuilder insert (int aX, int aY, String aString) {
        // Cadena
        int lineStart = 0;
        int lineBreak = aString.indexOf('\n');
        int lineEnd = (lineBreak == -1)? aString.length() : lineBreak;
        String line = aString.substring(lineStart, lineEnd);

        // Area
        int areaStart = offset(aX, aY);
        int areaEnd = areaStart + (lineEnd - lineStart);

        // Sustitución
        mBuffer.replace(areaStart, areaEnd, line);

        while (lineBreak != -1) {
            // Cadena
            lineStart = lineBreak + 1;
            lineBreak = aString.indexOf('\n', lineStart);
            lineEnd = (lineBreak == -1)? aString.length() : lineBreak;
            line = aString.substring(lineStart, lineEnd);

            // Area
            areaStart = offset(aX, ++aY);
            areaEnd = areaStart + (lineEnd - lineStart);

            // Sustitución
            mBuffer.replace(areaStart, areaEnd, line);
        }
        return this;
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return TextBuilder
     */
    public TextBuilder insertColumn () {
        return null;
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void resize () {
        // TODO Pendiente de implementar
    }

    /**
     * Añade retornos de carro al final de cada línea.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuffer result = new StringBuffer (mBuffer.toString());
        for (int ii = mColumnCount; ii < result.length(); ii += mColumnCount + 1) {
            result.insert(ii, '\n');
        }
        return result.toString();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
