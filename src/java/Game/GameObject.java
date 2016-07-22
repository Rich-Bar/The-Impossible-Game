package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected float x, y;
    protected ObjectID id;
    protected float velX = 0 , velY = 0;
    protected boolean falling = true;
    protected boolean jumping = false;
    
    public GameObject(float x, float y, ObjectID id){//Diese informationnen müss alle gameObjecte/ Tiles verfügen
        this.x = x;
        this.y = y;
        this.id = id;
    }
	public abstract void tick();
	public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    
	public void reSetPlayer(){
	}
	
	public int getX(){
		return (int) (x / 32);
	}
	
	public int getY(){
		return (int) (y / 32);
	}
	public ObjectID getId() {
		return id;
	}
	public void setId(ObjectID id) {
		this.id = id;
	}
	public float getVelX() {
		return velX;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public float getVelY() {
		return velY;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	public boolean isFalling() {
		return falling;
	}
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	public boolean isJumping() {
		return jumping;
	}
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
}
