package Shapes;

import Shapes.Shape;

public interface Shape {
	public abstract void resize(int Width, int Height);
	public abstract boolean intersect(Shape a, Shape b);
}
