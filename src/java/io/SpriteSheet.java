package io;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
	private BufferedImage image;
    public SpriteSheet(BufferedImage image)//Dise Klasse ist dazu dar um die Texturen aus Bildern zu laden
    {
        this.image = image;
    }
    
    public BufferedImage grabImage(int col, int row, int width, int height){//hier wird die Reihe und die Zeile des Bildes angegeben sowie die Grösse in Pixeln
        BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
        return img;
    }
    
    public List<BufferedImage> getImages(int columns, int rows){
    	List<BufferedImage> res = new ArrayList<>();
    	for (int i = 0; i < columns; i++) {
    		for (int j = 0; j < rows; j++) {
				res.add(image.getSubimage(image.getWidth() / columns * i, image.getHeight() / rows * j, image.getWidth() / columns, image.getHeight() / rows));
			}
		}
    	return res;
    }
}
