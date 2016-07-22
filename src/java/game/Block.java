package game;

import java.awt.Graphics;
import java.awt.Rectangle;

import io.Texture;
import main.Game;

public class Block extends GameObject{

	private int type;
	Texture tex = Game.getTextures();
	public Block(float x, float y, int type, ObjectID id) {
		super(x, y, id);
		this.type = type;
		System.out.println("Block added! Pos = " + x/32 + " : " + y/32);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		//System.out.println("I bin a Bl�ck");
		g.drawImage(tex.block[type], (int)x, (int)y, 32, 32, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
        return new Rectangle((int) x, (int) y, 32, 32);
	}

}
