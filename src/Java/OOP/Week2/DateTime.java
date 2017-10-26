package Java.OOP.Week2;

/**
 * Created by Andrei on 26.10.2017.
 */
public class DateTime {
    Date date;
    Time time;

    DateTime (Date d, Time t){
        date = d;
        time = t;
    }

    boolean isEarlierThan(DateTime that){
        if (this.date.isEarlierThan(that.date) || (this.date.isSameDate(that.date) && this.time.isEarlierThan(that.time))){
            return true;
        } else {
            return false;
        }
    }

    boolean isLaterThan(DateTime that){
        if (this.date.isLaterThan(that.date) || (this.date.isSameDate(that.date) && this.time.isLaterThan(that.time))){
            return true;
        } else {
            return false;
        }
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
