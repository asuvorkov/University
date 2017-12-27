package name.panitz.game.klaus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import name.panitz.game.framework.AbstractGame;
import name.panitz.game.framework.Button;
import name.panitz.game.framework.FallingImage;
import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.KeyCode;
import name.panitz.game.framework.SoundObject;
import name.panitz.game.framework.Vertex;

public class HeartsOfKlaus<I, S> extends AbstractGame<I, S> {
  List<Wall<I>> walls = new ArrayList<>();
  List<Heart<I>> hearts = new ArrayList<>();
  List<Barrel<I>> barrels = new ArrayList<>();
  int energy = 5;
  int collectedHearts = 0;
  Klaus<I> klaus;
  
  static final int GRID_WIDTH=34;
  
  String level1 = "w                 f  w\n" 
      + "whf         h    www w\n" 
      + "wwwww     wwww       w\n"
      + "w        h           w\n" 
      + "w       wwww       h w\n" 
      + "w                    w\n"
      + "w      hf          fhw\n" 
      + "w    wwww       wwwwww\n" 
      + "w                    w\n"
      + "wh             h     w\n" 
      + "wwwww       wwww     w\n" 
      + "w                    w\n"
      + "w p    h            hw\n"
      + "w   wwww        wwwwww\n"
      + "w                    w\n"
      + "w                   gw\n"
      + "wwwwwwww  wwwwwwwwwwww";

  public HeartsOfKlaus() {
    super(new Klaus<>(new Vertex(0,0)), 22*GRID_WIDTH, 17*GRID_WIDTH);

    klaus = (Klaus<I>) getPlayer();
    
    BufferedReader r = new BufferedReader(new StringReader(level1));
    int l = 0;
    try {
      for (String line=r.readLine(); line != null; line=r.readLine()){
        int col = 0;
        for (char c : line.toCharArray()) {
          switch (c) {
          case 'w':
            walls.add(new Wall<>
     	       (new Vertex(col * GRID_WIDTH, l * GRID_WIDTH)));
            break;
          case 'h':
            hearts.add(new Heart<>
       	        (new Vertex(col * GRID_WIDTH, l * GRID_WIDTH)));
            break;
          case 'f':
            barrels.add(new Barrel<>(
                new Vertex(col*GRID_WIDTH, l*GRID_WIDTH)));
            break;
          case 'p':
            getPlayer().getPos().moveTo(new Vertex
               (col * GRID_WIDTH, l * GRID_WIDTH-2));
            break;
          }
          col++;
        }
        l++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    getGOss().add(barrels);
    getGOss().add(walls);
    getGOss().add(hearts);
    getButtons().add(new Button("pause",()->pause()));
  }

  @Override
  public void paintTo(GraphicsTool<I> g) {
    super.paintTo(g);
    g.drawString(50, 10, "Energy: " + energy);
    g.drawString(50, 30, "Hearts: " + hearts.size());
  }

  SoundObject<S> crash = new SoundObject<S>("crash.wav");

  private void playerBarrelCollision() {
    for (Barrel<I> p : barrels) {
      if (p.touches(player)) {
        energy--;
        playSound(crash);
        p.fromTop(getWidth());
      }
      if (p.getPos().y > getHeight()) {
        p.fromTop(getWidth());
      }
    }
  }

  private void fallingBarrel() {
    for (FallingImage<I> p : barrels) {
      boolean isStandingOnTop = false;
      for (GameObject<I> wall : walls) {
        if (p.touches(wall)) {
          p.restart();
        }
        if (p.isStandingOnTopOf(wall)) {
          isStandingOnTop = true;
        }
      }
      if (!isStandingOnTop && !p.isJumping) {
        p.startJump(0.1);
      }
    }

  }

  private void checkPlayerWallCollsions() {
    boolean isStandingOnTop = false;
    for (GameObject<I> wall : walls) {
      if (player.touches(wall)) {
        klaus.stop();
        return;
      }
      if (player.isStandingOnTopOf(wall)) {
        isStandingOnTop = true;
      }
    }

    if (!isStandingOnTop && !klaus.isJumping)
      klaus.startJump(0.1);
  }

  private void collectHearts() {
    Heart<I> removeMe = null;
    for (Heart<I> heart : hearts) {
      if (getPlayer().touches(heart)) {
        removeMe = heart;
        collectedHearts++;
        break;
      }
    }
    if (removeMe != null)
      hearts.remove(removeMe);
  }

  @Override
  public void doChecks() {
    collectHearts();
    checkPlayerWallCollsions();
    fallingBarrel();
    playerBarrelCollision();
    if (getPlayer().getPos().y > getHeight()) {
      getPlayer().getPos()
        .moveTo(new Vertex(GRID_WIDTH, getHeight() - 80));
    }
  }

  @Override
  public void keyPressedReaction(KeyCode keycode) {
    if (keycode != null)
      switch (keycode) {
      case LEFT_ARROW:
        klaus.left();
        break;
      case RIGHT_ARROW:
        klaus.right();
        break;
      case UP_ARROW:
        klaus.jump();
        break;
      case DOWN_ARROW:
        klaus.stop();
        break;
        default:;
      }
  }

  public boolean lost() {
    return energy <= 0;
  }

  public boolean won() {
    return hearts.isEmpty();
  }

}
