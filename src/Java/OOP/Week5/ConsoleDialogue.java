package Java.OOP.Week5;

import java.io.*;

public class ConsoleDialogue {
  final ButtonLogic logic;

  public ConsoleDialogue(ButtonLogic logic) {
    this.logic = logic;
  } 

  public void run(){
    BufferedReader in
     = new BufferedReader(new InputStreamReader(System.in));
    do {
      try {
        System.out.println(logic.getButtonLabel()
                  +"\n(Ende mit 'good bye')");
        String command = in.readLine();
        if (command.equals("good bye"))break;
        System.out.println(logic.eval(command));
      } catch (IOException e) {
        System.out.println(e);
      }		
    }while (true);
  }
}