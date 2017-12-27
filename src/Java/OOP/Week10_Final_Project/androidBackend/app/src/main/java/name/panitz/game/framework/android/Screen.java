package name.panitz.game.framework.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import name.panitz.game.framework.GameLogic;
import name.panitz.game.framework.SoundObject;

public class Screen extends SurfaceView implements Runnable {
  GameLogic<Bitmap, MediaPlayer> game;
  Thread gameThread = null;
  SurfaceHolder ourHolder;

  volatile boolean playing;
  Canvas canvas;
  Paint paint;

  long fps;
  private long timeThisFrame;

  private AndroidGraphicsTool graphicsTool;
  private AndroidSoundTool soundTool;

  public Screen(Context context, GameLogic<Bitmap, MediaPlayer> game){
    super(context);
    this.game = game;
    ourHolder = getHolder();
    paint = new Paint();
    this.setMinimumHeight((int) game.getHeight());
    this.setMinimumWidth((int) game.getWidth());
    soundTool = new AndroidSoundTool(context);
  }


  @Override
  public void run() {
    while (playing) {
      // Capture the current time in milliseconds in startFrameTime
      long startFrameTime = System.currentTimeMillis();

      update();
      draw();
      game.playSounds(soundTool);

      timeThisFrame = System.currentTimeMillis() - startFrameTime;
      if (timeThisFrame >= 1) {
        fps = 1000 / timeThisFrame;
      }
    }  
  }

  @Override
  public boolean onTouchEvent(MotionEvent me) {
    return true;
  }

  public void update() {
    if (!game.isStopped()) {
      game.move();
      game.doChecks();
    }
  }

  public void draw() {
    // Make sure our drawing surface is valid or we crash
    if (ourHolder.getSurface().isValid()) {
      // Lock the canvas ready to draw
      canvas = ourHolder.lockCanvas();
      canvas.drawColor(Color.argb(255, 26, 128, 182));
      graphicsTool
          = new AndroidGraphicsTool(paint, getContext(), canvas);
      game.paintTo(graphicsTool);
      graphicsTool.paint.setColor(Color.argb(255, 26, 128, 182));
      graphicsTool.drawRect(game.getWidth(),0,800,800);
      // Draw everything to the screen
      ourHolder.unlockCanvasAndPost(canvas);
    }
    
  }

  // If SimpleGameEngine Activity is paused/stopped
  // shutdown our thread.
  public void pause() {
    playing = false;
    try {
      gameThread.join();
    } catch (InterruptedException e) {
      Log.e("Error:", "joining thread");
    }  
  }

  // If SimpleGameEngine Activity is started then
  // start our thread.
  public void resume() {
    playing = true;
    gameThread = new Thread(this);
    gameThread.start();
  }
}
