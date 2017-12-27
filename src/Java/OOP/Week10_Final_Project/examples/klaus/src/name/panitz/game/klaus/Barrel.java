package name.panitz.game.klaus;

import name.panitz.game.framework.FallingImage;
import name.panitz.game.framework.Vertex;

public class Barrel<I> extends FallingImage<I> {
  public Barrel(Vertex corner) {
    super("fass.gif", corner, new Vertex(1, 0));
  }

  public void fromTop(double wi) {
    getPos().moveTo(
        new Vertex(Math.random()*(wi - 2*40) + 40, -40));
  }
}
