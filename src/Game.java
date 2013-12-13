import java.util.ArrayList;

public abstract class Game {
	
	public Game() {
		Initialize();
		LoadContent();
	}
	
	public abstract void Initialize();
	public abstract void LoadContent();
	
	public static class Components
	{
		private static ArrayList<GameComponent> Components;
		
		public static ArrayList<GameComponent> getComponents()		{return Components;}
		
		public static void add(GameComponent comp)
		{
			if(Components == null)
			{
				Components = new ArrayList<GameComponent>();
			}
			Components.add(comp);
		}
		
		public static void remove(GameComponent comp)
		{
			Components.remove(comp);
		}
		
	}
	
}
