package name.panitz.game.klaus;

import name.panitz.game.framework.FallingImage;
import name.panitz.game.framework.Vertex;

public class Klaus<I> extends FallingImage<I> {
  public Klaus(Vertex corner) {
    super("player.png", corner, new Vertex(0, 0));
  }
}
