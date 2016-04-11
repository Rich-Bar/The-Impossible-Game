import java.awt.Graphics;
import java.awt.Image;
public class Menu 
{
    private Image i;
    private Game game;
    private Mouse mouse;
    private StateHandler stateHandler;
    int xx, yy;
    public Menu(Game game, Mouse mouse, StateHandler handler)
    {
        stateHandler = handler;
        this.game = game;
        ReadImage loader = new ReadImage();
        this.mouse = mouse; 
        try{
            i = loader.loadImage("/MenuBackground.png");
        }catch(Exception e){
            e.printStackTrace();
        }
        xx = (stateHandler.getInt("width")) / 4 ;
        yy = ((stateHandler.getInt("height")) / 4) - 200;
    }
        
    public void tick(){
        int x = mouse.getPosX();
        int y = mouse.getPosY();
        if(x >= 303 + xx && x <= 401 + xx && y >= 177 + yy && y <= 212 + yy){
                game.setSTATE(2);
        }
    }
    
    public void render(Graphics g){
        g.drawImage(i, xx, yy,800, 400, null);
    }
}
