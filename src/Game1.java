import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import Screens.GameWindow;
import Screens.Window;


public class Game1 extends Game{
	private Map map;
	
	public Game1()
	{
		
		do
		{
			for(GameComponent Component : Game.Components.getComponents())
			{
				Component.Update();
				Component.Draw();
			}
		}
		while(!Display.isCloseRequested());
	}
	
	
	public void Initialize() {
		Window window = new GameWindow(840, 480);
		glEnable(GL_BLEND);
		glViewport(0,0, Window.getWidth(), Window.getHeight());
		glMatrixMode(GL_MODELVIEW);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Window.getWidth(), Window.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);		
		
	}

	@Override
	public void LoadContent() {
		map = new Map(1);
		map.GenerateLevel(1);
	}

}
