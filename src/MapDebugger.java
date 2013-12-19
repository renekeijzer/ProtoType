

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MapDebugger {
	public MapDebugger()
	{
		
	}
	
	public void writeMapToConsole(ArrayList<ArrayList<Block>> mapList)
	{
		int x = 0;
		int y = 0;
		for(ArrayList<Block> row : mapList)
		{
			for(Object cell : row)
			{
				System.out.print("["+x+","+y+"]"+cell + ",");
				x++;
			}
			System.out.println();
			x=0;
			y++;
		}
	}
	
	public void WriteMaptoLog(ArrayList<ArrayList<Block>> mapList) throws FileNotFoundException, UnsupportedEncodingException
	{
		String tempstr = "";
		PrintWriter writer = null;
		writer = new PrintWriter("map_log.log", "UTF-8");
		for(int i = 0; i < mapList.size()-1; i++ )
		{
			for(int y = 0; y < mapList.get(i).size()-1; y++)
			{	
				tempstr = tempstr + "["+i+","+y+"]"+mapList.get(i).get(y) + ", ";
			}
			writer.println(tempstr);
			tempstr = "";
		}
		writer.close();
		System.exit(0);
	}
}
