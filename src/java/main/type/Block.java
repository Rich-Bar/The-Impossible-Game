package main.type;

import java.util.LinkedList;

import main.state.Game;

import java.awt.Graphics;
import java.awt.Rectangle;


public class Block extends GameObject
{
    private int type;
    Texture tex = Game.getInstance(); 
    public Block(float x, float y, int type, ObjectID id)
    {
        super(x, y, id);
        this.type = type;
    }
    
    public void tick(LinkedList<GameObject> object) {
        
    }
    
    public void render(Graphics g){
        if(type == 0){
            g.drawImage(tex.block[0], (int)x, (int)y, 32, 32, null);
        }
        if(type == 1){
            g.drawImage(tex.block[1], (int)x, (int)y, 32, 32, null);
        }
        if(type == 2){
            g.drawImage(tex.block[2], (int)x, (int)y, 32, 32, null);
        }
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
