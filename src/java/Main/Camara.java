package Main;
import Game.GameObject;
public class Camara {
	private int x,y;
    public Camara(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void tick(GameObject player){
        x = -player.getX() + Game.WIDTH/2;
        if(y - (-player.getY() + Game.HEIGHT/2 + 100) >= 3){//wenn er zuchoch ist wird die Welt nach untenverschoben
            y = -player.getY() + Game.HEIGHT/2 + 100;
        }
        if( y - (-player.getY() + Game.HEIGHT/2 - 100) <= -3){//wenn er zu niedrig ist wird die Welt nach unten verschoben
             y = -player.getY() + Game.HEIGHT/2 - 100;
        }
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
