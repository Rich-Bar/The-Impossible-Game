import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
public class Player extends GameObject
{    
    private float width = 32, height = 32;
    
    private float gravity = 0.8f;
    private float maxSpeed = 8;
    
    private Handler handler;
    
    private Game game;
    
    Texture tex = Game.getInstance();
    
    private Animation playerWalk;
    public Player(float x, float y, Handler handler, ObjectID id, Game game)
    {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        
        playerWalk = new Animation(3, tex.player[2], tex.player[3], tex.player[4], tex.player[4]);
    }
    
    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;
        if(falling || jumping){
            velY += gravity;
            
            if(velY > maxSpeed){
                velY = maxSpeed;
            }
        }
        collision(object);
        playerWalk.runAnimation();
    }
    
    private void collision(LinkedList<GameObject> object){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            
            
            if(tempObject.getId() == ObjectID.Block){
                
                if(getBounds().intersects(tempObject.getBounds())){
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                    
                }else{falling = true;}
                
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getY() +height;
                    velY = 0;
                }
                
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    //x = tempObject.getX() + 33  ;
                    //velX = 0;
                }
                
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    //x = tempObject.getX() - 33;
                    //velX = 0;
                    reSetPlayer();
                }
            }
            
            if(tempObject.getId() == ObjectID.Fence){
                
                if(getBounds().intersects(tempObject.getBounds())){
                    reSetPlayer();
                }
                
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    reSetPlayer();
                }
                
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    reSetPlayer();
                }
                
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    reSetPlayer();
                }
            }
            
            if(tempObject.getId() == ObjectID.Finish){
                
                if(getBounds().intersects(tempObject.getBounds()) || getBoundsTop().intersects(tempObject.getBounds()) || getBoundsLeft().intersects(tempObject.getBounds()) || getBoundsRight().intersects(tempObject.getBounds())){
                    finish();
                    game.rightState(game.getPlayingLevel(), true);
                    game.saveStates();
                }
            }
        }
    }
    
    private void reSetPlayer(){
        x = handler.getRPosX();
        y = handler.getRPosY();
        velX = 0;
    }
    
    private void finish(){
        game.setSTATE(1);
        handler.clearObject();
    }
    
    public void render(Graphics g){
        g.setColor(Color.blue);
        if(velX != 0){
            g.drawImage(tex.player[1], (int)x, (int)y, (int)width, (int)height, null);
        }else{
            g.drawImage(tex.player[0], (int)x, (int)y, (int)width, (int)height, null);
        }
        if(falling == true && jumping == true){
            playerWalk.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
        }
        /*Graphics2D g2d = (Graphics2D) g;
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());*/
        
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
    }
    
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) y, (int)width/2, (int)height/2);
    }
    
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y+5, (int)3, (int)height-12);
    }
    
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x+ width- 5), (int) y+5, (int)3, (int)height -10);
    }
}
