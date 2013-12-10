import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import Shapes.Rectangle;


public class Map extends GameComponent {
	
	private ArrayList<ArrayList<Block>> mapList;
	
	public Map(int level)
	{
		
	}
	
	public void Update()
	{
		
		
	}
	
	@SuppressWarnings("static-access")
	public void GenerateLevel(int level)
	{
		Vector2f TempPos = new Vector2f(0,0); 
		ArrayList<Block> temprow = new ArrayList<Block>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("levels/level" + level + ".txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				String[] tmp = line.split(",");
				for(String block : tmp)
				{
					String[]tmp2  = block.split(":");
					for(int i = 0; i < Integer.parseInt(tmp2[1]); i++)
					{
						Block tmpBlock = new Block(new Rectangle(TempPos, Block.Width, Block.Height), Integer.parseInt(tmp2[0]));
						//Components.add(tmpBlock);

						tmpBlock = null;
						
						TempPos.x += Block.Width;
					}
					
				}
				TempPos.y += Block.Height;
				TempPos.x = 0;
			}
			
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		}
	}

