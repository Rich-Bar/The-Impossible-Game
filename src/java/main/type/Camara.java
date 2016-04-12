package main.type;

import main.state.Game;

public class Camara 
{
    private float x,y;
    public Camara(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void tick(GameObject player){
        x = -player.getX() + Game.WIDTH/2;
        if(y - (-player.getY() + Game.HEIGHT/2 + 100) >= 3){
            y = -player.getY() + Game.HEIGHT/2 + 100;
        }
        if( y - (-player.getY() + Game.HEIGHT/2 - 100) <= -3){
             y = -player.getY() + Game.HEIGHT/2 - 100;
        }
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
}
