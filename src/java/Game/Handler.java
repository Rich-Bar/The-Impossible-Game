package Game;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Main.Game;
import Main.GameMode;
import InPut.KeyInput;

public class Handler extends GameMode{

	private GameObject playerObject;
	private MapHandler mapHandler;
	private Game game;
	private KeyInput key;
	
	private static int jumpSpeed = -(int)11;
    private static int runSpeed = (int)6;
    private static int width, widthL, widthR;
    private int distance;
	private Map<Integer, List<GameObject>> object = new HashMap<>();
	private int cerrentX;
	
	public Handler(Game game){
		this.game = game;
		mapHandler = new MapHandler(this, game.getWidth());
		key = game.getKey();
		width = game.getWidth();
		widthL = width / 4;
		widthR = width + widthL;
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(playerObject == null)return;
        if(key.getEnter()){
            playerObject.reSetPlayer();
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
        //playerObject.tick(object);
        //System.out.println(playerX);
        setDistance();
        cerrentX = playerObject.getX() - 7;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		for(int x = playerObject.getX() - widthL; x <= widthR; x++){
			for(int y = 0; y <= object.get(x).size(); y++){
				object.get(x).get(y).render();ww
			}
		}
	}

	@Override
	public String modeName() {
		// TODO Auto-generated method stub
		return "Game";
	}

	@Override
	public GameObject playerObject() {
		// TODO Auto-generated method stub
		return playerObject;
	}
	
	public int getPlayerX(){
		return (int)playerObject.getX() / 32;
	}
	
	private void setDistance(){//Setzt die distanz des Spilers
        if((int)playerObject.getX() <= distance)return;
        distance = (int)playerObject.getX();
    }
    
    public int getDistance(){
        return distance;
    }
    
    public Map<Integer, List<GameObject>> getObject(){
    	return object;
    }
    
    public int getCerrentX(){
    	return cerrentX;
    
    }

}