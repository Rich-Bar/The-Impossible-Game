package Texture;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ReadImage {
	 private BufferedImage image;
	    public BufferedImage loadImage(String path)//Diese Klasse ist nur dazu dar um BufferdImages zu Laden
	    {
	        try{
	        	image = ImageIO.read(this.getClass().getResource(path));
	        } catch(IOException e){
	        System.out.println("Error at ReadImage");}
	        return image;
	    }
}
