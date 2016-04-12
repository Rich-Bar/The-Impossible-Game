package main.handler;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
public class ReadImage
{
    private BufferedImage image;
    public BufferedImage loadImage(String path)
    {
        try{
            image = ImageIO.read(getClass().getResource(path));
        } catch(IOException e){
        System.out.println("Error at ReadImage");}
        return image;
    }
}
