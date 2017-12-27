package name.panitz.game.klaus;

import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Heart<I> extends ImageObject<I> {

	public Heart(Vertex corner ) {
		super("heart.png", corner, new Vertex(0,0));
	}

}
