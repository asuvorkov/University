package name.panitz.game.framework;

public class ImageObject<I> extends AbstractGameObject<I>{
  String imageFileName;
  I img;
	
  public ImageObject(String imageFileName) {
    super(0,0);
    this.imageFileName = imageFileName;
  }
	
  public ImageObject(String imageFileName, Vertex pos, Vertex motion) {
    super(0, 0, pos, motion);
    this.imageFileName = imageFileName;
  }
  public ImageObject(String imageFileName, Vertex position) {
    super(0, 0, position);
    this.imageFileName = imageFileName;
  }

  public ImageObject(String imageFileName, double width) {
    super(width);
    this.imageFileName = imageFileName;
  }
	
  @Override
  public void paintTo(GraphicsTool<I> g) {
    if (null==img) img = g.generateImage(imageFileName,this);
    if (null!=img) g.drawImage(img, getPos().x, getPos().y);
  }
}

