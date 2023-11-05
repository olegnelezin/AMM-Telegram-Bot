package org.example.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    public static String translateWeekday(String weekday_eng) {
        String result;
        switch (weekday_eng) {
            case "MONDAY":
                result = "Понедельник";
                break;
            case "TUESDAY":
                result = "Вторник";
                break;
            case "WEDNESDAY":
                result = "Среда";
                break;
            case "THURSDAY":
                result = "Четверг";
                break;
            case "FRIDAY":
                result = "Пятница";
                break;
            case "SATURDAY":
                result = "Суббота";
                break;
            case "SUNDAY":
                result = "Воскресенье";
                break;
            default:
                result = "None";
                break;
        }
        return result;
    }

    public static boolean isNumerator(LocalDate localDate) {
        LocalDate NUMERATOR_DATE = LocalDate.of(2023, 10, 30);
        long diff = ChronoUnit.DAYS.between(localDate, NUMERATOR_DATE);
        return diff % 7 == 0;
    }
}
