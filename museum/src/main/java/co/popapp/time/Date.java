/*
 *
 */

// P A C K A G E /////////////////////////////////////////////////////////////////////////
package co.popapp.time;

// I M P O R T ///////////////////////////////////////////////////////////////////////////
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

// C L A S S /////////////////////////////////////////////////////////////////////////////
/**
 * @author jamming
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Date extends java.util.Date {
    /** TODO Pendiente de documentar. */
    public static final long MILLIS_MIN = 60000;
    /** TODO Pendiente de documentar. */
    public static final long MILLIS_HOUR = 60 * MILLIS_MIN;
    /** TODO Pendiente de documentar. */
    public static final long MILLIS_DAY = 24 * MILLIS_HOUR;

    /**
     * No es estatico porque en una aplicación (Servidor por ejemplo) puede
     * haber varios clientes de partes distintas del mundo.
     */
    protected Calendar dateCalendar = null;

    /**
     * Constructor for IDate.
     */
    public Date () {
        super();
        dateCalendar = Calendar.getInstance();
    }

    /**
     * Constructor for IDate.
     * @param location
     */
    public Date (Locale location) {
        super();
        dateCalendar = Calendar.getInstance(location);
    }

    /**
     * Constructor for IDate.
     * @param arg0
     */
    public Date (long arg0) {
        super(arg0);
        dateCalendar = Calendar.getInstance();
    }

    /**
     * @see java.util.Calendar#add(int, int)
     */
    public void add (int aField, int amount) {
        // TODO Bloque generado automáticamente
        dateCalendar.add (aField, amount);
    }

    /**
     * @see java.util.Calendar#after(java.lang.Object)
     */
    public boolean after (Object aWhen) {
        // TODO Bloque generado automáticamente
        return dateCalendar.after (aWhen);
    }

    /**
     * @see java.util.Calendar#before(java.lang.Object)
     */
    public boolean before (Object aWhen) {
        // TODO Bloque generado automáticamente
        return dateCalendar.before (aWhen);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return Valor.
     */
    public Calendar calendar () {
        return dateCalendar;
    }

    /**
     * @see java.util.Calendar#clone()
     */
    @Override
    public Object clone () {
        // TODO Bloque generado automáticamente
        return dateCalendar.clone ();
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return Valor.
     */
    public Date dayTime () {
        return new Date(getTime() % MILLIS_DAY);
    }

    /**
     * @see java.util.Calendar#equals(java.lang.Object)
     */
    @Override
    public boolean equals (Object aObj) {
        // TODO Bloque generado automáticamente
        return dateCalendar.equals (aObj);
    }

    /**
     * @see java.util.Calendar#get(int)
     */
    public int get (int aField) {
        // TODO Bloque generado automáticamente
        return dateCalendar.get (aField);
    }

    /**
     * @see java.util.Calendar#getActualMaximum(int)
     */
    public int getActualMaximum (int aField) {
        // TODO Bloque generado automáticamente
        return dateCalendar.getActualMaximum (aField);
    }

    /**
     * @see java.util.Calendar#getActualMinimum(int)
     */
    public int getActualMinimum (int aField) {
        // TODO Bloque generado automáticamente
        return dateCalendar.getActualMinimum (aField);
    }

    /**
     * @see java.util.Calendar#compareTo(java.util.Calendar)
     */
    /*
    public int compareTo (Calendar anotherCalendar) {
        // TODO Bloque generado automáticamente
        return dateCalendar.compareTo (anotherCalendar);
    }
    */

    /**
     * @see java.util.Calendar#getFirstDayOfWeek()
     */
    public int getFirstDayOfWeek () {
        // TODO Bloque generado automáticamente
        return dateCalendar.getFirstDayOfWeek ();
    }

    /**
     * @see java.util.Calendar#getGreatestMinimum(int)
     */
    public int getGreatestMinimum (int aField) {
        // TODO Bloque generado automáticamente
        return dateCalendar.getGreatestMinimum (aField);
    }

    /**
     * @see java.util.Calendar#getLeastMaximum(int)
     */
    public int getLeastMaximum (int aField) {
        // TODO Bloque generado automáticamente
        return dateCalendar.getLeastMaximum (aField);
    }

    /**
     * @see java.util.Calendar#getMaximum(int)
     */
    public int getMaximum (int aField) {
        // TODO Bloque generado automáticamente
        return dateCalendar.getMaximum (aField);
    }

    /**
     * @see java.util.Calendar#getMinimalDaysInFirstWeek()
     */
    public int getMinimalDaysInFirstWeek () {
        // TODO Bloque generado automáticamente
        return dateCalendar.getMinimalDaysInFirstWeek ();
    }

    /**
     * @see java.util.Calendar#getMinimum(int)
     */
    public int getMinimum (int aField) {
        // TODO Bloque generado automáticamente
        return dateCalendar.getMinimum (aField);
    }

    /**
     * @see java.util.Calendar#getTimeZone()
     */
    public TimeZone getTimeZone () {
        // TODO Bloque generado automáticamente
        return dateCalendar.getTimeZone ();
    }

    /**
     * @see java.util.Calendar#hashCode()
     */
    @Override
    public int hashCode () {
        // TODO Bloque generado automáticamente
        return dateCalendar.hashCode ();
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param date
     * @return Valor.
     */
    public Date intervalWith (java.util.Date date) {
        return new Date(Math.abs(getTime() - date.getTime()));
    }

    /**
     * @see java.util.Calendar#isLenient()
     */
    public boolean isLenient () {
        // TODO Bloque generado automáticamente
        return dateCalendar.isLenient ();
    }

    /**
     * @see java.util.Calendar#getTimeInMillis()
     */
    /*
    public long getTimeInMillis () {
        // TODO Bloque generado automáticamente
        return dateCalendar.getTimeInMillis ();
    }
    */

    /**
     * @see java.util.Calendar#roll(int, boolean)
     */
    public void roll (int aField, boolean aUp) {
        // TODO Bloque generado automáticamente
        dateCalendar.roll (aField, aUp);
    }

    /**
     * @see java.util.Calendar#roll(int, int)
     */
    public void roll (int aField, int amount) {
        // TODO Bloque generado automáticamente
        dateCalendar.roll (aField, amount);
    }

    /**
     * @see java.util.Calendar#set(int, int)
     */
    public void set (int aField, int aValue) {
        // TODO Bloque generado automáticamente
        dateCalendar.set (aField, aValue);
    }

    /**
     * @see java.util.Calendar#setFirstDayOfWeek(int)
     */
    public void setFirstDayOfWeek (int aValue) {
        // TODO Bloque generado automáticamente
        dateCalendar.setFirstDayOfWeek (aValue);
    }

    /**
     * @see java.util.Calendar#setLenient(boolean)
     */
    public void setLenient (boolean aLenient) {
        // TODO Bloque generado automáticamente
        dateCalendar.setLenient (aLenient);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param location
     */
    public void setLocale (Locale location) {
        dateCalendar = Calendar.getInstance(location);
    }

    /**
     * @see java.util.Calendar#setMinimalDaysInFirstWeek(int)
     */
    public void setMinimalDaysInFirstWeek (int aValue) {
        // TODO Bloque generado automáticamente
        dateCalendar.setMinimalDaysInFirstWeek (aValue);
    }

    /**
     * @see java.util.Calendar#setTimeZone(java.util.TimeZone)
     */
    public void setTimeZone (TimeZone aValue) {
        // TODO Bloque generado automáticamente
        dateCalendar.setTimeZone (aValue);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return Valor.
     */
    public java.sql.Date sqlDate () {
        return new java.sql.Date(getTime());
    }

    /**
     * @see java.util.Calendar#setTimeInMillis(long)
     */
    /*
    public void setTimeInMillis (long aMillis) {
        // TODO Bloque generado automáticamente
        dateCalendar.setTimeInMillis (aMillis);
    }
    */

    /**
     * .
     * TODO Pendiente de documentar
     * @return Valor.
     */
    public java.sql.Time sqlTime () {
        return new java.sql.Time(getTime());
    }

    /**
     * @see java.util.Calendar#toString()
     */
    @Override
    public String toString () {
        // TODO Bloque generado automáticamente
        return dateCalendar.toString ();
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    /*
    public int compareTo (Object arg0) {
        // TODO Bloque generado automáticamente
        return dateCalendar.compareTo (arg0);
    }
    */
}
// E O F /////////////////////////////////////////////////////////////////////////////////
