package game;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Animation
{
    private int speed;
    private int frames;
    
    private int index = 0;
    private int count = 0;
    
    private BufferedImage[] images;
    private BufferedImage currentImg;
    
    public Animation(int speed, BufferedImage... args)//Dise Klasse ist für dei sprung animation da
    {
        this.speed = speed;
        images = new BufferedImage[args.length];
        for(int i = 0; i < args.length; i++){//läd jedes bild aus dem Char in das BufferdImage Arrey
            images[i] = args[i];
        }
        frames = args.length;
    }
    
    public void runAnimation(){//alle 10 ticks wird das bild geändert
        index++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }
    
    private void nextFrame(){//setzt das bild das in der animation angezeigt werden soll
        currentImg = images[count];
        count++;
        
        if(count >= frames){
            count = 0;
        }
        
    }
    
    public void drawAnimation(Graphics g, int x, int y){
        g.drawImage(currentImg, x, y, null);
    }
        
    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){//rendert das Bild das gerade ausgewählt ist
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
    }
}
