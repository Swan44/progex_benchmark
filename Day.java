// This is a mutant program.
// Author : ysma

public class Day
{

    public  int whichDay( int day, int month, int year )
    {
        int leap = 0;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            leap = 1;
        }
        if (month < 1 || month > 12) {
            return 0;
        }
        if (day < 1) {
            return 0;
        } else {
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 30) {
                    return 0;
                }
            } else {
                if (month == 2) {
                    if (leap == 0 && day > 28 || leap == 1 && day > 29) {
                        return 0;
                    }
                } else {
                    if (day > 31) {
                        return 0;
                    }
                }
            }
        }
        int sum = 0;
        switch (month) {
        case 1 :
            sum = 0;
            break;

        case 2 :
            sum = 31;
            break;

        case 3 :
            sum = 59;
            break;

        case 4 :
            sum = 90;
            break;

        case 5 :
            sum = 120;
            break;

        case 6 :
            sum = 151;
            break;

        case 7 :
            sum = 181;
            break;

        case 8 :
            sum = 212;
            break;

        case 9 :
            sum = 243;
            break;

        case 10 :
            sum = 273;
            break;

        case 11 :
            sum = 304;
            break;

        case 12 :
            sum = 334;
            break;

        default  :
            System.out.println( "month makes error" );
            break;

        }
        sum += day;
        if (month > 2) {
            sum += leap;
        }
        return sum;
    }

}