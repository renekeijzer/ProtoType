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
                int x = (int) (player.getPosition().x / 32);
        		int y = (int) (player.getPosition().y / 32);
                
                if(!player.isGrounded())
                {
                	System.out.println(getNearestRightBlock(player.getPosition()));
                	Block tmp = findNextSolidBlock(new Vector2f(player.getPosition().x, player.getPosition().y + 32));
                	//Block tmp = findNextSolidBlock(player.getPosition());
                        if(tmp != null)
                        {
                                if(player.intersects(mapList.get(y).get(x)) && Block.isSolid(mapList.get(y).get(x).getBlockType()))
                                {
                                	player.setDownwardVelocity(0.5f);
                                }
                                else if(player.intersects(tmp))
                                {
                                        endpos = tmp.getPosition().y - player.getHeight();
                                
                                        player.setPosition(new Vector2f(player.getPosition().x, endpos));
                                        player.setDownwardVelocity(0);
                                        player.setGrounded(true);
                                }
                        }
                }
                else
                {
                	
                    if(isFalling(player))
                    {
                    	player.setGrounded(false);
                    }
                }
            	
                
        		if(x >=  0 && y >= 0 && y < mapList.size() && x <mapList.get(1).size())
        		{
        			if(player.getDirection() != 2)
        			{
            			if(Block.isSolid(mapList.get(y).get(x).getBlockType()) && player.intersects(mapList.get(y).get(x)))
            			{
            				player.setColided(true);
            				player.setHorizontalVelocity(0);
            			}
            			else
            			{
	            			player.setColided(false);
            			}
            			
            			if(Block.isSolid(mapList.get(y+1).get(x).getBlockType()) && player.intersects(mapList.get(y+1).get(x)))
            			{
            				player.setColided(true);
            				player.setHorizontalVelocity(0);
            			}
            			else
            			{
	            			player.setColided(false);
            			}
            		}
        			else if(player.getDirection() != 1)
            		{
	            		if(Block.isSolid(mapList.get(y).get(x+1).getBlockType()) && player.intersects(mapList.get(y).get(x+1)))
	            		{	
	            			player.setColided(true);
	            			player.setHorizontalVelocity(0);
	            		}
	            		else
	            		{
	            			player.setColided(false);
	            		}
	            		
	            		if(Block.isSolid(mapList.get(y+1).get(x+1).getBlockType()) && player.intersects(mapList.get(y+1).get(x+1)))
	            		{
	            			player.setColided(true);
	            			player.setHorizontalVelocity(0);
	            		}else
	            		{
	            			player.setColided(false);	
	            		}
	            		
            		}
        			
        		}
        	}
                /* Player Collision detection END*/
        
        public void GenerateLevel(int level)
        {
        		Boolean renderMap = false;
        		String[] blocks = null;
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
                            if(renderMap)
                            {
                            	blocks = line.split(",");
                            	for(String block : blocks)
                            	{
                            		Block tmpBlock = new Block(new Rectangle(Tempx, Tempy, BlockWidth, BlockHeight), Integer.parseInt(block)); 
                            		Game.Components.add(tmpBlock);
                            		temprow.add(tmpBlock);
                            		Tempx += 32;	
                            	}
                            	
                            	Tempx = 0;
                            	Tempy += 32;
                            	mapList.add(temprow);
                            	temprow=new ArrayList<Block>();
                            }   
                        	
                        	if(line.contains("data"))
                            {
                            	   renderMap = true;
                            }
                               
                               
                        }
                        renderMap = false;
                        player = new Player(new Rectangle(new Vector2f(100, 100), PlayerWidth, PlayerHeight));
                        camera = new Camera(player);
                        
                        Game.Components.add(player);
                        Game.Components.add(camera);
                }
                catch(IOException e)
                {
                	e.printStackTrace();
                }
            }
        
        
                public Block findNextSolidBlock(Vector2f pos){
                	if(((int) (pos.y / BlockHeight) + 1 < mapList.size()
                			&& (int) (pos.x / BlockWidth) < mapList.get(0).size())
                			&& ((int) (pos.x / BlockWidth) >= 0 
                			&& (int) pos.y / BlockHeight >= 0))
                	{
                		for(int i = (int) ((pos.y / 32) + 1); i < mapList.size(); i++)
                        {
                                switch(mapList.get(i).get((int)pos.x/32).getBlockType())
                                {
                                case "Air":
                                case "Water":
                                case "Lava":
                                	  switch(mapList.get(i).get((int)(pos.x/32)+1).getBlockType())
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
                                              return mapList.get(i).get((int)(pos.x/32)+1);
                                      }
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
                	}
                	return null;
                }
                
                public boolean isFalling(MovableGameComponent comp)
                {
                        Vector2f tmppos = comp.getPosition();

                        int y = (int) (tmppos.y / 32) + 2;
                        int x = (int) (tmppos.x / 32) + 1;

                        if(y > mapList.size() - 1 || x > mapList.get(y).size() - 1 || y < 0|| x < 0)
                        {
                                return true;
                        }
         
                        if(!Block.isSolid(mapList.get(y).get(x).getBlockType()) &&!Block.isSolid(mapList.get(y).get(x-1).getBlockType()))
                        {
                                return true;
                        }
                        return false;
                        
                }
                
                public Block getNearestRightBlock(Vector2f pos)
                {
                	if(((int) (pos.y / BlockHeight) + 1 < mapList.size()
                			&& (int) (pos.x / BlockWidth) < mapList.get(0).size())
                			&& ((int) (pos.x / BlockWidth) >= 0 
                			&& (int) pos.y / BlockHeight >= 0))
                	{
                		for(int i = (int) ((pos.x / 32) + 1); i < mapList.get((int)(pos.y/32)).size(); i++)
                        {
                                switch(mapList.get((int)pos.y/32).get(i).getBlockType())
                                {
                                case "Air":
                                case "Water":
                                case "Lava":
                                	  switch(mapList.get((int)(pos.y/32) + 1).get(i).getBlockType())
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
                                              return mapList.get((int)(pos.y/32) + 1).get(i);
                                      }
                                        break;
                                case "Solid":
                                case "Stone":
                                case "Sand":
                                case "Gravel":
                                case "Spikes":
                                case "Wood":
                                case "Ice":
                                case "Glass":
                                        return mapList.get((int)(pos.y/32)).get(i);
                                }
                        }
                	}
					return null;
                }
        }



 