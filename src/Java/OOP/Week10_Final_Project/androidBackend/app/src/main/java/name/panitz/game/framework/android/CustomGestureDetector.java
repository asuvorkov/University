package name.panitz.game.framework.android;

import android.graphics.Bitmap;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.view.GestureDetector;
import android.view.MotionEvent;

import name.panitz.game.R;
import name.panitz.game.framework.GameLogic;
import name.panitz.game.framework.KeyCode;

public class CustomGestureDetector
  implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

  GameLogic<Bitmap, MediaPlayer> game;

  public CustomGestureDetector(GameLogic<Bitmap, MediaPlayer> game) {
    this.game = game;
  }

  @Override
  public boolean onSingleTapConfirmed(MotionEvent e) {
    return true;
  }

  @Override
  public boolean onDoubleTap(MotionEvent e) {
    game.keyPressedReaction(KeyCode.VK_SPACE);
    return true;
  }

  @Override
  public boolean onDoubleTapEvent(MotionEvent e) {
    return true;
  }

  @Override
  public boolean onDown(MotionEvent e) {
    return true;
  }

  @Override
  public void onShowPress(MotionEvent e) {
  }

  @Override
  public boolean onSingleTapUp(MotionEvent e) {
    return true;
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2
      , float distanceX, float distanceY) {
    return true;
  }

  @Override
  public void onLongPress(MotionEvent e) {
  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2
      , float velocityX, float velocityY) {
    if ((e1.getX() - e2.getX())<40) {
      game.keyPressedReaction(KeyCode.RIGHT_ARROW);
    }
    
    if (e1.getX() - e2.getX() > 40) {
      game.keyPressedReaction(KeyCode.LEFT_ARROW);
    }

    if (e2.getY() - e1.getY() >40) {
      game.keyPressedReaction(KeyCode.DOWN_ARROW);
    }

    if (e1.getY() - e2.getY() > 40) {
      game.keyPressedReaction(KeyCode.UP_ARROW);
    }

    return true;
  }
}
