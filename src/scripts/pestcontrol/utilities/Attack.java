package scripts.pestcontrol.utilities;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Game;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSNPC;

public class Attack {

	public static Filter<RSNPC> filter(RSArea area) {
		return org.tribot.api2007.ext.Filters.NPCs.inArea(area);
	}

	public static RSNPC target(RSArea area) {
		RSNPC[] target = NPCs.find(filter(area));
		if (target.length > 0) {
			for (RSNPC t : target) {
				if (t.getDefinition() != null) {
					if (!t.getDefinition().getName().equals("Void Knight")
							&& !t.getDefinition().getName().equals("Squire")
							&& !t.getDefinition().getName().equals("Portal")) {
						return t;
					}
				}
			}
		}
		return null;
	}

	public static void attack(RSArea area) {
		Vars.status = "Attacking npcs.";
		RSNPC target = target(area);
		if (target != null) {
			if (target.isOnScreen()) {
				if (Game.getUptext() != null) {
					if (Game.getUptext().contains("Attack")) {
						Mouse.click(1);
						Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								return Player.getRSPlayer().getInteractingCharacter() != null;
							}
						}, General.random(4000, 7000));
					} else {
						target.hover();
					}
				}
			} else {
				if (Player.getPosition().distanceTo(target) < 5)
					Camera.turnToTile(target);
				else
					Walking.walkTo(target);
			}
		}
	}

}
