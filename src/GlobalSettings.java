
public interface GlobalSettings {
	static int BlockWidth = 32;
	static int BlockHeight = 32;
	static int WindowWidth = 840;
	static int WindowHeight = 840;
	static int PlayerWidth = 30;
	static int PlayerHeight = 62;
	
	static float gravity = 0.3f;
	static float maxBlockVelocity = 15;
	static float maxPlayerVelocity = 15;
	
	static String[] strTypes = new String[] { "Air", "Solid", "Lava", "Water", "Sand",
			"Gravel", "Spikes", "Wood", "Stone", "Ice", "Mines", "Bombs",
			"Glass", };
}
