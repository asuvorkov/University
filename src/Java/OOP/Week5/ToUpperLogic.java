package Java.OOP.Week5;

/**
 * Created by Andrei on 23.11.2017.
 */
public class ToUpperLogic extends ButtonLogic{
  @Override
  String getButtonLabel() {
    return "Click me";
  }

  @Override
  String eval(String x) {
    return x.toUpperCase();
  }
  public static void main(String[] args){
    new Dialogue(new ToUpperLogic());
    new ConsoleDialogue(new ToUpperLogic()).run();
  }
}
