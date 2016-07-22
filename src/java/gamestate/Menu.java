package gamestate;
import game.GameObject;
import input.KeyInput;
import input.MouseInput;
import io.ReadImage;
import main.Game;
import main.GameMode;
import main.StateHandler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import button.HoverButton;
public class Menu extends GameMode{
	private BufferedImage theImpossibleGame;
	private MouseInput mouse;
	private StateHandler stateHandler;
	private Game game;
	private KeyInput key;
	private int width, height, startY;
    
    private Map<String, HoverButton> buttons = new HashMap<>(); 
    
	public Menu(Game game){
		init();
		stateHandler = game.getStateHandler();
        this.game = game;
        this.mouse = game.getMouse(); 
        width = stateHandler.getInt("width");
        height = stateHandler.getInt("height");
        startY = stateHandler.getInt("startY");
        key = game.getKey();
	}
	@Override
	public void tick() {
        if(key.getEsc()){
        	System.exit(0);
        }
        for(Entry<String, HoverButton> entry : buttons.entrySet()){
        	//Update button to recent mouse moves
        	entry.getValue().mouseUpdate(mouse.getXPos(), mouse.getYPos());
        	
        	//Check if mouse is pressed and button hovered
        	if(mouse.isPressed() && entry.getValue().isHovered()){
        		switch(entry.getKey()){
        		case "start":
        			game.setGameMode(new MenuLevel(game));
        			break;
        		case "stats":
        			game.setGameMode(new MenuStates(game));
        			break;
        		case "help":
        			game.setGameMode(new MenuHelp(game));
        			break;
        		}
        	}
        }
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(theImpossibleGame, (int)(width / 5.67),startY + (int)(height / 5.61), (int)(width / 1.88),(int)(height / 5.23), null);//Überschrift
		for(HoverButton b : buttons.values()){
			b.render(g);
		}
    }

	private void init(){	
		buttons.put("start", new HoverButton("src/assets/Texture/StartButton.png", 30, 40, 1));
		buttons.put("stats", new HoverButton("src/assets/Texture/YourStats.png", 30, 50, 1));
		buttons.put("help", new HoverButton("src/assets/Texture/NeedSomeHelp.png", 30, 60, 1));
		
		ReadImage loader = new ReadImage();        
        theImpossibleGame = loader.loadImage("src/assets/Texture/TheImpossibleGame1.png");

	}
	@Override
	public String modeName() {
		return "Menu";
	}
	@Override
	public GameObject playerObject() {
		return null;
	}
	@Override
	public Map<Integer, List<GameObject>> getObject() {
		return null;
	}
}
