import java.util.ArrayList;

public abstract class Game {
	
	public Game() {
		Initialize();
		LoadContent();
	}
	
	public abstract void Initialize();
	public abstract void LoadContent();	
}
