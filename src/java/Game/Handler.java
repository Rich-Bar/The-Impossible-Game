package game;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import input.KeyInput;
import main.Game;
import main.GameMode;

public class Handler extends GameMode{

	private int width;
	private GameObject playerObject;
	public Map<Integer, List<GameObject>> object = new HashMap<>();
	
	private KeyInput key;
	
    private static int jumpSpeed = -(int)11;
    private static int runSpeed = (int)6;

    private int RPosX, RPosY, playerX;

    private int distance;
    
    private MapHandler map;
    
    public boolean blabla = false;
	public Handler(Game game, String path){
		key = game.getKey();
		width = game.getWidth();
		map = new MapHandler(this, width, path);
	}

	@Override
	public void tick() {
		map.tick();
		playerObject.tick();
		playerX = playerObject.getX();
		
		if(key.getEnter()){
            playerObject.reSetPlayer();//wenn Enter gedrückt wurde dann wir der Spieler zu dem AnfangsPunkt zurückgesetzt
        }
		if(key.getSpace() && !playerObject.isJumping() && playerObject.getVelX() != 0){//wenn der Spiler nicht steht oder springt oder am falle und space gedrückt wurde ist dann wird er in nagativer an der y Achse Beschleunigt
            playerObject.setJumping(true);
            playerObject.setFalling(true);
            playerObject.setVelY(jumpSpeed);
        }
        if(key.getSpaceR() && playerObject.getVelX() == 0){//Wenn der Spieler steht und Space gedrückt wurde dann wird der Spieler positv an der X Achse Beschleunigt
            playerObject.setVelX(runSpeed);
            key.setSpaceR(false);
        }else key.setSpaceR(false);
        setDistance();
	}

	@Override
	public void render(Graphics g) {
		playerObject.render(g);
		for(int x = (playerX - width) /32; x <= (playerX + width) /32; x++){
			//System.out.println("redereeesdf    " + x);
			if(object.containsKey(x))for(GameObject objectttt : getObject().get(x)){
									//System.out.println(getObject());
									objectttt.render(g);
									}
		}
	}

	@Override
	public String modeName() {
		return "Game";
	}

	@Override
	public GameObject playerObject() {
		//System.out.println("PlayerObject????");
		return playerObject;
	}

	public void addPlayer(int x, int y) {
		playerObject = new Player(x, y, ObjectID.Player, this, Game.getTextures());
		System.out.println("Player added!");
	}
	
	private void setDistance(){
		if(playerX >= distance)distance = playerX;
	}
	
	public void setPlayerReStartPoint(int x, int y){//setzt der respawne punkt des Spielers
        RPosX = x;
        RPosY = y;
    }
	
	public int getRPosX(){
        //System.out.println(RPosX);
        return RPosX;
    }
    
    public int getRPosY(){
        //System.out.println(RPosY);
        return RPosY;
    }
    
    public int getPlayerX(){
        //System.out.println("handler getPlayerX() : " + playerX);
        return playerX;
    }
    
    public void addObject(int x, List<GameObject> list){
    	object.put(x, list);
    }
    
    public void clearObject(int x){
    	object.remove(x);
    }
    
    public void addListObject(int x, List<GameObject> list){
    	object.put(x, list);
    	System.out.println(object.get(x));
    }

	@Override
	public Map<Integer, List<GameObject>> getObject() {
		return object;
	}
	
}