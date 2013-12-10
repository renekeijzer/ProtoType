import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import Shapes.Rectangle;



public class Block extends MovableGameComponent {
	private Rectangle rect;
	private Texture texture;
	private Vector2f Position, velocity;
	private String BlockType;
	private boolean AbG, isFalling;

	public final static int Width = 32, Height = 32;

	public Vector2f getPosition() 				{		return this.Position;}
	public String getBlockType() 				{		return this.BlockType;}
	public void setPosition(Vector2f position)  {		this.Position = position;}
	public void setBlockType(String blockType) 	{		this.BlockType = blockType;}
	
	private String[] strTypes = new String[] { "Air", "Solid", "Lava", "Water", "Sand",
			"Gravel", "Spikes", "Wood", "Stone", "Ice", "Mines", "Bombs",
			"Glass", };

	public Block(Rectangle rect, int type)
	{
		this.rect = rect;
		this.BlockType = strTypes[type];
		
	}
	
	@Override
	public void Update() {
		System.out.println("as");
		
	}
	@Override
	public void Draw() {
		Color.white.bind();
		if(BlockType!="Air"){	
		texture.bind();
		glBegin(GL_QUADS);

		int x = (int) Position.x;
		int y = (int) Position.y;

		int xb = (int) Position.x + Width;
		int yb = (int) Position.y + Height;

		glTexCoord2f(0, 0);
		glVertex2f((float) x, (float) y);

		glTexCoord2f(0, 1);
		glVertex2f((float) x, (float) yb);

		glTexCoord2f(1, 1);
		glVertex2f((float) xb, (float) yb);

		glTexCoord2f(1, 0);
		glVertex2f((float) xb, (float) y);
		glEnd();
		}
		
		
	}
	@Override
	public void Initialize() {
		try {
			glEnable(GL_TEXTURE_2D);
			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
}
