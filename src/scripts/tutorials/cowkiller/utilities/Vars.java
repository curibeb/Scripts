
package scripts.tutorials.cowkiller.utilities;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Vars {

	// hello again, its me c#2bot
	// today we will be making a cow killing script
	// we will start of work by following exact procedures from last tutorial
	// however this time we will be using a tool to make our lives easier
	// lets create lumbridge area

	public static final RSArea lumbridgeGroundFloor = new RSArea(new RSTile(3201, 3201, 0), new RSTile(3227, 3235, 0));

	// second floor will be the same area however will have a high pleane
	// which will be one
	public static final RSArea lumbridgeSecondFloor = new RSArea(new RSTile(3201, 3201, 1), new RSTile(3227, 3235, 1));

	// same goes for the third floor, however will have 2 as its place
	public static final RSArea lumbridgeThirdFloor = new RSArea(new RSTile(3201, 3201, 2), new RSTile(3227, 3235, 2));

	// now lets use a method to check our area
	// now that all our areas in lumbridge castle are okay
	// lets go on marking the area between the cow place and the castle

	public static final RSArea betweenCowsAndCastle = new RSArea(new RSTile(3221, 3199, 0), new RSTile(3275, 3300, 0));

	// another thing look at how i am naming my convention, first world is
	// lowercase
	// words after are uppercase, thats the proper practice
	// remember we have to declare the variables modifier as public to access it
	// from another class
	// and also the static so we can call it without creating an instance of the
	// class
	public static final RSArea cowsArea = new RSArea(new RSTile(3239, 3300, 0), new RSTile(3270, 3255, 0));

	// now here we have an obstacle we have a gate, that could be closed
	// when we arrive, so how do we tackle it? okay lets grab the gates tile

	public static final RSTile gateTile = new RSTile(3253, 3267, 0);

	public static final RSTile canReach = new RSTile(3258, 3278, 0);

	// so our attacking is all good how about our looting we need to loot the
	// hide
	// however we want to only loot our kill so what do we do

	// so we got the dying animation of the cow lets declare it with the looting tile
	
	public static int deadCowAnimation = 5851;
	public static RSTile lootTile = null;
	public static RSTile lumbGroundTile = new RSTile(3215, 3219, 0);
	public static boolean shouldLoot = false;
}
