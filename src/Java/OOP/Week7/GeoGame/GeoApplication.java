package Java.OOP.Week7.GeoGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GeoApplication extends Application {  
  Pane pane = new Pane();
  Canvas canvas;
  public GeoApplication(GeoGame game){
    canvas =  new FXScreen(game);
  }

  public void start(Stage stage) throws Exception {
    pane.getChildren().add(canvas);
    Scene scene = new Scene(pane, canvas.getWidth(), canvas.getHeight());
    stage.setScene(scene);
    stage.show();
  }
}