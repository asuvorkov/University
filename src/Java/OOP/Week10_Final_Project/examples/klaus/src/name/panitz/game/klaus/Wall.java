package name.panitz.game.klaus;

import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Wall<I> extends ImageObject<I> {

	public Wall(Vertex corner) {
		super("wall.png", corner, new Vertex(0,0));
	}

}
