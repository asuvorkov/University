package Java.OOP.Week2;

/**
 * Created by Andrei on 26.10.2017.
 */
public class Appointment {
    DateTime dateTime;
    int length;
    String reason;
    String place;

    public Appointment(DateTime dt, int l, String r, String p) {
        dateTime = dt;
        length = l;
        reason = r;
        place = p;
    }

    public String toString(){
        return dateTime.toString() + " " + length + " " + reason + " " + place;
    }

    DateTime endingTime(){
        Time newTime = this.dateTime.time.minutesLater(length);
        Date date = this.dateTime.date;
        if (this.dateTime.time.hour < newTime.hour){
            int rest = this.dateTime.time.hour - newTime.hour;
            date = new Date(this.dateTime.date.day + rest, this.dateTime.date.month, this.dateTime.date.year);
        }
        DateTime output = new DateTime(date, newTime);
        return output;
    }

    public static void main(String[] args){
        Date d = new Date(13,11,2018);
        Time t = new Time(12,59,15);
        DateTime dt = new DateTime(d,t);
        Appointment ap = new Appointment(dt,12000,"Zahnarzt","Burgstraße 4");
        System.out.println(ap.endingTime());
    }
}
