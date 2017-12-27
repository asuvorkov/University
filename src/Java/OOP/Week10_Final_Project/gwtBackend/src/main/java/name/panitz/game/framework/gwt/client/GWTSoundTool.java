package name.panitz.game.framework.gwt.client;

import com.google.gwt.media.client.Audio;
import  name.panitz.game.framework.SoundTool;

class GWTSoundTool implements SoundTool<Audio>{

  @Override
  public Audio loadSound(String fileName) {
     Audio result = Audio.createIfSupported();
     result.setSrc(fileName);
     return result;
  }
  @Override
  public void playSound(Audio sound) {
    sound.play();
  }
}
