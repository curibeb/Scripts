package scripts.pestcontrol.utilities;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

public class Portals {

	public static boolean portalDead(int hp) {
		return hp == 0;
	}

	public static Filter<RSObject> filter(RSArea area) {
		return org.tribot.api2007.ext.Filters.Objects.inArea(area);
	}

	public static void attackPortalNpc(RSArea portal, RSArea gate) {
		if (portal.contains(Player.getPosition())) {
			if (Attack.target(portal) != null) {
				Attack.attack(portal);
			}
		} else {
			RSObject[] door = Objects.find(50, filter(gate));
			if (door.length > 0) {
				Vars.status = "To portal.";
				WebWalking.walkTo(centreTile(portal));
			} else {
				openPortalDoor(gate);
			}
		}
	}

	public static void openPortalDoor(RSArea area) {
		if (area.contains(Player.getPosition())) {
			Vars.status = "Open gate.";
			RSObject[] door = Objects.findNearest(10, "Gate");
			if (door.length > 0) {
				if (door[0].isOnScreen()) {
					if (DynamicClicking.clickRSObject(door[0], "Open")) {
						Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								return PathFinding.canReach(centreTile(area), false);
							}

						}, General.random(4000, 6000));
					}
				} else {
					Walking.blindWalkTo(door[0]);
				}
			}
		} else {
			Vars.status = "To gate";
			Walking.blindWalkTo(centreTile(area));
		}
	}

	public static RSTile centreTile(RSArea area) {
		return area.polygon.npoints > 0
				? new RSTile((int) Math.round(avg(area.polygon.xpoints)), (int) Math.round(avg(area.polygon.ypoints)))
				: null;
	}

	private static double avg(final int... nums) {
		long total = 0;
		for (int i : nums) {
			total += (long) i;
		}
		return (double) total / (double) nums.length;
	}

}
