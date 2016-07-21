package GameState;
import java.awt.Graphics;
import Main.GameMode;
import java.awt.Image;
import java.util.List;
import java.util.Map;

import Main.Game;
import InPut.KeyInput;
import Texture.ReadImage;
import Main.StateHandler;
import Game.GameObject;

import java.awt.Font;
import java.awt.Color;
public class MenuStates extends GameMode
{
    private Game game;
    private KeyInput key;
    private StateHandler statesHandler;
    private Image[] pages = new Image[5];
    private Image passedImage, passedNotImage;
    private int page;
    private int maxPage = 5;
    private int width, height, startY;
    private boolean passed;
    public MenuStates(Game game)//Konstruktor wird in init() augeführt
    {
        this.game = game;//Überschreibt Objecte
        this.key = game.getKey();
        this.statesHandler = game.getStateHandler();
        getGraphics();//Laä die Bilder
        width = statesHandler.getInt("width");//Bildschirm auflössung
        height = statesHandler.getInt("height");
        startY = statesHandler.getInt("startY");
    }
    
    private void getGraphics(){
        ReadImage loader = new ReadImage();
        try{
            pages[0] = loader.loadImage("/StatesLevel1.png");
            pages[1] = loader.loadImage("/StatesLevel2.png");
            pages[2] = loader.loadImage("/StatesLevel3.png");
            pages[3] = loader.loadImage("/StatesLevel4.png");
            pages[4] = loader.loadImage("/StatesLevel5.png");
            passedImage = loader.loadImage("/Passed.png");
            passedNotImage = loader.loadImage("/PassedNot.png");
        }catch(Exception e){
            e.printStackTrace();//Gibt fehlermeldung wenn ein Bild nicht gefunden wurde
        }
    }
    
    public void tick(){//logische prozesse die durch die klasse Game 60 mal in einer Secunde aufgerufen werden
        if(key.getSpaceR()){//wenn space losgelassen wird dann 
            page++;//wird auf die nächste seite geschaltet
            if(page >= maxPage){//wenn es bereits die letzte seit war wird auf die erste Seite zurückgesetzt
                page = 0;
            }
            key.setSpaceR(false);
        }
        switch(page){//testet ob das level das auf der Steite beschrieben wird bereits fertig absolviert wurde
            case 0: passed = statesHandler.getBoolean("level1"); break;
            case 1: passed = statesHandler.getBoolean("level2"); break;
            case 2: passed = statesHandler.getBoolean("level3"); break;
            case 3: passed = statesHandler.getBoolean("level4"); break;
            case 4: passed = statesHandler.getBoolean("level5"); break;
        }
    }
    
    public void render(Graphics g){//Zeichnet das bild
        g.drawImage(pages[page], 0, startY, width, height, null);
        if(passed){
            g.drawImage(passedImage, (int)(width / 2.12), startY + (int)(height / 2.71), (int)(width / 22.59), (int)(height / 12.71), null);// wenn das level bereits absolviert wurde wird ein Hacken gezeichnet
        }else{
            g.drawImage(passedNotImage, (int)(width / 2.12),startY +  (int)(height / 2.71), (int)(width / 22.59), (int)(height / 12.71), null);//wenn nicht dann ein kreuz
        }
        g.setFont(new Font("TimesRoman", Font.PLAIN, 75));//zeigt was die weiteste distanc in dem level war
        g.setColor(Color.white);
        g.drawString(String.valueOf(statesHandler.getInt("level" +(page + 1))), 1150, startY + 684);
    }

	@Override
	public String modeName() {
		// TODO Auto-generated method stub
		return null;
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
