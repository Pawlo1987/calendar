package com.example.user.calendarexemp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    //получение даты из String в формате "dd-MM-yyyy"
    //https://stackoverflow.com/questions/9945072/convert-string-to-date-in-java
    public int getDayOfWeek(String str_date) {
        Date date = new Date();
        DateFormat formatter;
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = (Date) formatter.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date yourDate = date;
        Calendar c = Calendar.getInstance();
        c.setTime(yourDate);
        return c.get(Calendar.DAY_OF_WEEK);
    }//getDayOfWeek

    //получение количества дней в месяце
    public int getNumberOfDaysInMonth(int year, int month, int date) {
        Calendar myCalendar = (Calendar) Calendar.getInstance().clone();
        myCalendar.set(year, month, date);
        return myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }//getNumberOfDaysInMonth

    //преобразование из минут(int) в часы(String)
    public String convertTime(int min) {
        return (min/60 < 10 ? "0" + String.valueOf(min/60) : String.valueOf(min/60)) + ":00";
    }//getNumberOfDaysInMonth
}
