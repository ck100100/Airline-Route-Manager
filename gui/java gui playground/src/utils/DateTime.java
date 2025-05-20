package utils;

public class DateTime {
    public int minute;
    public int hour;
    public int day;
    public int month;
    public int year;
    public DateTime(Integer minute, Integer hour, Integer day, Integer month, Integer year) {
        boolean isValid = 0 > minute || minute > 60
                || 0 > hour || hour > 60
                || 0 > day || day > 31
                || 0 > month || month > 12
                || 1900 > year || year > 2100;
        if(!isValid) {
            throw new IllegalArgumentException("Invalid date time paramaters");
        }
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
