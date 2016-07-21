package Texture;

import java.awt.image.BufferedImage;

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
}
