/*
 * StringRectangle.java
 * Creado en: 08-jun-2005 8:13:10 por: juanjo
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.util;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////

import co.popapp.Validate;

/**
 * Texto con forma rectangular donde poder insertar cadenas (no incluye retornos de carro).
 * TODO Pendiente de documentar
 * @author jamming
 */
public class TextScreen {
    /** TODO Pendiente de documentar. */
    private static final char DEFAULT_FILL_CHARACTER = ' ';
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
    public TextScreen (int aColumns, int aRows) {
        this (aColumns, aRows, DEFAULT_FILL_CHARACTER);
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aColumns
     * @param aRows
     * @param aFillCharacter
     */
    public TextScreen (int aColumns, int aRows, char aFillCharacter) {
        super();
        Validate.isTrue (aColumns > 0, "The number of columns must be greater than zero");
        Validate.isTrue (aRows > 0, "The must exist one row at least");
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
    public TextScreen (int aColumns, String aSourceString) {
        this (aColumns, aSourceString, DEFAULT_FILL_CHARACTER);
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aColumns
     * @param aSourceString
     * @param aFillCharacter
     */
    public TextScreen (int aColumns, String aSourceString, char aFillCharacter) {
        super();
        Validate.isTrue (aColumns > 0, "The number of columns must be greater than zero");
        Validate.notNull(aSourceString, "The sourceString can't be 'null'");

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
        Validate.isTrue(aColumnIndex < mColumnCount && aColumnIndex > 0,
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
     * @param aX
     * @param aY
     * @param aW
     * @param aH
     * @return TextBuilder
     */
    /*
    public TextMatrix getText (int aX, int aY, int aW, int aH) {
        // TODO Pendiente de implementar
        return null;
    }
    */

    /**
     * .
     * TODO Pendiente de documentar
     */
    /*
    public void resize () {
        // TODO Pendiente de implementar
    }
    */

    /**
     * .
     * TODO Pendiente de documentar
     * @param aRowIndex
     * @return String
     */
    public String getRow (int aRowIndex) {
        int rowCount = getRowCount();
        Validate.isTrue(aRowIndex < rowCount && aRowIndex > 0,
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
     * TODO Controlar los parámetros, los retornos de carro, que la cadena no se salga de los bordes...
     * @param aX
     * @param aY
     * @param aString
     * @return Valor
     */
    public TextScreen insert (int aX, int aY, String aString) {
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
     * @return TextMatrix
     */
    public TextScreen insertColumn () {
        return null;
    }

    /**
     * Añade retornos de carro al final de cada línea.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuffer result = new StringBuffer (mBuffer);
        for (int ii = mColumnCount; ii < result.length(); ii += mColumnCount + 1) {
            result.insert(ii, '\n');
        }
        return result.toString();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
