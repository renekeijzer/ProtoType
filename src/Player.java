import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glVertex2f;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

import Shapes.Rectangle;

public class Player extends MovableGameComponent implements GlobalSettings {
	private Vector2f Position, velocity;
	private float maxVelocity, minVelocity;
	private boolean Grounded;
	private Rectangle rect;

	
	public Vector2f getPosition() 				{		return this.Position;		}
	public boolean isGrounded() 				{		return this.Grounded;			}
	
	public void setDownwardVelocity(float i) 	{		this.velocity.y = i;}
	public void setPosition(Vector2f position) 	{		this.Position = position;}
	public void setGrounded(boolean grounded) 	{		this.Grounded = grounded;}

	public Player(Rectangle rect) {
		this.minVelocity = -10;
		this.maxVelocity = 10;
		this.velocity = new Vector2f(0, 0);
		this.Position = rect.getPosition();
		this.Grounded = false;
		this.rect = rect;

	}

	@Override
	public void Update() {
		if(!Grounded)
		{
			if(velocity.y < maxPlayerVelocity)
			{
				velocity.y += gravity;
			}
		}
		
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			if (velocity.x > minVelocity) {
				velocity.x = velocity.x - 0.5f;
			}
		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			if (velocity.x < maxVelocity) {
				velocity.x = velocity.x + 0.5f;
			}
		} else {
			if (velocity.x < 0) {
				velocity.x = velocity.x + 0.5f;
			} else if (velocity.x > 0) {
				velocity.x = velocity.x - 0.5f;
			}
		}

		Position.x += velocity.x;
		Position.y += velocity.y;
	}

	@Override
	public void Draw() {
		Color.white.bind();
		glBegin(GL_QUADS);
		glColor3f(0.5f, 0.5f, 1.0f);
		glVertex2f(Position.x, Position.y);
		glVertex2f(Position.x, Position.y + rect.getHeight());
		glVertex2f(Position.x + rect.getWidth(), Position.y + rect.getHeight());
		glVertex2f(Position.x + rect.getWidth(), Position.y);
		glEnd();

	}

	@Override
	public void Initialize() {
		glEnable(GL_TEXTURE_2D);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

	}
	
	@Override
	public boolean intersects(Block b) {
		if(this.rect.intersect(b.getRectangle()))
		{
			return true;
		}
		return false;
	}
	@Override
	public int getHeight() {
		return rect.getHeight();
	}
	@Override
	public int getWidth() {
		return rect.getWidth();
	}


	

}
