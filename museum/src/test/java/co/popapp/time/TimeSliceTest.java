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
import static co.popapp.time.TimeSlice.Interval.*;

import java.util.EnumSet;

import org.junit.Before;
import org.junit.Test;

import co.popapp.time.TimeSlice;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class TimeSliceTest {
    private TimeSlice ts0Week;
    private TimeSlice ts1Week;
    private TimeSlice ts1Week1Day;
    private TimeSlice ts1Week1Day10Ms;

    /** Test method for {@link TimeSlice#setFieldTimes(java.util.EnumSet, long[])}. */
    @Test public void setFieldTimesIntervalSetLongArray () {
        System.out.println (ts0Week);
        System.out.println (ts1Week);
        System.out.println (ts1Week1Day);
        System.out.println (ts1Week1Day10Ms);
    }

    /** Test method for {@link TimeSlice#setMillis(java.util.EnumSet, long)}. */
    @Test public void setMillisIntervalSetLong () {
        // TODO
    }

    @Before public void setUp () {
        ts0Week = new TimeSlice (EnumSet.of (WEEK));
        ts1Week = new TimeSlice (EnumSet.of (WEEK), WEEK.length);
        ts1Week1Day = new TimeSlice (EnumSet.of (WEEK, DAY), WEEK.length + DAY.length);
        ts1Week1Day10Ms =
            new TimeSlice (EnumSet.of (WEEK, DAY, MILLISECOND), new long[] { 1, 1, 10 });
    }

    /** Test method for {@link TimeSlice#toString()}. */
    @Test public void timeSliceToString () {
        // TODO
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
