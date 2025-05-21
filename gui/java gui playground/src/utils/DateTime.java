package utils;

public class DateTime {
    public int minute;
    public int hour;
    public int day;
    public int month;
    public int year;
    public DateTime(Integer minute, Integer hour, Integer day, Integer month, Integer year) throws IllegalArgumentException {
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

    public String parse() {
        String minute = parseWithZeroPadding(2, this.minute);
        String hour = parseWithZeroPadding(2, this.hour);
        String day = parseWithZeroPadding(2, this.day);
        String month = parseWithZeroPadding(2, this.month);
        String year = parseWithZeroPadding(4, this.year);
        String parsedDate = hour + ":" + minute + " " + day + "/" + month + "/" + year;

        return parsedDate;
    }

    private String parseWithZeroPadding(int digitLength, int data) {
        int numDataDigits;
        if(data != 0)
             numDataDigits = (int) Math.floor(Math.log10(data)) + 1;
        else
            numDataDigits = 1;

        if(numDataDigits > digitLength)
            throw new IllegalArgumentException("Data provided is larger than digit length!");

        String parsedData = "";

        for(int i=0; i < digitLength - numDataDigits; i++)
            parsedData += "0";

        parsedData += Integer.toString(data);

        return parsedData;
    }
}
