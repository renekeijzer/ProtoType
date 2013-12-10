
public abstract class MovableGameComponent extends GameComponent {

	public MovableGameComponent()
	{
		Initialize();
	}
	
	public abstract void Update();
	public abstract void Draw();
	public abstract void Initialize();

}
