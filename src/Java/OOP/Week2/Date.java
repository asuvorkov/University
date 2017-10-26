package Java.OOP.Week2;

/**
 * Created by Andrei on 26.10.2017.
 */
public class Date {
    int day;
    int month;
    int year;

    Date (int d, int m, int y){
        day = d;
        month = m;
        year = y;
    }

    boolean isEarlierThan(Date that) {
        boolean earlier = false;
        if (this.year < that.year){
            earlier = true;
        }else {
            if (this.month < that.month){
                earlier = true;
            }else {
                if (this.day < that.day){
                    earlier = true;
                }
            }
        }
        return earlier;
    }

    boolean isLaterThan(Date that) {
        boolean later = false;
        if (this.year > that.year){
            later = true;
        }else {
            if (this.month > that.month){
                later = true;
            }else {
                if (this.day > that.day){
                    later = true;
                }
            }
        }
        return later;
    }

    boolean isSameDate(Date that) {
        boolean same = false;
        if (that.year == this.year && that.month == this.month && that.day == this.day){
            same = true;
        }
        return same;
    }

    boolean isLeapYear() {
        if (this.year % 4 == 0 && (this.year % 100 != 0 || this.year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    int getAbsoluteDaysInYear() {
        if (isLeapYear()){
            return 366;
        }else {
            return 365;
        }
    }

    public String toString() {
        return (day + "." + month + "." +year);
    }

    public static void main(String[] args){
        Date date1 = new Date (1, 1, 1919);
        Date date2 = new Date (2, 1, 1919);
        System.out.println(date1.isEarlierThan(date2));
    }
}
