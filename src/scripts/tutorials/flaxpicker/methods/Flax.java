package scripts.tutorials.flaxpicker.methods;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSObject;

import scripts.tutorials.flaxpicker.utilities.Vars;

public class Flax {

	private static boolean isInFlaxArea() {
		// returns true if we are in the flax area.
		return Vars.flaxArea.contains(Player.getPosition());
	}

	public static void pick() {
		if (!isInFlaxArea()) {
			// if we are not in the flax area lets walk there
			Walking.blindWalkTo(Vars.flaxTile);
		} else {
			// well if we are there lets pick some flax
			RSObject[] flax = Objects.findNearest(20, Vars.flax);
			// we check if the object exists and list them in an area
			if (flax.length > 0) {
				RSObject f = flax[0];
				if (f != null) {
					// check if its on screen
					if (f.isOnScreen()) {
						//click it
						if (DynamicClicking.clickRSTile(f, "Pick"))
							//allow some sleep after picking
							General.sleep(500,1200);
					} else {
						// if the object is not on screen lets walk to it
						Walking.walkTo(f);
					}
				}
			}
		}
	}

}
