
public interface GlobalSettings {
	static int BlockWidth = 32;
	static int BlockHeight = 32;
	static int WindowWidth = 0;
	static int WindowHeight = 0;
	
	static float gravity = 0.5f;
	static float maxBlockVelocity = 10;
	
	static String[] strTypes = new String[] { "Air", "Solid", "Lava", "Water", "Sand",
			"Gravel", "Spikes", "Wood", "Stone", "Ice", "Mines", "Bombs",
			"Glass", };
}
