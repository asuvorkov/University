package name.panitz.game.framework.android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;

import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.GraphicsTool;


public class AndroidGraphicsTool implements GraphicsTool<Bitmap> {
  Canvas canvas;
  Context view;
  Paint paint;
  float density;

  private int byIdName(String name) {
    Resources res = view.getResources();
    return res.getIdentifier(name, "drawable", view.getPackageName());
  }
  
  public AndroidGraphicsTool(Paint paint,Context view, Canvas canvas){
    this.canvas = canvas;
    this.view = view;
    this.paint = paint;
    DisplayMetrics dm = view.getResources().getDisplayMetrics();
    density = dm.density;
  }
  

  @Override
  public void drawImage(Bitmap img, double x, double y) {
    canvas.drawBitmap(img,(int)(density*x),(int)(density*y),null);
  }

  @Override
  public void drawRect(double x, double y, double w, double h) {
    paint.setStyle(Paint.Style.STROKE);
    canvas.drawRect((float)(density*x),(float)(density*y)
      ,(float)(w*density+(density*x))
      ,(float)(h*density+(density*y)),paint);
  }

  @Override
  public void fillRect(double x, double y, double w, double h) {
    paint.setStyle(Paint.Style.FILL);
    canvas.drawRect((float)(density*x),(float)(density*y)
        ,(float)(w*density+(density*x))
        ,(float)(h*density+(density*y)),paint);

  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  public void drawOval(double x, double y, double w, double h) {
    paint.setStyle(Paint.Style.STROKE);
    canvas.drawOval((float)(density*x),(float)(density*y)
        ,(float)(w*density+(density*x))
        ,(float)(h*density+(density*y)),paint);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  public void fillOval(double x, double y, double w, double h) {
    paint.setStyle(Paint.Style.FILL);
    canvas.drawOval((float)(density*x),(float)(density*y)
        ,(float)(w*density+(density*x))
        ,(float)(h*density+(density*y)),paint);
  }

  @Override
  public void setColor(double r, double g, double b) {
    paint.setARGB(255,(int)(255*r),(int)(255*g),(int)(255*b));
  }

  @Override
  public void drawString(double x, double y, int fntsize
      , String fntName, String text) {
    paint.setTextSize(fntsize);
    canvas.drawText(text,(int)(density*x),(int)(density*y),paint);
  }

  @Override
  public Bitmap generateImage(String name, GameObject<Bitmap> go) {
    Bitmap bmp = BitmapFactory.decodeResource(view.getResources()
       ,byIdName(name.substring(0,name.lastIndexOf('.'))));
    go.setWidth(bmp.getWidth()/density);
    go.setHeight(bmp.getHeight()/density);
    return bmp;
  }
}
