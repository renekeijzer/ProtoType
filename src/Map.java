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
	public Camera camera;
	public Player player;
	private float endpos;
	
	public Map(int level)
	{
		mapList = new ArrayList<ArrayList<Block>>();
	}
	
	public void Update()
	{
		/* Block Collision detection BEGIN */
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
					Block tmp = findNextSolidBlock(block.getPosition());
					if(block.intersects(tmp))
					{
						endpos = tmp.getPosition().y - BlockHeight;
						
						block.setPosition(new Vector2f(block.getPosition().x, endpos));
						block.setDownwardVelocity(0);
						block.setGrounded(true);
					}
				}				
			}	
		}
		/* Block Collision detection END */
		/* Player Collision detection BEGIN*/
		if(!player.isGrounded())
		{
			Block tmp = findNextSolidBlock(player.getPosition());
			System.out.println(tmp);
			if(tmp != null)
			{
				if(player.intersects(tmp))
				{
					endpos = tmp.getPosition().y - player.getHeight();
				
					player.setPosition(new Vector2f(player.getPosition().x, endpos));
					player.setDownwardVelocity(0);
					player.setGrounded(true);
				}
			}
			else
			{
				
				
			}
			
			
		}
		else
		{
			if(isFalling(player))
			{
				player.setGrounded(false);
			}
		}	
	}	
		/* Player Collision detection END*/
	
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
			player = new Player(new Rectangle(new Vector2f(100, 100), 30, 62));
			camera = new Camera(player);
			Game.Components.add(player);
			Game.Components.add(camera);
			
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		}
	
	
		public Block findNextSolidBlock(Vector2f pos){
			for(int i = ((int)pos.y+32)/32; i < mapList.size(); i++)
			{
				switch(mapList.get(i).get((int)pos.x/32).getBlockType())
				{
				case "Air":
				case "Water":
				case "Lava":
					break;
				case "Solid":
				case "Stone":
				case "Sand":
				case "Gravel":
				case "Spikes":
				case "Wood":
				case "Ice":
				case "Glass":
					return mapList.get(i).get((int)pos.x/32);
				}
					
				
			}
			return null;
		}
		
		public boolean isFalling(MovableGameComponent comp)
		{
			Vector2f tmppos = comp.getPosition();

			int y = (int) (tmppos.y / 32);
			int x = (int) (tmppos.x / 32);

			if(y > mapList.size() || x > mapList.get(y).size())
			{
				return false;
			}
			
			if(!Block.isSolid(mapList.get(y).get(x).getBlockType()))
			{
				return true;
			}
			return false;
			
		}
	}



