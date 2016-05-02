package Game;

import java.awt.Rectangle;

public class Player extends GameObject{

	private int posX, posY;
	private int reSetX, reSteY; 
	public Player(int x, int y, ObjectID id){
		super(x,y, id);
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void reSetPlayer(){
		posX = reSetX;
		posY = reSteY;
	}
	
	public int getX(){
		return posX;
	}
	
	public int getY(){
		return posY;
	}
	

}
