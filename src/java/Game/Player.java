package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;

import Main.Game;
import Texture.Texture;

public class Player extends GameObject{


    private float width = 32, height = 32;
    private float gravity = 0.8f;
    private float maxSpeed = 8;
	private int posX, posY;
	private int reSetX, reSteY; 
	private Handler h;
	private Animation playerWalk;
	private Texture tex = Game.getTextures();
	public Player(int x, int y, ObjectID id, Handler h, Texture tex){
		super(x ,y, id);
		System.out.println(x + " : " + y);
		reSetX = x;
		reSteY = y;
		this.h = h;
		playerWalk = new Animation(3, tex.player[2], tex.player[3], tex.player[4], tex.player[4]);
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;//addirt die geschwindigkeit zu der Position
         y += velY;
        if(falling || jumping){//Wenn der Spieler am fliegen oder im Springen ist dann wird 
            velY += gravity;//die Y Koordinate solange erhöt bis wieder ein Block unter dem Spieler ist
            
            if(velY > maxSpeed){//Verhindert das der Spieler unendlich schnell fällt
                velY = maxSpeed;
            }
        }
        playerWalk.runAnimation();
		for(int xx = (int)x/32 - 1; xx <= x/32 + 1; xx++){
			//System.out.println(xx + " xx" +  h.getObject().containsKey(xx));
			if(h.getObject() != null && h.getObject().containsKey(xx))for(GameObject object : h.
					getObject().
					get(xx)){
				//System.out.println(xx + " xxowienrfvio");
				collusion(object);
			}
		}
		//System.out.println(x + " :    " + y);
	}

	private void collusion(GameObject tempObject){
		//System.out.println("Collusion Tested");
		if(tempObject.getId() == ObjectID.Block || tempObject.getId() == ObjectID.Ground){//wenn das Object ein Block oder Boden Object ist wird getestet ob es mit Dem Spieler Inteverirt
            
            if(getBounds().intersects(tempObject.getBounds())){//Wenn der Block unter dem Spiler ist wird der Spieler Über dem Block gehalten
                y = tempObject.getY() *32 - height;
                velY = 0;
                falling = jumping = false;
                //System.out.println("#######################Ground");
            }else{falling = true;}
            
            if(getBoundsTop().intersects(tempObject.getBounds())){
                reSetPlayer();
            }
            
            if(getBoundsLeft().intersects(tempObject.getBounds())){
                //x = tempObject.getX() + 33  ;
                //velX = 0;
            }
            
            if(getBoundsRight().intersects(tempObject.getBounds())){
                //x = tempObject.getX() - 33;
            	System.out.println("resetaernhttzmkreusrerbhn");
                velX = 0;
                reSetPlayer();
            }
		}
		if(tempObject.getId() == ObjectID.Fence){
			if(getBounds().intersects(tempObject.getBounds()) || getBoundsLeft().intersects(tempObject.getBounds()) || getBoundsRight().intersects(tempObject.getBounds()) || getBoundsTop().intersects(tempObject.getBounds())){
				reSetPlayer();
				velX = 0;
			}
		}
		
		if(tempObject.getId() == ObjectID.Finish){
			if(getBounds().intersects(tempObject.getBounds()) || getBoundsLeft().intersects(tempObject.getBounds()) || getBoundsRight().intersects(tempObject.getBounds()) || getBoundsTop().intersects(tempObject.getBounds())){
				finish();
			}
		}
	}
		
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
        if(falling == true && jumping == true){//Wenn er in der luft ist dann wird die animation durchgeführt
            playerWalk.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
            return;
        }
        if(velX != 0){//Wenn er nicht steht dann wird er so gezeichnet 
            g.drawImage(tex.player[1], (int)x, (int)y, (int)width, (int)height, null);
        }else{//Wenn er jedoch steht dann wird er vollgend gezeichnet
            g.drawImage(tex.player[0], (int)x, (int)y, (int)width, (int)height, null);
        }
	}
	
	private void finish(){
		System.exit(0);
	}
	
	public void reSetPlayer(){
		x = reSetX;
		y = reSteY;
	} 
	
	public int getX(){
		return (int) x ;
	}
	
	public int getY(){
		return (int)y;
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
