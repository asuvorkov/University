package Java.OOP.Week3;

/**
 * Created by Andrei on 03.11.2017.
 */
public class DateEaster {
  int day;
  int month;
  int year;

  DateEaster(int d, int m, int y) {
    day = d;
    month = m;
    year = y;
    assert day <= 31 && day >= 1 : " Not valid day";
    assert month <= 12 && month >= 1 : " Not valid month";
  }

  DateEaster easter(){
    //hier den Ostersonntag berechnen
    int a = this.year % 19;
    int b = this.year / 100;
    int c = this.year % 100;
    int d = b / 4;
    int e = b % 4;
    int f = (b + 8) / 25;
    int g = (b - f + 1) / 3;
    int h = (19 * a + b - d - g + 15) % 30;
    int i = c / 4;
    int k = c % 4;
    int l = (32 + 2 * e + 2 * i - h - k) % 7;
    int m = (a + 11 * h + 22 * l) / 451;
    int n = (h + l - 7 * m + 114) / 31;
    int p = (h + l - 7 * m + 114) % 31;
    DateEaster output = new DateEaster(p + 1, n, this.year);
    return output;
  }

  @Override
  public String toString() {
    return this.day + "." + this.month + "." + this.year;
  }

  public static void main(String[] args){
    DateEaster date = new DateEaster(7, 2, 2020);
    System.out.println(date.easter().toString());
  }
}
