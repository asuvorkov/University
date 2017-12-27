package name.panitz.game.framework.gwt.client;

import name.panitz.game.example.simple.SimpleGame;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class WebGames implements EntryPoint { 
  public void onModuleLoad() {
    RootPanel.get("spiel").add(new GameScreen(new SimpleGame<>()));
  }
}

