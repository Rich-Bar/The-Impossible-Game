package main.handler;
import java.awt.image.BufferedImage;

import main.state.Game;
import main.type.Block;
import main.type.ObjectID;
import main.type.Player;
public class MapHandler implements Runnable
{
    private Handler handler;
    
    private Thread t;
    private boolean running = false;
    
    private BufferedImage level;
    private Game game;
    private int reihe = 0;
    
    private int xx = 50;
    private int yy, lastXX;
    
    public MapHandler(Handler handler, Game game)
    {
        this.handler = handler;
        this.game = game;
    }
    
    public void run(){
        firstRender();
        while(running){
            if(handler.getPlayerX() > lastXX){
                
            }
        }
    }
    
    public void start(){
        if(!running){
            t = new Thread(this);
            t.start();
            running = true;
        }
    }
    
    public void firstRender(){
        int h = level.getHeight();
        int x = 0;
        int position = 0;
        reihe = 0;
        while(x < h){
            System.out.println(x);
            for(int y = 0; y < 100; y++){
                addObject(position, x, y);
                position++;
            }
            position++;
            x++;
        }
    }
    
    protected void updateList(){
        int i = (reihe * yy) + 1;
        while(i < (reihe + 1) * yy){
            handler.removeObject(i);
            addObject(i, xx + reihe, i);
            i++;
        }
    }
    
    public void setLevel(BufferedImage image){
        level = image;
        yy = level.getHeight();
    }
    
    private void addObject(int pos, int x, int y){
        int pixel = level.getRGB(x, y);
        int red   = (pixel >> 16) & 0xff;//Filtert denn Rot anteil des Pixles heraus
        int green = (pixel >> 8) & 0xff;//Filtert denn Grün anteil des Pixles heraus
        int blue  =  pixel & 0xff;//Filtert denn Blau anteil des Pixles heraus
        if(red == 255 && green == 255 && blue == 255){//Wenn der Pixel Genau diese Werte Besitz dann wird ein Block zu der Welt hinzugefügt
            handler.addObject(pos, new Block(x * 32, y * 32, 1, ObjectID.Block));
        }
        if(red == 255 && green == 0 && blue == 0){//Wenn der Pixel Genau diese Werte Besitz dann wird ein Block zu der Welt hinzugefügt
            handler.addObject(pos, new Block(x * 32, y * 32, 0, ObjectID.Fence));
        }
        if(blue == 232){//Wenn der Pixel Genau diese Werte Besitz dann wird ein Spieler zu der Welt hinzugefügt
            handler.addObject(pos, new Player(x * 32, y * 32, handler, ObjectID.Player, game));
            //System.out.println(x + ":" + y);
            handler.setPlayerReStartPoint(x * 32, y * 32);
        }
        if(red == 255 && green == 255 && blue == 0){//Wenn der Pixel Genau diese Werte Besitz dann wird ein Ziel zu der Welt hinzugefügt
            handler.addObject(pos, new Block(x * 32, y * 32, 2, ObjectID.Finish));
            //System.out.println("Finish set");
        }
    }
}
