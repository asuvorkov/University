package Java.OOP.Week3;

import static java.lang.Math.floor;

/**
 * Created by Andrei on 03.11.2017.
 */
public class Date {
  int day;
  int month;
  int year;

  Date(int d, int m, int y) {
    day = d;
    month = m;
    year = y;
    assert day <= 31 && day >= 1 : " Not valid day";
    assert month <= 12 && month >= 1 : " Not valid month";
  }

  int dayOfWeek(){
    //hier den Wochentag berechnen
    if (month < 3) year = year - 1;
    int a = (int) floor(2.6 * ((month + 9) % 12 + 1) - 0.2);
    int b = (int) (floor(year / 400) - 2 * floor(year / 100));
    int c = (int) (floor(year % 100 / 4) + b - 1);
    int output =(((day + a + year % 100 + c) % 7 + 7) % 7 + 1);
    if (output == 7){
      return 0;
    }
    return output;
  }
  public static void main(String[] args){
    Date date = new Date(7, 2, 2016);
    System.out.println(date.dayOfWeek());
  }
}
