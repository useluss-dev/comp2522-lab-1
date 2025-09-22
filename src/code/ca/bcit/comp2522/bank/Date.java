package ca.bcit.comp2522.bank;

/**
 * Represents a calendar date with day, month, and year values.
 *
 * <p>The {@code Date} class provides:</p>
 * <ul>
 *     <li>Validation to ensure all dates fall between the year 1800 and the current year (2025).</li>
 *     <li>Support for retrieving day, month, and year values individually.</li>
 *     <li>Formatted output in {@code YYYY-MM-DD} format.</li>
 *     <li>Computation of the day of the week using month codes and century offsets.</li>
 * </ul>
 *
 * <p>Dates are immutable once constructed.</p>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class Date
{
    private static final int CURRENT_YEAR = 2025;
    private static final int YEAR_1800 = 1800;
    private static final int YEAR_1900 = 1900;
    private static final int YEAR_2000 = 2000;

    // --- Constants for months ---
    private static final int MONTHS_IN_YEAR = 12;
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;

    // --- Constants for month codes ---
    private static final int JANUARY_CODE = 1;
    private static final int FEBRUARY_CODE = 4;
    private static final int MARCH_CODE = 4;
    private static final int APRIL_CODE = 0;
    private static final int MAY_CODE = 2;
    private static final int JUNE_CODE = 5;
    private static final int JULY_CODE = 0;
    private static final int AUGUST_CODE = 3;
    private static final int SEPTEMBER_CODE = 6;
    private static final int OCTOBER_CODE = 1;
    private static final int NOVEMBER_CODE = 4;
    private static final int DECEMBER_CODE = 6;

    // --- Constants for month names ---
    private static final String JANUARY_STRING = "January";
    private static final String FEBRUARY_STRING = "February";
    private static final String MARCH_STRING = "March";
    private static final String APRIL_STRING = "April";
    private static final String MAY_STRING = "May";
    private static final String JUNE_STRING = "June";
    private static final String JULY_STRING = "July";
    private static final String AUGUST_STRING = "August";
    private static final String SEPTEMBER_STRING = "September";
    private static final String OCTOBER_STRING = "October";
    private static final String NOVEMBER_STRING = "November";
    private static final String DECEMBER_STRING = "December";

    // --- Constants for day codes ---
    private static final int SATURDAY_CODE = 0;
    private static final int SUNDAY_CODE = 1;
    private static final int MONDAY_CODE = 2;
    private static final int TUESDAY_CODE = 3;
    private static final int WEDNESDAY_CODE = 4;
    private static final int THURSDAY_CODE = 5;

    // --- Constants for day names ---
    private static final String SATURDAY_STRING = "Saturday";
    private static final String SUNDAY_STRING = "Sunday";
    private static final String MONDAY_STRING = "Monday";
    private static final String TUESDAY_STRING = "Tuesday";
    private static final String WEDNESDAY_STRING = "Wednesday";
    private static final String THURSDAY_STRING = "Thursday";
    private static final String FRIDAY_STRING = "Friday";

    // --- Constants for days in months ---
    private static final int LEAP_FEBRUARY_DAYS = 29;
    private static final int REGULAR_FEBRUARY_DAYS = 28;
    private static final int LONG_MONTH = 31;
    private static final int SHORT_MONTH = 30;

    // --- Leap year divisors ---
    private static final int LEAP_YEAR_DIVISOR = 4;
    private static final int CENTURY_DIVISOR = 100;
    private static final int QUADRICENTENNIAL_DIVISOR = 400;

    // --- Offsets and values for weekday calculation ---
    private static final int DAY_SUMMATION_START_VALUE = 0;
    private static final int YEAR_2000_OFFSET = 6;
    private static final int YEAR_1800_OFFSET = 2;
    private static final int JAN_FEB_LEAP_OFFSET = 6;
    private static final int FOUR_DIVISOR = 4;
    private static final int NUM_WEEK_DAYS = 7;

    // --- Minimum valid values ---
    private static final int MIN_DAY = 1;
    private static final int MIN_YEAR = YEAR_1800;

    // --- Constants for remainder ---
    private static final int EVEN_DIVISOR = 2;
    private static final int HAS_REMAINDER = 1;
    private static final int NO_REMAINDER = 0;

    private final int year;
    private final int month;
    private final int day;

    /**
     * Constructs a new {@code Date} object after validating the provided year, month, and day.
     *
     * @param year  the year
     * @param month the month
     * @param day   the day of the month (validated against the month and leap year rules)
     * @throws IllegalArgumentException if the date is invalid
     */
    public Date(final int year,
                final int month,
                final int day)
    {
        validateDate(year, month, day);

        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Validates a date against minimum/maximum year, month, and day constraints.
     *
     * @param year  the year to validate
     * @param month the month to validate
     * @param day   the day to validate
     * @throws IllegalArgumentException if the year, month, or day is out of valid range
     */
    private static void validateDate(final int year,
                                     final int month,
                                     final int day)
    {
        final int daysInMonth;
        daysInMonth = getDaysInMonth(year, month);

        if (year < MIN_YEAR || year > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Year is not between " + MIN_YEAR + " and " + CURRENT_YEAR);
        }

        if (month < JANUARY || month > DECEMBER)
        {
            throw new IllegalArgumentException("Month is not between " + JANUARY + " and " + DECEMBER);
        }

        if (day < MIN_DAY || day > daysInMonth)
        {
            throw new IllegalArgumentException("Day is not valid");
        }
    }

    /**
     * Retrieves the day of the month.
     *
     * @return the day
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Retrieves the month of the year.
     *
     * @return the month
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Retrieves the year.
     *
     * @return the year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Returns the name of the current month represented by this instance.
     *
     * @return the full name of the current month (e.g., "January", "February")
     */
    public String getMonthName()
    {
        if (month == JANUARY)
        {
            return JANUARY_STRING;
        }
        else if (month == FEBRUARY)
        {
            return FEBRUARY_STRING;
        }
        else if (month == MARCH)
        {
            return MARCH_STRING;
        }
        else if (month == APRIL)
        {
            return APRIL_STRING;
        }
        else if (month == MAY)
        {
            return MAY_STRING;
        }
        else if (month == JUNE)
        {
            return JUNE_STRING;
        }
        else if (month == JULY)
        {
            return JULY_STRING;
        }
        else if (month == AUGUST)
        {
            return AUGUST_STRING;
        }
        else if (month == SEPTEMBER)
        {
            return SEPTEMBER_STRING;
        }
        else if (month == OCTOBER)
        {
            return OCTOBER_STRING;
        }
        else if (month == NOVEMBER)
        {
            return NOVEMBER_STRING;
        }
        return DECEMBER_STRING;
    }

    /**
     * Determines the number of days in a given month of a given year.
     *
     * @param year  the year to evaluate (for leap year adjustment)
     * @param month the month to evaluate
     * @return the number of days in the specified month
     */
    private static int getDaysInMonth(final int year, final int month)
    {
        if (month == FEBRUARY)
        {
            if (isLeapYear(year))
            {
                return LEAP_FEBRUARY_DAYS;
            }
            return REGULAR_FEBRUARY_DAYS;
        }

        if (month < AUGUST && month % EVEN_DIVISOR == HAS_REMAINDER)
        {
            return LONG_MONTH;
        }

        if (month >= AUGUST && month % EVEN_DIVISOR == NO_REMAINDER)
        {
            return LONG_MONTH;
        }
        return SHORT_MONTH;
    }

    /**
     * Returns the month code associated with a given month.
     * <p>
     * Month codes are integer values used in calendar calculations
     * (such as determining weekdays).
     * </p>
     *
     * @param month the month to evaluate
     * @return the integer code corresponding to the specified month
     */
    private static int getMonthCode(final int month)
    {
        if (month == JANUARY)
        {
            return JANUARY_CODE;
        }
        else if (month == FEBRUARY)
        {
            return FEBRUARY_CODE;
        }
        else if (month == MARCH)
        {
            return MARCH_CODE;
        }
        else if (month == APRIL)
        {
            return APRIL_CODE;
        }
        else if (month == MAY)
        {
            return MAY_CODE;
        }
        else if (month == JUNE)
        {
            return JUNE_CODE;
        }
        else if (month == JULY)
        {
            return JULY_CODE;
        }
        else if (month == AUGUST)
        {
            return AUGUST_CODE;
        }
        else if (month == SEPTEMBER)
        {
            return SEPTEMBER_CODE;
        }
        else if (month == OCTOBER)
        {
            return OCTOBER_CODE;
        }
        else if (month == NOVEMBER)
        {
            return NOVEMBER_CODE;
        }
        return DECEMBER_CODE;
    }

    /**
     * Returns the name of the weekday that corresponds to a given day code.
     * <p>
     * Day codes are integer values used in calendar calculations
     * to identify specific weekdays.
     * </p>
     *
     * @param dayCode the numeric code for the day of the week
     * @return the full name of the weekday (e.g., "Monday", "Tuesday")
     */
    private static String getWeekDayName(final int dayCode)
    {
        if (dayCode == SATURDAY_CODE)
        {
            return SATURDAY_STRING;
        }
        else if (dayCode == SUNDAY_CODE)
        {
            return SUNDAY_STRING;
        }
        else if (dayCode == MONDAY_CODE)
        {
            return MONDAY_STRING;
        }
        else if (dayCode == TUESDAY_CODE)
        {
            return TUESDAY_STRING;
        }
        else if (dayCode == WEDNESDAY_CODE)
        {
            return WEDNESDAY_STRING;
        }
        else if (dayCode == THURSDAY_CODE)
        {
            return THURSDAY_STRING;
        }
        return FRIDAY_STRING;
    }

    /**
     * Determines whether a given year is a leap year based on the rules of the gregorian calendar.
     *
     * @param year the year to check
     * @return {@code true} if the year is a leap year, otherwise {@code false}
     */
    private static boolean isLeapYear(final int year)
    {
        if (year % QUADRICENTENNIAL_DIVISOR == NO_REMAINDER)
        {
            return true;
        }

        if (year % CENTURY_DIVISOR == NO_REMAINDER)
        {
            return false;
        }

        return year % LEAP_YEAR_DIVISOR == NO_REMAINDER;
    }

    /**
     * Returns the date formatted as {@code YYYY-MM-DD}.
     *
     * @return the formatted date string
     */
    public String getYYYYMMDD()
    {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    /**
     * Computes the day of the week for this date.
     *
     * <p>
     * To get the day of the week, do the following seven steps for dates in the 1900s:
     * e.g. October 31 1977:
     * <ol>
     *      <li>Calculate the number of twelves in 77: 6</li>
     *      <li>Calculate the remainder from step 1: 77 - 12*6 = 77 - 72 = 5</li>
     *      <li>Calculate the number of fours in step 2: 5/4 = 1.25, so 1</li>
     *      <li>Add the day of the month to each step above: 31 + 6 + 5 + 1 = 43</li>
     *      <li>Add the month code (for jfmamjjasond: 144025036146): for october it is 1: 43 + 1 = 44</li>
     *      <li>Add the previous five numbers: (44) and mod by 7: 44%7 = 2 (44/7 = 6 remainder 2)</li>
     *      <li>sat sun mon tue wed thu fri is 0 1 2 3 4 5 6; our 2 means Oct 31 1977 was monday</li>
     * </ol>
     * </p>
     *
     * <p>
     * Extra notes:
     * a) for January/February dates in leap years, add 6 at the start
     * b) for all dates in the 2000s, add 6 at the start
     * c) for all dates in the 1800s, add 2 at the start
     * </p>
     *
     * @return the name of the day of the week (e.g., "Monday")
     */
    public String getDayOfTheWeek()
    {
        int dayFromSummation;
        final int numTwelves;
        final int numTwelvesRemainder;
        final int foursInRemainder;
        final int lastTwoDigitsOfYear;

        dayFromSummation = DAY_SUMMATION_START_VALUE;
        if (month == JANUARY || month == FEBRUARY)
        {
            if (isLeapYear(year))
            {
                dayFromSummation += JAN_FEB_LEAP_OFFSET;
            }
        }

        if (year >= YEAR_2000)
        {
            dayFromSummation += YEAR_2000_OFFSET;
        }
        else if (year < YEAR_1900)
        {
            dayFromSummation += YEAR_1800_OFFSET;
        }

        lastTwoDigitsOfYear = year % CENTURY_DIVISOR;

        numTwelves = lastTwoDigitsOfYear % MONTHS_IN_YEAR;
        dayFromSummation += numTwelves;

        numTwelvesRemainder = lastTwoDigitsOfYear - MONTHS_IN_YEAR * numTwelves;
        dayFromSummation += numTwelvesRemainder;

        foursInRemainder = numTwelvesRemainder / FOUR_DIVISOR;
        dayFromSummation += foursInRemainder;

        dayFromSummation += day;
        dayFromSummation += getMonthCode(month);

        return getWeekDayName(dayFromSummation % NUM_WEEK_DAYS);
    }
}