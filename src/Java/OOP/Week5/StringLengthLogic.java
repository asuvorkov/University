package Java.OOP.Week5;

/**
 * Created by Andrei on 23.11.2017.
 */
public class StringLengthLogic extends ButtonLogic{
  @Override
  String getButtonLabel() {
    return "Click me";
  }

  @Override
  String eval(String x) {
    return String.valueOf(x.length());
  }

  public static void main(String[] args){
    new Dialogue(new StringLengthLogic());
    new ConsoleDialogue(new StringLengthLogic()).run();
  }
}
