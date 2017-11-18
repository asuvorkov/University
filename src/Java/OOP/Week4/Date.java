package Java.OOP.Week4;

import static java.lang.Math.floor;

/**
 * Created by Andrei on 18.11.2017.
 */
class Date {
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

  boolean isLeapYear() {
    return this.year % 4 == 0
        && (this.year % 100 != 0 || this.year % 400 == 0);
  }

  int daysInTheMonth(){
    if (this.month == 2){
      if (this.isLeapYear()){
        return 29;
      }
      return 28;
    }
    if (this.month == 1 || this.month == 3 || this.month == 5
        || this.month == 7 || this.month == 8 || this.month == 10
        || this.month == 12){
      return 31;
    }
    return 30;
  }

  Date nextDay(){
    //hier den nächsten berechnen
    Date d = this;
    int days = daysInTheMonth();
    if (d.day == days){
      d.day = 1;
      if (d.month == 12){
        d.month = 1;
        d.year += 1;
      }else {
        d.month += 1;
      }
      return d;
    }
    d.day += 1;
    return d;
  }
  int dayOfWeek(){
    //hier den Wochentag berechnen
    int y = year;
    if (month < 3) y = year - 1;
    int a = (int) floor(2.6 * ((month + 9) % 12 + 1) - 0.2);
    int b = (int) (floor(y / 400) - 2 * floor(y / 100));
    int c = (int) (floor(y % 100 / 4) + b - 1);
    int output =(((day + a + y % 100 + c) % 7 + 7) % 7 + 1);
    return output;
  }
  String monthAsHTML(){
    // hier den HTML String berechnen
    Date dateTemp = new Date(1, this.month, this.year);
    int days = daysInTheMonth();
    boolean firstWeek = true;
    String output = "<table>\n<tr><th>Mo</th><th>Di</th><th>Mi</th>"
        + "<th>Do</th><th>Fr</th><th>Sb</th><th>So</th></tr>\n";

    for (int i = 1; i < days + 1; i++){
      output += "<tr>";
      if (firstWeek){
        for (int a = 1 ; a < 8; a++){
          if (a < dateTemp.dayOfWeek()){
            output += "<td></td>";
          }else {
            output += "<td>" + i + "</td>";
            if (a != 7){
              i++;
            }
          }
        }
        firstWeek = false;
      }else {
        for (int a = 1 ; a < 8; a++){
          if (i == days + 1){
            break;
          }
          output += "<td>" + i + "</td>";
          if (a != 7){
            i++;
          }
        }
      }
      output += "</tr>\n";
    }
    return output + "</table>";
  }
  Date mothersDay(){
    //hier den Muttertag berechnen
    Date dateTemp = new Date(1, 5, this.year);
    int sunday = 0;
    for (int i = dateTemp.day; i < 16; i++){
      if (dateTemp.dayOfWeek() == 7){
        sunday++;
      }
      if (sunday == 2){
        return new Date(i, 5, dateTemp.year);
      }
      dateTemp.nextDay();
    }
    return null;
  }
  public String toString() {
    return (day + "." + month + "." + year);
  }

  public static void main(String[] args){
    Date datebla = new Date(31, 12, 2002);
    System.out.println(datebla.dayOfWeek());
    System.out.println(datebla.mothersDay().toString());
    System.out.println(datebla.monthAsHTML());
    System.out.println(datebla.nextDay().toString());
  }
}
