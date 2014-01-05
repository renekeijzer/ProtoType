import static org.lwjgl.opengl.GL11.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.lwjgl.opengl.Display;

import Screens.GameWindow;
import Screens.Window;


public class Game1 extends Game implements GlobalSettings{
	private Map map;
	
	public Game1()
	{
		do
		{
			glClear(GL_COLOR_BUFFER_BIT);
//			for(int i = Components.getComponents().size() - 1; i > 0 ; i--)
//			{
//				Components.getComponents().get(i).Update();
//				Components.getComponents().get(i).Draw();
//			}
			
			for(GameComponent Component : Components.getComponents())
			{
				Component.Update();
				Component.Draw();
			}
			Display.update();
		}
		while(!Display.isCloseRequested());
	}
	
	
	public void Initialize() {
		Window window = new GameWindow(WindowWidth, WindowHeight);
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
		map = new Map(2);
		map.GenerateLevel(2);
		Game.Components.add(map);
	}

}
