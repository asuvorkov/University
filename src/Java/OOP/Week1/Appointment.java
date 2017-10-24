package Java.OOP;

/**
 * Created by Andrei on 24.10.2017.
 */
public class Appointment {
    DateTime time;
    int length;
    String reason;
    String place;

    public Appointment(DateTime dt, int l, String r, String p) {
        time = dt;
        length = l;
        reason = r;
        place = p;
    }

    public String toString(){
        return time.toString() + " " + length + " " + reason + " " + place;
    }

    public static void main(String[] args){
        Date d = new Date(29,11,2018);
        Time t = new Time(12,59,15);
        DateTime dt = new DateTime(d,t);
        Appointment ap = new Appointment(dt,60,"Zahnarzt","Burgstraße 4");
        System.out.println(ap);
    }
}
