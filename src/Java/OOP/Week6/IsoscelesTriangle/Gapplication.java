package Java.OOP.Week6.IsoscelesTriangle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Gapplication extends Application {  
  Pane pane = new Pane();
  Canvas canvas;
  public Gapplication(GeometricObject... geos){
    canvas =  new FXScreen(geos);
  }

  public void start(Stage stage) throws Exception {
    pane.getChildren().add(canvas);
    Scene scene = new Scene(pane, canvas.getWidth(), canvas.getHeight());
    stage.setScene(scene);
    stage.show();
  }
}