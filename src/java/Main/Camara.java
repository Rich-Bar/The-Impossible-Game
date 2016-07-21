package Main;
import Game.GameObject;
public class Camara {
	private int x,y;
	private int width, height;
    public Camara(Game game, int x, int y)
    {
        this.x = x;
        this.y = y;
        width = game.getStateHandler().getInt("width") / 2;
        height = game.getStateHandler().getInt("height") / 2;
        
    }
    
    public void tick(GameObject player){
        x = -player.getX()  + width;
        //System.out.println(x);
        //System.out.println(player.getY());
        //System.out.println(y - (-player.getY() + height + 100));
        if(y - (-player.getY()  + height + 100) >= 3){//wenn er zuchoch ist wird die Welt nach untenverschoben
            y = -player.getY() + height + 100;
        }
        if( y - (-player.getY() + height - 100) <= -3){//wenn er zu niedrig ist wird die Welt nach unten verschoben
             y = -player.getY() + height - 100;
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
