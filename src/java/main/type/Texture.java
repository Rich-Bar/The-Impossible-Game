package main.type;
import java.awt.image.BufferedImage;

import main.handler.ReadImage;
public class Texture
{
    SpriteSheet bs, ps, os;
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    private BufferedImage others_sheet = null;
    
    public BufferedImage[] block = new BufferedImage[3];
    public BufferedImage[] player = new BufferedImage[6];
    public BufferedImage[] others = new BufferedImage[3];
    public Texture()
    {
        ReadImage loader = new ReadImage();
        try{
            block_sheet = loader.loadImage("/BlockSheet.png");
            player_sheet = loader.loadImage("/PlayerSheet.png");
            others_sheet = loader.loadImage("/OthersSheet.png");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        os = new SpriteSheet(others_sheet);
        
        getTextures();
    }
    
    private void getTextures(){
        block[0] = bs.grabImage(1, 1, 64, 64);//Hindernis
        block[1] = bs.grabImage(2, 1, 64, 64);//Boden/Wand
        block[2] = bs.grabImage(3, 1, 64, 64);//Ziel
        
        player[0] = ps.grabImage(1, 1, 32, 32);
        player[1] = ps.grabImage(2, 1, 32, 32);
        player[2] = ps.grabImage(3, 1, 32, 32);
        player[3] = ps.grabImage(4, 1, 32, 32);
        player[4] = ps.grabImage(5, 1, 32, 32);
        player[5] = ps.grabImage(6, 1, 32, 32);
        
        others[0] = os.grabImage(1, 1, 32, 32);//nummer 1
        others[1] = os.grabImage(2, 1, 32, 32);//nummer 2
        others[2] = os.grabImage(3, 1, 32, 32);//nummer 3
    }

}
