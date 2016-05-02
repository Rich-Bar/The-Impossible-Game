package GameState;
import Main.GameMode;
import Texture.ReadImage;
import Main.StateHandler;
import InPut.KeyInput;
import InPut.MouseInput;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

//import Game.GameObject;
import Main.Game;
public class Menu extends GameMode{
	private boolean loaded;
	private BufferedImage background, start1, start2 ,theImpossibleGame, yourStates, yourStates2, needSomeHelp, needSomeHelp2;
	private MouseInput mouse;
	private StateHandler stateHandler;
	private Game game;
	private KeyInput key;
	private int width, height, startY;
    private boolean startButton, yourStatesButton, needSomeHelpButton;
	public Menu(Game game){
		init();
		stateHandler = game.getStateHandler();
        this.game = game;
        this.mouse = game.getMouse(); 
        width = stateHandler.getInt("width");
        height = stateHandler.getInt("height");
        startY = stateHandler.getInt("startY");
        key = game.getKey();
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		int x = mouse.getPosX();//Xkoordinate wo das die Mouse gedrückt ist
        int y = mouse.getPosY();//Y Koordinate wo die Mouse gedrückt ist
        int xx = mouse.getXPos();//X Koordinate wo die Mouse hinzeigt
        int yy = mouse.getYPos();//Y Koordinate wo die Mouse hinzeigt
        if(x >= (int)(width / 5.8) && x <= ((int)(width / 5.8) + width / 5) && y >= startY + (int)(height / 1.97) && y <= startY + ((int)(height / 1.97) + height / 11)){//Testet ob die Mouse in einen Bereich gedrückt wurde
        	game.setGameMode(new MenuLevel());
        }
        if(xx >= (int)(width / 5.8) && xx <= ((int)(width / 5.8) + width / 5) && yy >= startY + (int)(height / 1.97) && yy <= startY + ((int)(height / 1.97) + height / 11)){//Testet ob die Mouse sich in einem bereich liegt
            startButton = true;//wird gesetzt wenn sich die mouse auf dem start Button liegt
        }else{startButton = false;}//wird gesetzt wenn sich die mouse nicht auf dem start Button liegt
        if(xx >= (int)(width / 1.72) && xx <= ((int)(width / 1.72 + width / 4.56)) && yy >= startY + (int)(height / 1.97) && yy <= startY + ((int)(height / 1.97) + (int)(height / 12.19))){//Testet ob die Mouse sich in einem bereich liegt
            yourStatesButton = true;//wird gesetzt wenn sich die mouse auf dem States Button liegt
        }else{yourStatesButton = false;}//wird gesetzt wenn sich die mouse nicht auf dem States Button liegt
        if(x >= (int)(width / 1.72) && x <= (int)((width / 1.72) + (width / 4.56)) && y >= startY + (int)(height / 1.97) && y <= startY + ((int)(height / 1.97) + (int)(height / 12.19))){//Testet ob die Mouse in einen Bereich gedrückt wurde
        	game.setGameMode(new MenuStates(game));
        }
        if(xx >= (int)(width / 2.03) && xx <= ((int)(width / 2.03) + (int)(width / 3.43)) && yy >= startY + (int)(height / 1.27) && yy <= startY + ((int)(height / 1.27) + (int)(height / 11.82))){//Testet ob die Mouse sich in einem bereich liegt
            needSomeHelpButton = true;//wird gesetzt wenn sich die Mouse über dem Help Button befindet
        }else{needSomeHelpButton = false;}//wird gesetzt wenn sich die Mouse nicht über dem Help Button befindet
        if(x >= (int)(width / 2.03) && x <= ((int)(width / 2.03) + (int)(width / 3.43)) && y >= startY + (int)(height / 1.27) && y <= startY + ((int)(height / 1.27) + (int)(height / 11.82))){//Testet ob die Mouse in einen Bereich gedrückt wurde
        	game.setGameMode(new MenuHelp(game));
        }
        if(key.getEsc()){
        	System.exit(0);
        }
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		if(startButton){
            g.drawImage(start2, (int)(width / 5.8),startY + (int)(height / 1.97), width / 5, height / 11, null);//Wird gezeichnet wenn sich die Mouse auf dem StartButton befindet
        }else{
            g.drawImage(start1, (int)(width / 5.8),startY + (int)(height / 1.97), width / 5, height / 11, null);//Wird gezeichnet wenn sich die Mouse nicht auf dem StartButton befindet
        }
        g.drawImage(theImpossibleGame, (int)(width / 5.67),startY + (int)(height / 5.61), (int)(width / 1.88),(int)(height / 5.23), null);//Überschrift
        if(yourStatesButton){
            g.drawImage(yourStates2, (int)(width / 1.72),startY + (int)(height / 1.97), (int)(width / 4.56),(int)(height / 12.19), null);//Wird gezeichnet wenn sich die Mouse über dem States Button befindet
        }else{
            g.drawImage(yourStates, (int)(width / 1.72),startY + (int)(height / 1.97), (int)(width / 4.56),(int)(height / 12.19), null);//Wird gezeichnet wenn sich die Mosue nicht über dem States Button befindet
        }
        if(needSomeHelpButton){
            g.drawImage(needSomeHelp2, (int)(width / 2.03),startY + (int)(height / 1.27), (int)(width / 3.43),(int)(height / 11.82), null);//Wird gezeichnet wenn sich die Mouse über den Help Button befindet
        }else{
            g.drawImage(needSomeHelp, (int)(width / 2.03),startY + (int)(height / 1.27), (int)(width / 3.43),(int)(height / 11.82), null);//Wird gezeichnet wenn sich die Mouse nicht über dem Help Button befindet
        }
     
	}

	private void init(){
		loaded = false;
		ReadImage loader = new ReadImage();        
        try{
            background = loader.loadImage("/Texture/MenuBackground4.png");
            start1 = loader.loadImage("/Texture/StartButton1.png");
            start2 = loader.loadImage("/Texture/StartButton2.png");
            theImpossibleGame = loader.loadImage("/Texture/TheImpossibleGame1.png");
            yourStates = loader.loadImage("/Texture/YourStates1.png");
            yourStates2 = loader.loadImage("/Texture/YourStates2.png");
            needSomeHelp = loader.loadImage("/Texture/NeedSomeHelp.png");
            needSomeHelp2 = loader.loadImage("/Texture/NeedSomeHelp2.png");
            loaded = true;
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	@Override
	public String modeName() {
		// TODO Auto-generated method stub
		return "Menu";
	}
	@Override
	public GameObject playerObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
