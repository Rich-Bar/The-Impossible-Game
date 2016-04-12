package main.handler;
import java.util.LinkedList;

import main.type.GameObject;
import main.type.ObjectID;

import java.awt.Graphics;
public class Handler
{
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    private GameObject tempObject;
    
    private KeyInput key;
    
    private int RPosX, RPosY, playerX;
    public Handler(KeyInput key)
    {
        this.key = key;
    }
    
    public void tick(){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            if(tempObject.getId() == ObjectID.Player){
                playerX = (int)tempObject.getX();
                if(key.getD()){
                    tempObject.setVelX(5);
                }
                if(key.getA()){
                    tempObject.setVelX(0);
                }
                if(!key.getA()  && !key.getD()){
                    //tempObject.setVelX(0);
                }
                if(key.getW()){
                    //tempObject.setVelY(-10);
                }
                if(key.getSpace() && !tempObject.isJumping()){
                        tempObject.setJumping(true);
                        tempObject.setFalling(true);
                        tempObject.setVelY(-11);
                }
                if(tempObject.getVelX() == 0 && key.getEnter()){
                    tempObject.setVelX(5);
                }
                tempObject.tick(object);
                playerX = (int)tempObject.getX();
            }
        }
        
    }
    
    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    
    public void addObject(int pos, GameObject object){
        System.out.println(pos + " Pos");
        this.object.add(pos, object);
    }
    
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    
    public void removeObject(int i){
        object.remove(i);
    }
    
    public void clearObject(){
        object.clear();
    }
    
    public void setPlayerReStartPoint(int x, int y){
        RPosX = x;
        RPosY = y;
    }
    
    public int getRPosX(){
        System.out.println(RPosX);
        return RPosX;
    }
    
    public int getRPosY(){
        System.out.println(RPosY);
        return RPosY;
    }
    
    public int getPlayerX(){
        return playerX;
    }
}
