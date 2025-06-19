// This is a mutant program.
// Author : ysma

public class Calendar
{

    public static  int cal( int month1, int day1, int month2, int day2, int year )
    {
        int numDays = 0;
        if (month1 < 1 || month1 > 12) {
            numDays = -1;
            return numDays;
        }
        if (month2 < 1 || month2 > 12) {
            numDays = -1;
            return numDays;
        }
        if (day1 < 1 || day1 > 31) {
            numDays = -1;
            return numDays;
        }
        if (day2 < 1 || day2 > 31) {
            numDays = -1;
            return numDays;
        }
        if (year < 1 || year > 10000) {
            numDays = -1;
            return numDays;
        }
        if (month2 == month1) {
            numDays = day2 - day1;
        } else {
            int[] daysIn = { 0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
            int m4 = 0;
            int m100 = 0;
            int m400 = 0;
            m4 = year % 4;
            m100 = year % 100;
            m400 = year % 400;
            if (m4 != 0 || m100 == 0 && m400 != 0) {
                daysIn[2] = 28;
            } else {
                daysIn[2] = 29;
            }
            numDays = day2 + (daysIn[month1] - day1);
            for (int i = month1 + 1; i <= month2 - 1; i++) {
                numDays = daysIn[i] + numDays;
            }
        }
        return numDays;
    }

}
