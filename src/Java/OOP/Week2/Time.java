package Java.OOP.Week2;

/**
 * Created by Andrei on 26.10.2017.
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

    boolean isEarlierThan(Time that) {
        boolean earlier = false;
        if (this.hour < that.hour){
            earlier = true;
        }else {
            if (this.minute < that.minute && this.hour <= that.hour){
                earlier = true;
            }else {
                if (this.second < that.second && this.minute <= that.minute && this.hour <= that.hour){
                    earlier = true;
                }
            }
        }
        return earlier;
    }

    boolean isLaterThan(Time that) {
        boolean later = false;
        if (this.hour > that.hour){
            later = true;
        }else {
            if (this.minute > that.minute && this.hour >= that.hour){
                later = true;
            }else {
                if (this.second > that.second && this.minute >= that.minute && this.hour >= that.hour){
                    later = true;
                }
            }
        }
        return later;
    }

    Time minutesLater(int min){
        int rest = (this.minute + min) % 60;
        int h = (this.minute + min - rest) / 60;
        Time newTime = new Time((this.hour + h) % 24, rest, this.second);
        return newTime;
    }

    public String toString() {
        return (hour + ":" + minute + " Uhr");
    }

    public static void main(String[] args){
        Time time3 = new Time (12, 16, 58);
        Time time2 = new Time (12, 17, 56);
        System.out.println(time3.isLaterThan(time2));
    }
}
