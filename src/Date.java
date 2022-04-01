/**
 * This class models a Date
 * 
 * @author shreya.jaiswal
 *
 */
public class Date implements Comparable<Date> {

    /** This date's month */
    private int month;
    /** This date's day */
    private int day;
    /** This date's year */
    private int year;

    // constructor
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;

    }

    /**
     * Constructs a new date from CSV input.
     * <p>
     * Throws illegal argument exception if date is invalid
     * 
     * @param date :String representation of date of the format YYYYMMDD
     * @return new date object
     */
    public static Date fromYYYYMMDDString(String date) {

        int dateAsInt = Integer.parseInt(date);
        int day = dateAsInt % 100;
        int month = ((dateAsInt % 10000) - day) / 100;
        int year = (dateAsInt - (month * 100 + day)) / 10000;

        if (!Date.isValidDate(new Date(month, day, year))) {
           
            throw new IllegalArgumentException();
        }
        return new Date(month, day, year);

    }

    /**
     * Constructs a new date from user input.
     * <p>
     * Throws illegal argument exception if date is invalid
     * 
     * @param date :String representation of date of the format YYYY-MM-DD
     * @return new date object
     */
    public static Date fromYYYYMMDDDashString(String date) {

        String[] dateArray = date.split("-");
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[2]);
        if (!Date.isValidDate(new Date(month, day, year))) {
           
            throw new IllegalArgumentException();
        }
        return new Date(month, day, year);
    }

    /**
     * Returns integer representation of Date object.
     * 
     * @return Date as integer of format YYYYMMDD
     */
    public int getAsYYYYMMDD() {
        return this.year * 10000 + this.month * 100 + this.day;
    }

    /**
     * indicates if this Date object is equal to the specified Date object
     */
    @Override
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        } else if (other.getClass() != this.getClass()) {
            return false;
        }
        // we know we have a date in other
        Date otherAsDate = (Date) other;
        if (this.getAsYYYYMMDD() == otherAsDate.getAsYYYYMMDD()) {
            return true;
        }
        return false;

    }

    /**
     * returns a String representation of this Date object
     */
    @Override
    public String toString() {
        int dateAsInt = this.getAsYYYYMMDD();
        int[] dateArray = new int[3];
        dateArray[0] = dateAsInt % 100;
        int day = dateArray[0];
        dateArray[1] = ((dateAsInt % 10000) - day) / 100;
        int month = dateArray[1];
        dateArray[2] = (dateAsInt - (month * 100 + day)) / 10000;
        int year = dateArray[2];

        return month + "/" + day + "/" + year;

    }

    /**
     * Determines if given year is a leap year.
     * 
     * @param year :integer number of years
     * @return boolean true if the year is a leap year and false otherwise.
     */
    private static boolean isLeapYear(int year) {

        return (year % 4) == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    /**
     * Determines if given date is a valid date.
     * 
     * @param date :Date object
     * @return boolean true if Date is valid and false otherwise.
     */
    private static boolean isValidDate(Date date) {

        int dateAsInt = date.getAsYYYYMMDD();
        int[] dateArray = new int[3];
        dateArray[0] = dateAsInt % 100;
        int day = dateArray[0];
        dateArray[1] = ((dateAsInt % 10000) - day) / 100;
        int month = dateArray[1];
        dateArray[2] = (dateAsInt - (month * 100 + day)) / 10000;
        int year = dateArray[2];

        boolean result;

        result = true;

        if (year <= 0) {
            result = false;
        }
        if (month < 1 || month > 12) {
            result = false;
        }
        if (day < 1 || day > 31) {
            result = false;
        }
        if (month == 2 && isLeapYear(year) && day > 29) {
            result = false;
        }
        if (month == 2 && !isLeapYear(year) && day > 28) {
            result = false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            result = false;
        }

        return result;
    }

    /**
     * compares this date to the input date
     * 
     * @param other :Date to be compared
     * @return 1,-1,0 if this date is less than,greater than or equal to other
     *         respectively.
     */
    public int compareTo(Date other) {

        if (this.getAsYYYYMMDD() < other.getAsYYYYMMDD()) {
            return -1;
        } else if (this.getAsYYYYMMDD() > other.getAsYYYYMMDD()) {
            return 1;
        }

        return 0; // if equal

    }

}
