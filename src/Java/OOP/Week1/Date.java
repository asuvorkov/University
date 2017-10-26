package Java.OOP.Week1;

/**
 * Created by Andrei on 24.10.2017.
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

    public String toString() {
        return (day + "." + month + "." +year);
    }

    public static void main(String[] args){
        Date date = new Date (22, 10, 1987);
        System.out.println(date);
    }
}
