package scripts.cowkiller.methods;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.cowkiller.utilities.Vars;

public class Bank {

	public static void bank() {
		if (Player.getPosition().getPlane() == 0) {
			if (Vars.lumbridgeGroundFloor.contains(Player.getPosition())) {
				clickStairCase(new RSTile(3204, 3207, 0), "Climb-up", new Condition() {
					@Override
					public boolean active() {
						return Player.getPosition().getPlane() == 1;
					}
				});
			} else {
				// if we arent there then lets get there
				WebWalking.walkTo(Vars.lumbGroundTile);
			}
		}
		if (Vars.lumbridgeSecondFloor.contains(Player.getPosition())) {
			clickStairCase(new RSTile(3204, 3207, 1), "Climb-up", new Condition() {
				@Override
				public boolean active() {
					return Player.getPosition().getPlane() == 2;
				}
			});
		}
		if (Vars.lumbridgeThirdFloor.contains(Player.getPosition())) {
			bankItems();
		}
	}

	public static void bankItems() {
		// first we check if the bank screen is open
		if (Banking.isBankScreenOpen()) {
			// then we deposit all
			if (Banking.depositAll() > 0) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return Inventory.getAll().length == 0;
					}
				}, General.random(4000, 7000));
			}
		} else {
			// if its not open we open it
			if (Banking.openBank()) {
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						return Banking.isBankScreenOpen();
					}
				}, General.random(4000, 7000));
			}
		}
	}

	public static void clickStairCase(RSTile tile, String action, Condition condition) {
		RSObject[] stair = Objects.getAt(tile);
		if (stair.length > 0) {
			if (stair[0].isOnScreen()) {
				if (stair[0].click(action)) {
					Timing.waitCondition(condition, General.random(4000, 6000));
				}
			} else {
				WebWalking.walkTo(stair[0]);
			}
		}
	}

}
