package Java.OOP.Week5;

import Java.OOP.Week3.DateEaster;

/**
 * Created by Andrei on 23.11.2017.
 */
public class EasterLogic extends ButtonLogic{
  @Override
  String getButtonLabel() {
    return "Click me";
  }

  @Override
  String eval(String x) {
    return new DateEaster(1, 1, Integer.valueOf(x)).easter().toString();
  }

  public static void main(String[] args){
    new Dialogue(new EasterLogic());
    new ConsoleDialogue(new EasterLogic()).run();
  }
}
