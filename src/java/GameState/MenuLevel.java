package GameState;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Map;

import Texture.ReadImage;
import InPut.MouseInput;
import Main.Game;
import Main.StateHandler;
import Game.GameObject;
import Main.GameMode;
public class MenuLevel extends GameMode{

	private Game game;
    private Image i, passed, level1, level2, level3, level4, level5;
    private MouseInput mouse;
    StateHandler states;
    private boolean level1Button, level2Button, level3Button, level4Button, level5Button;
    private int width, height, yStart;
    public MenuLevel(Game game)//Konstruktor wird in init() durchgeführt
    {
        this.game = game;//Übernimmt Objecte
        mouse = game.getMouse();
        states = game.getStateHandler();
        loadImages();//Läd Bilder
        width = states.getInt("width");
        height = states.getInt("height");
        yStart = states.getInt("startY");
    }
    
    private void loadImages(){
        ReadImage loader = new ReadImage();
        try{//gibt fehler wenn Bild nicht gefunden wird
            i = loader.loadImage("LevelMenu.png");
            passed = loader.loadImage("Passed.png");
            level1 = loader.loadImage("Level1Icon.png");
            level2 = loader.loadImage("Level2Icon.png");
            level3 = loader.loadImage("Level3Icon.png");
            level4 = loader.loadImage("Level4Icon.png");
            level5 = loader.loadImage("Level5Icon.png");
        }catch(Exception e){
            e.printStackTrace();//Fehlermeldung
        }
    }
    
    public void tick(){//logische prozzese werden durchgeführt wird 60 in eine Secunde von der Game Klasse ausgeführt
        int x = mouse.getPosX();//X Koordinate wo die Mouse Gedrückt wurde
        int y = mouse.getPosY();//Y Koordinate wo die Mouse gedrückt wurde
        int xx = mouse.getXPos();//X Koordinate wo sich die Mouse befindet
        int yy = mouse.getYPos();//Y Koordinate wo sich die Mouse befindet
        if(x >= (int)width / 19.2 && x <= (int)width / 19.2 + (int)width / 16 && y >= (int)yStart + height / 10.8 && y <= (int)yStart + height / 10.8 + (int)height / 9){
            game.setGame("/Texture/Level1Map.png");
        }
        if(xx >= (int)width / 19.2 && xx <= (int)width / 19.2 + (int)width  / 16 && yy >= (int)yStart + height / 10.8 && yy <= (int)yStart + height / 10.8 + (int)height / 9){
            level1Button = true;//Wird gesetzt wenn sich mouse über dem level 1 Button befindet
        }else level1Button = false;//Wird gesetzt wenn sich mouse nicht über dem level 1 Button befindet
        if(x >= (int)width / 7.11 && x <= (int)width / 7.11 + width / 16 && y >= (int)yStart + height / 10.8 && y <= (int)yStart + height / 10.8 + (int)height / 9){
        	game.setGame("/Texture/Level2Map.png");
        }
        if(xx >= (int)width / 7.11 && xx <= (int)width / 7.11 + width / 16 && yy >= (int)yStart + height / 10.8 && yy <= (int)yStart + height / 10.8 + (int)height / 9){
            level2Button = true;//wird gesetzt wenn sich mouse über dem level2 Button befindet
        }else level2Button = false;//wird gesetzt wenn sich mouse nicht über dem level2 Button befindet
        if(x >= (int)width / 4.36 && x <= (int)width / 4.36 +  width / 16 && y >= (int)yStart + height / 10.8 && y <= (int)yStart + height / 10.8 + (int)height / 9){
        	game.setGame("/Texture/Level3Map.png");
        }
        if(xx >= (int)width / 4.36 && xx <= (int)width / 4.36 +  width / 16 && yy >= (int)yStart + height / 10.8 && yy <= (int)yStart + height / 10.8 + (int)height / 9){
            level3Button = true;//wird gesetzt wenn sich mouse über dem level3 Button befindet
        }else level3Button = false;//wird gesetzt wenn sich mouse nicht über dem level3 Button befindet
        if(x >= (int)width / 3.15 && x <= (int)width / 3.15 + (int)width / 16 && y >= (int)yStart + height / 10.8 && y <= (int)yStart + height / 10.8 + (int)height / 9){
        	game.setGame("/Texture/Level4Map.png");
        }
        if(xx >= (int)width / 3.15 && xx <= (int)width / 3.15 + (int)width / 16 && yy >= (int)yStart + height / 10.8 && yy <= (int)yStart + height / 10.8 + (int)height / 9){
            level4Button = true;//Wird gesetzt wenn sich mouse über dem level 1 Button befindet
        }else level4Button = false;//Wird gesetzt wenn sich mouse nicht über dem level 1 Button befindet
        if(x >= (int)width / 2.46 && x <= (int)width / 2.46 + (int)width / 16 && y >= (int)yStart + height / 10.8 && y <= (int)yStart + height / 10.8 + (int)height / 9){
        	game.setGame("/Texture/Level5Map.png");
        }
        if(xx >= (int)width / 2.46 && xx <= (int)width / 2.46 + (int)width / 16 && yy >= (int)yStart + height / 10.8 && yy <= (int)yStart + height / 10.8 + (int)height / 9){
            level5Button = true;//Wird gesetzt wenn sich mouse über dem level 1 Button befindet
        }else level5Button = false;//Wird gesetzt wenn sich mouse nicht über dem level 1 Button befindet
    }
    
    public void render(Graphics g){//Rendert das Levelmenu
        //g.drawImage(i, 0, 0, 800, 400, null);
        if(level1Button){
            g.drawImage(level1, (int)(width / 18.29) , (int)(yStart + height / 10.29), (int)(width / 17.45), (int)(height / 9.81), null);//wenn sich die Mouse über dem Button befindet wird er leicht verschoben und kleiner dargestellt
        }else g.drawImage(level1, (int)(width / 19.2), (int)(yStart + height / 10.8), (int)(width / 16), (int)(height / 9), null);
        if(level2Button){
            g.drawImage(level2, (int)(width / 6.98), (int)(yStart + height / 10.29), (int)(width / 17.45), (int)(height / 9.81), null);//wenn sich die Mouse über dem Button befindet wird er leicht verschoben und kleiner dargestellt
        }else g.drawImage(level2, (int)(width / 7.11), (int)(yStart + height / 10.8), (int)(width / 16), (int)(height / 9), null);
        if(level3Button){
            g.drawImage(level3, (int)(width / 4.31), (int)(yStart + height / 10.29), (int)(width / 17.45), (int)(height / 9.81), null);//wenn sich die Mouse über dem Button befindet wird er leicht verschoben und kleiner dargestellt
        }else g.drawImage(level3, (int)(width / 4.36), (int)(yStart + height / 10.8), (int)(width / 16), (int)(height / 9), null);
        if(level4Button){
            g.drawImage(level4, (int)(width / 3.12), (int)(yStart + height / 10.29), (int)(width / 17.45), (int)(height / 9.81), null);
        }else g.drawImage(level4, (int)(width / 3.15), (int)(yStart + height / 10.8), (int)(width / 16), (int)(height / 9), null);
        if(level5Button){
            g.drawImage(level5, (int)(width / 2.45), (int)(yStart + height / 10.29), (int)(width / 17.45), (int)(height / 9.81), null);
        }else g.drawImage(level5, (int)(width / 2.46), (int)(yStart + height / 10.8), (int)(width / 16), (int)(height / 9), null);
        passedLevels(g);
    }
    
    public void passedLevels(Graphics g){//setzt hacken wenn level bereits absolviert wurde
        if(states.getBoolean("level1")){//testet ob level bereits absolviert wurde
            if(!level1Button){
                g.drawImage(passed, (int)(width / 19.2), (int)(yStart + height / 10.8), 30, 30, null);//Wenn die Mouse auf dem Button liegt der Hacke verschoben sodas er nicht auf dem rand des Buttons liegt
            }else g.drawImage(passed, (int)(width / 18.29), (int)(yStart + height / 10.29), 30, 30, null);
        }
        if(states.getBoolean("level2")){//testet ob level bereits absolviert wurde
            if(!level2Button){
                g.drawImage(passed, (int)(width / 7.11), (int)(yStart + height / 10.8), 30, 30, null);//Wenn die Mouse auf dem Button liegt der Hacke verschoben sodas er nicht auf dem rand des Buttons liegt
            }else g.drawImage(passed, (int)(width / 6.98), (int)(yStart + height / 10.29), 30, 30, null);
        }
        if(states.getBoolean("level3")){//testet ob level bereits absolviert wurde
            if(level3Button){
                g.drawImage(passed, (int)(width / 4.31), (int)(yStart + height / 10.29), 30, 30, null);//Wenn die Mouse auf dem Button liegt der Hacke verschoben sodas er nicht auf dem rand des Buttons liegt
            }else g.drawImage(passed, (int)(width / 4.36), (int)(yStart + height / 10.8), 30, 30, null);
        }
        if(states.getBoolean("level4")){//testet ob level bereits absolviert wurde
            if(!level4Button){
                g.drawImage(passed, (int)(width / 3.15), (int)(yStart + height / 10.8), 30, 30, null);//Wenn die Mouse auf dem Button liegt der Hacke verschoben sodas er nicht auf dem rand des Buttons liegt
            }else g.drawImage(passed, (int)(width / 3.12), (int)(yStart + height / 10.29), 30, 30, null);
        }
        if(states.getBoolean("level5")){//testet ob level bereits absolviert wurde
            if(!level5Button){
                g.drawImage(passed, (int)(width / 2.46), (int)(yStart + height / 10.8), 30, 30, null);//Wenn die Mouse auf dem Button liegt der Hacke verschoben sodas er nicht auf dem rand des Buttons liegt
            }else g.drawImage(passed, (int)(width / 2.45), (int)(yStart + height / 10.29), 30, 30, null);
        }
    }

	@Override
	public String modeName() {
		// TODO Auto-generated method stub
		return "MenuLevel";
	}

	@Override
	public GameObject playerObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, List<GameObject>> getObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
