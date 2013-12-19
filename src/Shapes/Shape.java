package Shapes;

import org.lwjgl.util.vector.Vector2f;

import Shapes.Shape;

public interface Shape {
	
	
	public abstract Vector2f getPosition();
	public abstract void setPosition(Vector2f pos);
	
	public abstract int getHeight();
	public abstract int getWidth();
	
	public abstract void resize(int Width, int Height);
	public abstract boolean intersect(Shape a);
}
