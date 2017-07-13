/*
 * CircularQueue.java - 19-jun-2003
 */

// P A C K A G E /////////////////////////////////////////////////////////////////////////
package co.popapp.util;

// I M P O R T ///////////////////////////////////////////////////////////////////////////

// C L A S S /////////////////////////////////////////////////////////////////////////////

import co.popapp.StringUtils;

/**
 * Implementación de una cola circular.
 * @author jamming
 */
public class CircularQueue {
    /**
     * Método auxiliar que devuelve el número de dígitos de un valor en la base especificada.
     * @param number
     * @param base
     * @return Valor.
     */
    private static int digitsOf (int number, int base) {
        int value = (number >= 0)? number : Math.abs(number);
        int ii = 0;
        do {
            value /= base;
            ii++;
        }
        while (value >= base);
        return ii;
    }
    /** Capacidad de la cola, número máximo de elementos simultaneos. */
    protected int queueCapacity = 32;
    /** Número de elementos ocupados de la cola. No puede ser mayor que <code>queueCapacity</code>. */
    protected int queueSize = 0;
    /** Array con las celdas de la cola. */
    protected Object [] elementsArray = null;
    /** Indice del array de elementos en donde se guarda el último insertado. */
    protected int headIndex = 0;

    /** Indice del array de elementos en donde se guarda el los elementos más "antiguos". */
    protected int tailIndex = 0;

    /**
     * .
     */
    public CircularQueue () {
        super();
        elementsArray = new Object [queueCapacity];
    }

    /**
     * .
     * @param initialCapacity
     */
    public CircularQueue (int initialCapacity) {
        super();
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("No se puede tener una cola si elementos o con un número negativo de ellos. Capacidad inicial: "
                + initialCapacity);
        }
        queueCapacity = initialCapacity;
        elementsArray = new Object [queueCapacity];
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param index
     * @return Valor.
     */
    protected boolean hasElement (int index) {
        if (index < 0 || index > queueCapacity) {
            throw new IllegalArgumentException("");
        }
        return index >= tailIndex && index < headIndex;
    }

    /**
     * .
     * @param element
     */
    public void add (final Object element) {
        elementsArray[headIndex] = element;
        headIndex = ++headIndex % queueCapacity;
        // Si despues de haber desplazado "headIndex" la cabeza y la cola son
        // iguales es que la lista está llena
        if (headIndex == tailIndex) {
            tailIndex = ++tailIndex % queueCapacity;
        }
        else {
            queueSize++;
        }
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return Valor.
     */
    public int capacity () {
        return queueCapacity;
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void clear () {
        queueSize = headIndex = tailIndex = 0;
    }

    /**
     *  TH
     * |*| | | | | | |
     *  0 1 2 3 4 5 6
     *
     * @return  Valor.
     */
    public Object [] elements () {
        Object [] result = null;
        int resultLength = 0;
        if (tailIndex < headIndex) {
            resultLength = headIndex - tailIndex;
            result = new Object [resultLength];
            System.arraycopy(elementsArray, tailIndex, result, 0, resultLength);
        }
        else if (tailIndex > headIndex) {
            resultLength = queueCapacity - (tailIndex - headIndex);
            result = new Object [resultLength];
            int mid = queueCapacity - tailIndex;
            System.arraycopy(elementsArray, tailIndex, result, 0, mid);
            System.arraycopy(elementsArray, 0, result, mid, headIndex);
        }
        return result;
    }

    /**
     * .
     * @return Valor.
     */
    public Object head () {
        return elementsArray[headIndex];
    }

    /**
     * .
     * @return Valor.
     */
    public Object remove () {
        if (queueSize == 0) {
            throw new IllegalStateException("Cola vacia");
        }
        Object tailValue = elementsArray[tailIndex];
        tailIndex = ++tailIndex % queueCapacity;
        queueSize--;
        return tailValue;
    }

    /**
     * .
     * @param newCapacity
     */
    public void resize (int newCapacity) {
        if (newCapacity < queueSize) {
            throw new IllegalArgumentException("No se puede asignar una capacidad menor que el numero de elementos actuales. Número de elementos: "
                + queueSize + " Nueva capacidad: " + newCapacity);
        }
        queueCapacity = newCapacity;
        Object [] auxArray = elements();
        elementsArray = new Object [newCapacity];
        tailIndex = 0;
        if (auxArray != null) {
            System.arraycopy(auxArray, 0, elementsArray, 0, auxArray.length);
            headIndex = auxArray.length;
        }
        else {
            headIndex = 0;
        }
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return Valor.
     */
    public int size () {
        return queueSize;
    }

    /**
     * .
     * @return Valor.
     */
    public Object tail () {
        return elementsArray[tailIndex];
    }

    /**
     *
     * |TH|  |  |  |  |
     *  0  1  2  3  4
     * 0: Obj.toString ()
     * 1: Obj.toString ()
     * 2: Obj.toString ()
     * 3: Obj.toString ()
     * 4: Obj.toString ()
     *
     * @todo Partir en líneas de 80 columnas
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        String [] cells = new String [queueCapacity];
        StringBuffer indexes = new StringBuffer();
        StringBuffer objects = new StringBuffer();

        // Ancho de las casillas
        int cellWidth = digitsOf(queueCapacity, 10);
        if (cellWidth < 2)
            cellWidth = 2;

        // Recorre los elementos
        for (int ii = 0; ii < queueCapacity; ii++) {
            // Dibuja el esquema
            StringBuffer cell = new StringBuffer();
            if (ii == tailIndex) {
                cell.append('T');
            }
            if (ii == headIndex) {
                cell.append('H');
            }
            char cellPad = hasElement(ii)? '*' : ' ';
//            cells[ii] = Strings.padStart (cell.toString (), cellWidth, cellPad);
//            indexes.append(Strings.padStart (String.valueOf (ii), cellWidth + 1, ' '));

            // Dibuja la lista de elementos
            if (hasElement(ii)) // se puede sustituir por (cellPad == '*') que da mas rendimiento, pero es menos claro
            {
                objects.append('\n').append(ii).append(": ").append(String.valueOf(elementsArray[ii]));
            }
        }
        indexes.append(objects);
        indexes.insert(0, StringUtils.join ("|", "|", "|\n ", cells));
        return indexes.toString();
    }

}
// E O F /////////////////////////////////////////////////////////////////////////////////
