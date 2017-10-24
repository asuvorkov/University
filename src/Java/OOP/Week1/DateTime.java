package Java.OOP;

/**
 * Created by Andrei on 24.10.2017.
 */
public class DateTime {
    Date date;
    Time time;

    DateTime (Date d, Time t){
        date = d;
        time = t;
    }

    public String toString(){
        return date.toString() + " " + time.toString();
    }

    public static void main(String[] args){
        Date date = new Date (22, 10, 1987);
        Time time = new Time (12, 30, 50);
        DateTime dt = new DateTime (date, time);
        System.out.println(dt);
    }
}
