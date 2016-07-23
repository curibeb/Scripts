package scripts.tutorials.fishing.utilities;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Vars {

	// In this class you will be storing your variables.
	
	// for example the fishing net is an item that has a constant id.
	// Now public refers to whether this variable is allowed to be used outside this class.
	// if i wanted to not allow it to be used outside the class i would call it private.
	// so whats with the static you may ask?
	// well lets see.
	// we can see it gave an error when we tried to set the value of final to 0
	// that is because we called final in the declaration of the variable
	// so the final states that our variable should always be constant and will never chance in value initially declared.
	
	// now lets collect the variables needed to create the fishing script
	// we have the fishing net id.
	// now we need to debug the entity of the fishing spot
	// we can do that by tool > debug entity
	// now we came to realize that the fishing spot is an npc
	// that will be our npc name
	// now what we need to do is declare the banking area and the fishing area
	// to create an area we need to call the class RSArea
	// now how to we create the area?
	// debuging position will show the position of the tile
	// now this will be the first tile we use to create a rectangle
	// now lets declare our fishing spot area
	// now we need to specify tiles away from the fishing spot so that we ensure that the fishing spots are withing the area
	
	public static final RSArea fishingArea = new RSArea (new RSTile (3080,3221,0),new RSTile (3095,3238,0));
	public static final RSArea bankArea = new RSArea(new RSTile(3092,3246,0),new RSTile(3095,3240,0));
	public static final int fishingNetID = 303;
	public static final String fishingSpot = "Fishing spot";
}
