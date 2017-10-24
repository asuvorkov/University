package Java.OOP;

/**
 * Created by Andrei on 24.10.2017.
 */
public class Time {
    int hour;
    int minute;
    int second;

    Time (int h, int m, int s){
        hour = h;
        minute = m;
        second = s;
    }

    public String toString() {
        return (hour + ":" + minute + " Uhr");
    }

    public static void main(String[] args){
        Time time = new Time (22, 10, 30);
        System.out.println(time);
    }
}
