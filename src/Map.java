import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import Shapes.Rectangle;


public class Map extends GameComponent {
	
	private ArrayList<ArrayList<Block>> mapList;
	
	public Map(int level)
	{
		mapList = new ArrayList<ArrayList<Block>>();
	}
	
	public void Update()
	{
		MapDebugger debug = new MapDebugger();
		for(ArrayList<Block> list : mapList)
		{
			for(Block block : list)
			{
				if(Block.isAffectedByGravity(block.getBlockType()) && !block.isGrounded())
				{	
					if(block.getVelocity().y < maxBlockVelocity){
						block.getVelocity().y += gravity;
					}
					block.setDownwardVelocity(block.getVelocity().y);
					Block tmp = mapList.get((int) (block.getPosition().y/BlockWidth)).get((int)block.getPosition().x/BlockHeight);
					
					try {
						debug.WriteMaptoLog(mapList);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println(block);
					//System.out.println(block.getPosition().y/BlockWidth + " - "+block.getPosition().x/BlockHeight);
					//System.out.println(tmp);
					
					if(block.intersects(tmp))
					{
						block.setDownwardVelocity(0);
						block.setGrounded(true);
					}
				}				
			}
			
		}
	}
	
	public void GenerateLevel(int level)
	{
		int Tempx = 0;
		int Tempy = 0;
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
				temprow = new ArrayList<Block>();
				for(String block : tmp)
				{
					String[]tmp2  = block.split(":");
					for(int i = 0; i < Integer.parseInt(tmp2[1]); i++)
					{
						Block tmpBlock = new Block(new Rectangle(new Vector2f(Tempx, Tempy), BlockWidth, BlockHeight), Integer.parseInt(tmp2[0]));
						Game.Components.add(tmpBlock);
						temprow.add(tmpBlock);						
						Tempx += BlockWidth;
					}
				}
				mapList.add(temprow);
				temprow = null;
				Tempy += BlockHeight;
				Tempx = 0;
			}
			
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		}
	}



