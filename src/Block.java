import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import Shapes.Rectangle;



public class Block extends MovableGameComponent {
	private Rectangle rect;
	private Texture texture;
	private Vector2f Position, velocity;
	private String BlockType;
	private boolean grouded;

	public int Width = 0, Height = 0;

	
	
	public Vector2f getVelocity() 				{		return this.velocity;}
	public Vector2f getPosition() 				{		return this.Position;}
	public String getBlockType() 				{		return this.BlockType;}
	public boolean isGrounded()					{ 		return this.grouded;}	
	
	public void setGrounded(boolean g)			{		this.grouded = g;}
	public void setVelocity(Vector2f velocity) 	{		this.velocity = velocity;}
	public void setDownwardVelocity(float y)	{		this.velocity.y = y;}
	public void setLinearVelocity(float x)		{		this.velocity.x = x;}
	public void setPosition(Vector2f position)  {		this.Position = position;}
	public void setBlockType(String blockType) 	{		this.BlockType = blockType;}

	public Block(Rectangle rectangle, int type)
	{
		this.grouded = false;
		this.velocity = new Vector2f(0,0);
		this.Width = BlockWidth;
		this.Height = BlockHeight;
		this.rect = rectangle;
		this.BlockType = strTypes[type];
		this.Position = this.rect.getPosition();
		
		try {
			if(BlockType!="Air"){
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("Assets/" + BlockType
							+ "/tile0.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
		
	}
	
	@Override
	public void Update() {
		Position.x += velocity.x;
		Position.y += velocity.y;
		this.rect.setPosition(Position);
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
	
	
	public static boolean isAffectedByGravity(String Type)
	{
		switch(Type)
		{
		case "Sand":
		case "Gravel":
			return true;
		default:
			return false;
		
		}
	}
	
	public String toString()
	{
		return BlockType + " - " + Position;
		
	}
	
	public boolean intersects(Block b)
	{
		if(this.rect.intersect(b.rect) && b.getBlockType() != "Air")
		{
			return true;
		}
		return false;
	}
}
