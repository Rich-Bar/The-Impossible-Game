package main.state;
import java.awt.Image;

import main.handler.Mouse;
import main.handler.ReadImage;
import main.handler.StateHandler;

import java.awt.Graphics;
public class MenuLevel
{
    private Game game;
    private Image i, passed;
    private Mouse mouse;
    StateHandler states;
    public MenuLevel(Game game, Mouse mouse, StateHandler states)
    {
        this.game = game;
        this.mouse = mouse;
        this.states = states;
        ReadImage loader = new ReadImage();
        try{
            i = loader.loadImage("/LevelMenu.png");
            passed = loader.loadImage("/Passed.png");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void tick(){
        int x = mouse.getPosX();
        int y = mouse.getPosY();
        if(x >= 102 && x <= 216 && y >= 37 && y <= 119){
            game.loadMap(1);
        }
        if(x >= 260 && x <= 406 && y >= 43 && y <= 108){
            game.loadMap(2);
        }
        if(x >= 460 && x <= 630 && y >= 38 && y <= 119){
            game.loadMap(3);
        }
    }
    
    public void render(Graphics g){
        g.drawImage(i, 0, 0, 800, 400, null);
        passedLevels(g);
    }
    
    public void passedLevels(Graphics g){
        if(states.getBoolean("level1")){
            g.drawImage(passed, 102, 37, 30, 30, null);
        }
        if(states.getBoolean("level2")){
            g.drawImage(passed, 260, 43, 30, 30, null);
        }
        if(states.getBoolean("level3")){
            g.drawImage(passed, 460, 38, 30, 30, null);
        }
    }
}
