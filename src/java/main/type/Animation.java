package main.type;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Animation
{
    private int speed;
    private int frames;
    
    private int index = 0;
    private int count = 0;
    
    private BufferedImage[] images;
    private BufferedImage currentImg;
    
    private Graphics2D g2d;
    
    private int rotate;
    public Animation(int speed, BufferedImage... args)
    {
        this.speed = speed;
        images = new BufferedImage[args.length];
        for(int i = 0; i < args.length; i++){
            images[i] = args[i];
        }
        frames = args.length;
    }
    
    public void runAnimation(){
        index++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }
    
    private void nextFrame(){
        for(int i= 0 ; i < frames; i++){
            if(count == i){
                currentImg = images[i];
                rotate += 20;
            }
        }
        
        count++;
        
        if(count > frames){
            count = 0;
        }
        
    }
    
    public void drawAnimation(Graphics g, int x, int y){
        g.drawImage(currentImg, x, y, null);
        g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(rotate));
    }
        
    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
        //g2d = (Graphics2D) g;
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
        //g2d.rotate(Math.toRadians(rotate));
        //g2d.rotate(Math.toRadians(0));
    }
    
    public void setRotate(int i){
        rotate = i;
    }
}
