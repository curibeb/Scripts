package scripts.tutorials.fishing.methods;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSNPC;

import scripts.tutorials.fishing.utilities.Vars;

public class Fish {

	// this will be our fishing class where we will write the methods to fish

	// now how to determine if we need to fish?
	// we will need to check our animation
	// when we are idle out animation is -1
	// when we are fishing our animation changes

	private static boolean needToFish() {
		// this checks out player animation and will return true if its -1
		return Player.getAnimation() == -1;
	}

	// so we create a boolean to check if we are in the fishing area
	// just like we did in the paint

	public static boolean isInFishArea() {
		return Vars.fishingArea.contains(Player.getPosition());
	}

	public static void catchFish() {
		if (needToFish()) {
			// now this RSNPC array will collect all the npcs with the name we
			// stated in our variables class
			RSNPC[] fish = NPCs.findNearest(Vars.fishingSpot);
			// now our first check is to see if the array actually has a fishing
			// spot

			// if there is a fishing spot
			if (fish.length > 0) {
				// this will grab the first element in the array which as an
				// index of 0
				RSNPC spot = fish[0];
				// null check the spot
				if (spot != null) {
					// if the spot is valid and its on the screen
					if (spot.isOnScreen()) {
						// then click the spot
						if (spot.click("Net")) {
							// if we click the spot we should sleep
							// as you saw the script was spam clicking the
							// fishing spot
							// so we need to add a conditional sleep
							// if we meet the condition we carry on else we
							// sleep
							Timing.waitCondition(new Condition() {
								@Override
								public boolean active() {
									// the script will move on if we meet this
									// condition
									return Player.getAnimation() != -1;
								}
								// other wise it will sleep for this random
								// amount
							}, General.random(4000, 7000));
						}
					} else {
						// if the spot os not on the screen we walk to it
						Walking.blindWalkTo(spot);
					}
				}
			}
		}
	}

}
