package gamestate;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import button.IButton; 
import button.ToggleHoverButton;
import game.GameObject;
import input.MouseInput;
import io.ReadImage;
import main.Game;
import main.GameMode;
import main.StateHandler;
public class MenuLevel extends GameMode{

	private Game game;
    private Image i;
    private MouseInput mouse;
    StateHandler states;
    
    private Map<String, IButton> buttons = new HashMap<>();
    public MenuLevel(Game game)//Konstruktor wird in init() durchgeführt
    {
        this.game = game;//Übernimmt Object
        mouse = game.getMouse();
        states = game.getStateHandler();
        loadImages();//Lädt Bilder
    }
    
    private void loadImages(){
        ReadImage loader = new ReadImage();
            i = loader.loadImage("LevelMenu.png");
            buttons.put("Level1", new ToggleHoverButton("src/assets/Texture/Level1Icon.png", 5.5, 9.75, 1, states.getBoolean("level1")));
            buttons.put("Level2", new ToggleHoverButton("src/assets/Texture/Level2Icon.png", 14, 9.75, 1, states.getBoolean("level2")));
            buttons.put("Level3", new ToggleHoverButton("src/assets/Texture/Level3Icon.png", 22.5, 9.75, 1, states.getBoolean("level3")));
            buttons.put("Level4", new ToggleHoverButton("src/assets/Texture/Level4Icon.png", 31, 9.75, 1, states.getBoolean("level4")));
            buttons.put("Level5", new ToggleHoverButton("src/assets/Texture/Level5Icon.png", 39.5, 9.75, 1, states.getBoolean("level5")));
    }
    
    public void tick(){//logische prozzese werden durchgeführt wird 60 in eine Secunde von der Game Klasse ausgeführt
        for(Entry<String, IButton> entry : buttons.entrySet()){
        	//Update button to recent mouse moves
        	entry.getValue().mouseUpdate(mouse.getXPos(), mouse.getYPos());
        	
        	//Check if mouse is pressed and button hovered
        	if(mouse.isPressed() && entry.getValue().isHovered()){
        		game.setGame("src/assets/Texture/" + entry.getKey() + "Map.png");
        	}
        }
    }
    
    public void render(Graphics g){//Rendert das Levelmenu
        g.drawImage(i, 0, 0, 800, 400, null);
        for(IButton b : buttons.values()){
        	b.render(g);
        }
    }

	@Override
	public String modeName() {
		return "MenuLevel";
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
