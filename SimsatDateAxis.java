/*
 * FILE NAME
 * 
 *    SimsatDateTickUnit.java
 * 
 * ABSTRACT
 * 
 *    Overriden JFreeChart class created to workaround bug in JFreeChart.
 * 
 * MODIFICATION HISTORY
 * 
 *  <date>      <ref>    <person>       <description>
 *  25-01-2005  none     ghamilton        Created.
 *  09-08-2005  SSLNXMMI-461 gevison	Changes for Code Review.
 *
 */

package org.jfree.simsat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.axis.TickUnits;

/**
 * Overriden JFreeChart class created to workaround bug in JFreeChart. The bug
 * was a problem in method DateAxis::refreshTicksHorizontal that went into a
 * tight loop - freezing the MMI see issue SSLNXMMI-266 - when using
 * milliseconds as the tick unit. This class overrides
 * createStandardDateTickUnits to remove milliseconds as a tick unit. This class
 * should be removed when the bug is fixed.
 */
public class SimsatDateAxis extends DateAxis
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * F1 Data Format
     */
    private static final String F1_FORMAT = "HH:mm:ss.SSS";

    /**
     * F2 Data Format
     */
    private static final String F2_FORMAT = "HH:mm:ss";

    /**
     * F3 Data Format
     */
    private static final String F3_FORMAT = "HH:mm";

    /**
     * F4 Data Format
     */
    private static final String F4_FORMAT = "d-MMM, HH:mm";

    /**
     * F5 Data Format
     */
    private static final String F5_FORMAT = "d-MMM";

    /**
     * F6 Data Format
     */
    private static final String F6_FORMAT = "MMM-yyyy";

    /**
     * F7 Data Format
     */
    private static final String F7_FORMAT = "yyyy";


    /**
     * Constructor.
     * 
     * @param timeAxisLabel
     */
    public SimsatDateAxis(String timeAxisLabel)
    {
        super(timeAxisLabel);

        // reset the tick to what we want
        setStandardTickUnits(createStandardDateTickUnits(TimeZone.getDefault()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jfree.chart.axis.DateAxis#createStandardDateTickUnits(java.util.TimeZone)
     */
    public static TickUnitSource createStandardDateTickUnits(TimeZone zone)
    {
        TickUnits units = new TickUnits();

        // date formatters
        DateFormat f1 = new SimpleDateFormat(F1_FORMAT);
        DateFormat f2 = new SimpleDateFormat(F2_FORMAT);
        DateFormat f3 = new SimpleDateFormat(F3_FORMAT);
        DateFormat f4 = new SimpleDateFormat(F4_FORMAT);
        DateFormat f5 = new SimpleDateFormat(F5_FORMAT);
        DateFormat f6 = new SimpleDateFormat(F6_FORMAT);
        DateFormat f7 = new SimpleDateFormat(F7_FORMAT);

        f1.setTimeZone(zone);
        f2.setTimeZone(zone);
        f3.setTimeZone(zone);
        f4.setTimeZone(zone);
        f5.setTimeZone(zone);
        f6.setTimeZone(zone);
        f7.setTimeZone(zone);

        // milliseconds - commented out deliberately to show what is different
        // between
        // this and the base class' method
        /*
         * units.add(new DateTickUnit(DateTickUnit.MILLISECOND, 1, f1));
         * units.add(new DateTickUnit(DateTickUnit.MILLISECOND, 5,
         * DateTickUnit.MILLISECOND, 1, f1)); units.add(new
         * DateTickUnit(DateTickUnit.MILLISECOND, 10, DateTickUnit.MILLISECOND,
         * 1, f1)); units.add(new DateTickUnit(DateTickUnit.MILLISECOND, 25,
         * DateTickUnit.MILLISECOND, 5, f1)); units.add(new
         * DateTickUnit(DateTickUnit.MILLISECOND, 50, DateTickUnit.MILLISECOND,
         * 10, f1)); units.add( new DateTickUnit(DateTickUnit.MILLISECOND, 100,
         * DateTickUnit.MILLISECOND, 10, f1) ); units.add( new
         * DateTickUnit(DateTickUnit.MILLISECOND, 250, DateTickUnit.MILLISECOND,
         * 10, f1) ); units.add( new DateTickUnit(DateTickUnit.MILLISECOND, 500,
         * DateTickUnit.MILLISECOND, 50, f1) );
         */

        /*
         * Code review comment: It makes no sense to make the literal values in
         * the following code proper constants of the class or the method. The
         * values are only used here and no where else and would make more sense
         * to anyone maintaining this code for them to be left as they are.
         */

        // seconds
        units.add(new DateTickUnit(DateTickUnit.SECOND, 1, DateTickUnit.MILLISECOND, 50, f2));
        units.add(new DateTickUnit(DateTickUnit.SECOND, 5, DateTickUnit.SECOND, 1, f2));
        units.add(new DateTickUnit(DateTickUnit.SECOND, 10, DateTickUnit.SECOND, 1, f2));
        units.add(new DateTickUnit(DateTickUnit.SECOND, 30, DateTickUnit.SECOND, 5, f2));

        // minutes
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 1, DateTickUnit.SECOND, 5, f3));
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 2, DateTickUnit.SECOND, 10, f3));
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 5, DateTickUnit.MINUTE, 1, f3));
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 10, DateTickUnit.MINUTE, 1, f3));
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 15, DateTickUnit.MINUTE, 5, f3));
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 20, DateTickUnit.MINUTE, 5, f3));
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 30, DateTickUnit.MINUTE, 5, f3));

        // hours
        units.add(new DateTickUnit(DateTickUnit.HOUR, 1, DateTickUnit.MINUTE, 5, f3));
        units.add(new DateTickUnit(DateTickUnit.HOUR, 2, DateTickUnit.MINUTE, 10, f3));
        units.add(new DateTickUnit(DateTickUnit.HOUR, 4, DateTickUnit.MINUTE, 30, f3));
        units.add(new DateTickUnit(DateTickUnit.HOUR, 6, DateTickUnit.HOUR, 1, f3));
        units.add(new DateTickUnit(DateTickUnit.HOUR, 12, DateTickUnit.HOUR, 1, f4));

        // days
        units.add(new DateTickUnit(DateTickUnit.DAY, 1, DateTickUnit.HOUR, 1, f5));
        units.add(new DateTickUnit(DateTickUnit.DAY, 2, DateTickUnit.HOUR, 1, f5));
        units.add(new DateTickUnit(DateTickUnit.DAY, 7, DateTickUnit.DAY, 1, f5));
        units.add(new DateTickUnit(DateTickUnit.DAY, 15, DateTickUnit.DAY, 1, f5));

        // months
        units.add(new DateTickUnit(DateTickUnit.MONTH, 1, DateTickUnit.DAY, 1, f6));
        units.add(new DateTickUnit(DateTickUnit.MONTH, 2, DateTickUnit.DAY, 1, f6));
        units.add(new DateTickUnit(DateTickUnit.MONTH, 3, DateTickUnit.MONTH, 1, f6));
        units.add(new DateTickUnit(DateTickUnit.MONTH, 4, DateTickUnit.MONTH, 1, f6));
        units.add(new DateTickUnit(DateTickUnit.MONTH, 6, DateTickUnit.MONTH, 1, f6));

        // years
        units.add(new DateTickUnit(DateTickUnit.YEAR, 1, DateTickUnit.MONTH, 1, f7));
        units.add(new DateTickUnit(DateTickUnit.YEAR, 2, DateTickUnit.MONTH, 3, f7));
        units.add(new DateTickUnit(DateTickUnit.YEAR, 5, DateTickUnit.YEAR, 1, f7));
        units.add(new DateTickUnit(DateTickUnit.YEAR, 10, DateTickUnit.YEAR, 1, f7));
        units.add(new DateTickUnit(DateTickUnit.YEAR, 25, DateTickUnit.YEAR, 5, f7));
        units.add(new DateTickUnit(DateTickUnit.YEAR, 50, DateTickUnit.YEAR, 10, f7));
        units.add(new DateTickUnit(DateTickUnit.YEAR, 100, DateTickUnit.YEAR, 20, f7));

        return units;
    }
}
