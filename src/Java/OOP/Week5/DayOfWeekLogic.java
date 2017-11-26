package Java.OOP.Week5;

import Java.OOP.Week3.Date;
/**
 * Created by Andrei on 23.11.2017.
 */
public class DayOfWeekLogic extends ButtonLogic {
  @Override
  String getButtonLabel() {
    return "Click me";
  }

  @Override
  String eval(String x) {
    String[] parts = x.split("[.]");
    Date date = new Date(Integer.valueOf(parts[0]),
        Integer.valueOf(parts[1]),
        Integer.valueOf(parts[2]));
    switch (date.dayOfWeek()){
      case 1 : return "Montag";
      case 2 : return "Dienstag";
      case 3 : return "Mittwoch";
      case 4 : return "Donnerstag";
      case 5 : return "Freitag";
      case 6 : return "Sonnabend";
      case 0 : return "Sonntag";
      default: return null;
    }
  }

  public static void main(String[] args){
    new Dialogue(new DayOfWeekLogic());
    new ConsoleDialogue(new DayOfWeekLogic()).run();
  }
}
