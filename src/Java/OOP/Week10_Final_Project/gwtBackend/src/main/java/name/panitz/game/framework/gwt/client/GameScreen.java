package name.panitz.game.framework.gwt.client;

import name.panitz.game.framework.GameLogic;
import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.KeyCode;
import name.panitz.game.framework.SoundObject;
import name.panitz.game.framework.Button;

import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.media.client.Audio;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class GameScreen extends VerticalPanel{
  Canvas canvas;
  Canvas backBuffer;
  final int refreshRate = 13;
  int height ;
  int width;

  Context2d context;
  Context2d backBufferContext;

  GWTSoundTool soundTool = new GWTSoundTool();
  
  final CssColor redrawColor = CssColor.make("rgba(200,0,0,0.6)");

  final Timer timer = new Timer() {
    @Override
    public void run() {
       doUpdate();
       for (SoundObject<Audio> so:game.getSoundsToPlayOnce()){
          so.playSound(soundTool);
       }
       game.getSoundsToPlayOnce().clear();
    }
  };

  GameLogic<ImageElement,Audio> game;

  public GameScreen(GameLogic<ImageElement,Audio> game) {
    this.game = game;
    width = (int) game.getWidth();
    height = (int) game.getHeight();
    canvas = Canvas.createIfSupported();
    backBuffer = Canvas.createIfSupported();

    Panel vp = this;
    Panel hp = new HorizontalPanel();
	
    Label blankLabel = new Label(" ");
    blankLabel.setPixelSize(40, 40);
			
    vp.add(hp);
    vp.add(canvas);

    HorizontalPanel buttonsPanel = new HorizontalPanel();
    for (Button b:game.getButtons()){
      com.google.gwt.user.client.ui.Button bb
	=new com.google.gwt.user.client.ui.Button(b.name,(ClickEvent ev)->{Window.alert("How high?");b.action.run();}); 
      buttonsPanel.add(bb);
      bb.setEnabled(true);
    }
    
    vp.add(buttonsPanel);
    // init the canvases
    canvas.setWidth(width + "px");
    canvas.setHeight(height + "px");
		
    canvas.setCoordinateSpaceWidth(width);
    canvas.setCoordinateSpaceHeight(height);
    backBuffer.setCoordinateSpaceWidth(width);
    backBuffer.setCoordinateSpaceHeight(height);

    context = canvas.getContext2d();
    backBufferContext = backBuffer.getContext2d();


    //    canvas.setFocus(true);
    canvas.addKeyPressHandler((ev)->{
      game.keyPressedReaction(KeyCode.fromCode(ev.getNativeEvent().getKeyCode()));
    });
    timer.scheduleRepeating(refreshRate);
    timer.run();

    
  }

  void checkForEndOfGame() {
    if (game.isStopped()) {
      timer.cancel();
    }
  }
	
  void doUpdate() {
    game.move();
    game.doChecks();
    backBufferContext.setFillStyle(redrawColor);
    backBufferContext.fillRect(0, 0, width, height);
    game.paintTo(new GWTGraphics(backBufferContext));
    context.drawImage(backBufferContext.getCanvas(), 0, 0);
    canvas.setFocus(true);
  }
}

