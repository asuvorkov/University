package name.panitz.game.framework.android;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;

import name.panitz.game.R;
import name.panitz.game.framework.SoundTool;

import static android.media.MediaPlayer.*;

public class AndroidSoundTool implements SoundTool<MediaPlayer> {
  Context view;

  public AndroidSoundTool(Context view) {
    this.view = view;
  }

  private int byIdName(String name) {
    Resources res = view.getResources();
    return res.getIdentifier(name, "raw", view.getPackageName());
  }

  @Override
  public MediaPlayer loadSound(String name) {
    return MediaPlayer.create(view
        ,byIdName(name.substring(0,name.lastIndexOf('.'))));
  }

  @Override
  public void playSound(MediaPlayer sound) {
    if (sound.isPlaying()) {
      sound.pause();
      sound.seekTo(0);
    }
    sound.start();
  }
}
