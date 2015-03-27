/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.time;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static java.text.MessageFormat.*;

import java.util.EnumSet;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
class TimeSliceFormat {
    private String mFormat;

    public TimeSliceFormat (String aFormat) {
        if (aFormat == null || aFormat.length () == 0)
            throw new IllegalArgumentException ("");
        mFormat = aFormat;
    }

    public String format (TimeSlice aTimeSlice) {
        return null;
    }

    public TimeSlice parse (String aTextTimeSlice) {
        return null;
    }
}

/**
 * TODO .
 * @author jamming
 */
public class TimeSlice {
    /**
     * TODO .
     * @author jamming
     */
    public enum Interval {
        WEEK (7 * 24 * 60 * 60 * 1000, 'w'),
        DAY (24 * 60 * 60 * 1000, 'd'),
        HOUR (60 * 60 * 1000, 'h'),
        MINUTE (60 * 1000, 'm'),
        SECOND (1000, 's'),
        MILLISECOND (1, 'S');

        public final long length;
        public final char token;
        private Interval (long aLength, char aToken) {
            length = aLength;
            token = aToken;
        }
    }

    /** TODO . */
    private EnumSet<Interval> mFields;
    /** TODO . */
    private long[] mFieldTimes;
    /** TODO . */
    private long mMillis;

    /**
     * TODO .
     * @param aFields .
     */
    public TimeSlice (EnumSet<Interval> aFields) { setFields (aFields); }

    /**
     * TODO .
     * @param aFields .
     * @param aMillis .
     */
    public TimeSlice (EnumSet<Interval> aFields, long aMillis) { setMillis (aFields, aMillis); }

    /**
     * TODO .
     * @param aFields .
     * @param aFieldTimes .
     */
    public TimeSlice (EnumSet<Interval> aFields, long [] aFieldTimes) {
        setFieldTimes (aFields, aFieldTimes);
    }

    /**
     * TODO .
     * @return .
     */
    public EnumSet<Interval> getFields () { return mFields; }

    /**
     * TODO .
     * @return .
     */
    public long[] getFieldTimes () { return mFieldTimes; }

    /**
     * TODO .
     * @return .
     */
    public long getMillis () { return mMillis; }

    /**
     * TODO .
     * @param aFields .
     */
    public void setFields (EnumSet<Interval> aFields) { setMillis (aFields, mMillis); }

    /**
     * TODO .
     * @param aFields .
     * @param aFieldTimes .
     */
    public void setFieldTimes (EnumSet<Interval> aFields, long[] aFieldTimes) {
        // Parameter validation
        if (aFields == null || aFields.size () == 0 || aFieldTimes == null)
            throw new IllegalArgumentException ("The field list and field times can't be empty");
        if (aFieldTimes.length != aFields.size ())
            throw new IllegalArgumentException (
                format ("The number of fields ({0}) and the number of times ({1}) must match",
                    aFields.size (), aFieldTimes.length));

        mFields = aFields;
        mFieldTimes = aFieldTimes;
        mMillis = 0; // Will be recalculated based on field times

        // Recalculate millis
        // TODO Avoid if the instance is created (mFieldTimes == null)
        int ii = 0;
        for (Interval f : mFields) mMillis += f.length * mFieldTimes[ii++];
    }

    /**
     * TODO .
     * @param aFieldTimes .
     */
    public void setFieldTimes (long[] aFieldTimes) { setFieldTimes (mFields, aFieldTimes); }

    /**
     * TODO .
     * @param aFields .
     * @param aMillis .
     */
    public void setMillis (EnumSet<Interval> aFields, long aMillis) {
        if (aFields == null || aFields.size () == 0)
            throw new IllegalArgumentException ("The field list can't be empty");
        if (aFields.equals (mFields) && mMillis == aMillis)
            return; // If equals, don't reset the current values

        mFields = aFields;

        // If fields are changed, the field array is allocated again
        if (mFieldTimes == null || mFieldTimes.length != aFields.size ())
            mFieldTimes = new long [aFields.size ()];

        // Recalculate fields
        // TODO Avoid if the instance is created (mFieldTimes == null)
        aMillis = mMillis = Math.abs (aMillis);
        int ii = 0;
        for (Interval f : mFields) {
            mFieldTimes [ii++] = aMillis / f.length;
            aMillis %= f.length;
        }
    }

    /**
     * TODO .
     * @param aMillis .
     */
    public void setMillis (long aMillis) { setMillis (mFields, aMillis); }

    /**
     * TODO .
     * @param aFields .
     * @param aFieldTimes .
     */
    public void setTimes (EnumSet<Interval> aFields, long... aFieldTimes) {
        setFieldTimes (aFields, aFieldTimes);
    }

    /**
     * TODO .
     * @param aFieldTimes .
     */
    public void setTimes (long... aFieldTimes) { setFieldTimes (mFields, aFieldTimes); }

    /** @see Object#toString() */
    @Override public String toString () {
        StringBuilder result = new StringBuilder (128);
        int ii = 0;
        for (Interval f : mFields) {
            long fTime = mFieldTimes[ii++];
            if (ii > 1) result.append (' '); // TODO Not optimal (better iterate from position 1)
            result.append (fTime).append (' ').append (f);
            if (fTime != 1) result.append ('S');
        }
        return result.toString ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
