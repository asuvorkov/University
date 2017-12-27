package name.panitz.game.framework.android;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import name.panitz.game.framework.Button;
import name.panitz.game.framework.GameLogic;


public class GameActivity extends AppCompatActivity {
  Screen gameView;
  private GestureDetector mGestureDetector;

  GameLogic<Bitmap,MediaPlayer> game;
  
  public GameActivity(GameLogic<Bitmap,MediaPlayer> game) {
    this.game = game;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gameView = new Screen(this, game);
    gameView.setMinimumWidth((int) game.getWidth());
    gameView.setMinimumHeight((int) game.getHeight());
       
    LinearLayout rl = new LinearLayout(this);
    // Create an object of our Custom Gesture Detector Class
    CustomGestureDetector customGestureDetector
      = new CustomGestureDetector(game);
    // Create a GestureDetector
    mGestureDetector=new GestureDetector(this,customGestureDetector);
    mGestureDetector.setOnDoubleTapListener(customGestureDetector);

    gameView.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, final MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
      }
    });


    LinearLayout butView = new LinearLayout(this);
    butView.setOrientation(LinearLayout.VERTICAL);

    for (Button b:game.getButtons()){
      android.widget.Button but = new android.widget.Button(this);
      but.setText(b.name);
      but.setOnClickListener(ev->b.action.run());
      butView.addView(but);
    }
    rl.addView(butView);
    rl.addView(gameView);

    setContentView(rl);
  }

  @Override
  protected void onResume() {
    super.onResume();
    // Tell the gameView resume method to execute
    gameView.resume();
  }

  // This method executes when the player quits the game
  @Override
  protected void onPause() {
    super.onPause();
    // Tell the gameView pause method to execute
    gameView.pause();
  }
}
