import org.lwjgl.util.vector.Vector2f;

import Shapes.Rectangle;


public abstract class MovableGameComponent extends GameComponent {

	public Rectangle rect;
	public MovableGameComponent()
	{
		Initialize();
	}
	
	public abstract Vector2f getPosition();
	public abstract void Update();
	public abstract void Draw();
	public abstract void Initialize();
	public abstract int getHeight();
	public abstract int getWidth();
	
	public boolean intersects(MovableGameComponent b)
	{
		if(this.rect.intersect(b.rect))
		{
			return true;
		}
		return false;
	}

	public boolean intersects(Block b) {
		return false;
	}

}
