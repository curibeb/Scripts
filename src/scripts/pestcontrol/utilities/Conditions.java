package scripts.pestcontrol.utilities;

import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.pestcontrol.enums.Interface;

public class Conditions {

	public static Condition insideGameInterfaceOn() {
		return new Condition() {
			@Override
			public boolean active() {
				return !Interface.INSIDEGAME.text().contains("You must defend");
			}
		};
	}

	public static Condition insideLobbyBoat() {
		return new Condition() {
			@Override
			public boolean active() {
				return AreaCheck.isInsideLobbyBoat();
			}
		};
	}

	public static Condition playerInteracting() {
		return new Condition() {
			@Override
			public boolean active() {
				return Player.getRSPlayer().getInteractingCharacter() != null;
			}
		};
	}

	public static Condition canReachTile(RSTile tile) {
		return new Condition() {
			@Override
			public boolean active() {
				return PathFinding.canReach(tile, false);
			}
		};
	}

}
