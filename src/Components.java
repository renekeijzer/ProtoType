import java.util.ArrayList;

	public class Components
	{
		private ArrayList<GameComponent> Components;
		
		public ArrayList<GameComponent> getComponents()		{return Components;}
		
		public Components()
		{
			Components = new ArrayList<GameComponent>();
		}
		
		public void add(GameComponent comp)
		{
			Components.add(comp);
		}
		
		public void remove(GameComponent comp)
		{
			Components.remove(comp);
		}
		
	}