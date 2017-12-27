package name.panitz.game.framework.gwt.client;

import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.GameObject;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class GWTGraphics implements GraphicsTool<ImageElement>{
  Context2d gc;
	
  public GWTGraphics(Context2d gc) {
    this.gc = gc;
  }

  @Override
  public void drawImage(ImageElement img, double x, double y) {
    gc.drawImage(img, x, y);
  }

  @Override
  public void drawRect(double x, double y, double w, double h) {
    gc.rect(x, y, w, h);
  }

  @Override
  public void fillRect(double x, double y, double w, double h) {
    gc.fillRect(x, y, w, h);
  }

  @Override
  public void drawOval(double x, double y, double w, double h) {
    // TODO 
  }

  @Override
  public void fillOval(double x, double y, double w, double h) {
    // TODO 
  }


  public void setColor(double r ,double g ,double b){
    // TODO 
  }
  
  @Override
  public void drawString(double x,double y,int fontSize, String fontName, String text){
    gc.setFont(fontSize+"pt "+fontName);
    gc.fillText(text, x, y);
  }

  @Override
  public ImageElement generateImage(String name,GameObject<ImageElement> go){
    Image image = new Image(name);
    image.addLoadHandler(new LoadHandler() {			
       @Override
       public void onLoad(LoadEvent event) {
         go.setWidth(image.getWidth());							
         go.setHeight(image.getHeight());
         RootLayoutPanel.get().remove(image);
      }
    });
	
    ImageElement imgel = ImageElement.as(image.getElement());
    RootLayoutPanel.get().add(image);
    return imgel;
  }
}

