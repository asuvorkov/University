package name.panitz.game;

import name.panitz.game.framework.android.GameActivity;
import name.panitz.game.example.simple.SimpleGame;

public class MainActivity extends GameActivity{
  public MainActivity() {
    super(new SimpleGame<>());
  }
}
