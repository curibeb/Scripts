package scripts.flaxspinner.misc;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.flaxspinner.positions.Areas;
import scripts.flaxspinner.positions.Tiles;
import scripts.flaxspinner.tasks.Spin;
import scripts.flaxspinner.utilities.Conditions;

public class Lumby {

	public static String status = "";

	public static void handleLumby() {
		if (Spin.lost()) {
			Spin.status = "Lost recovering.";
			bankLumby(Tiles.LUMBYFIRSTFLOORLADDERTILE.getTile(), "Climb-up");
		}
		if (Spin.bank.contains(Spin.myTile())) {
			Spin.status = "To ladder.";
			bankLumby(Tiles.LUMBYTHIRDFLOORLADDERTILE.getTile(), "Climb-down");
		}
		if (Areas.LUMBYSECONDFLOOR.getArea().contains(Spin.myTile())) {
			if (PathFinding.canReach(new RSTile(3209, 3212, 1), true)) {
				Spin.spin();
			} else {
				Spin.status = "Open door.";
				RSObject[] door = Objects.getAt(Tiles.LUMBYDOORTILE.getTile());
				if (Spin.interactWithObject(door, "Open")) {
					Timing.waitCondition(Conditions.objectAtLumbyDoorTile(), General.random(2500, 3500));
				}
			}
		}
	}

	public static void bankLumby(RSTile tile, String action) {
		RSObject[] ladder = Objects.getAt(tile);
		if (Spin.interactWithObject(ladder, action)) {
			Timing.waitCondition(Conditions.lumbySecondFloor(), General.random(2500, 3500));
		}
	}

	public static void headToBankLumb() {
		if (PathFinding.canReach(new RSTile(3206, 3208, 1), true)) {
			Spin.status = "To bank.";
			if (Interface.SPININTERFACE.open()) {
				Interfaces.closeAll();
			} else {
				RSObject[] ladder = Objects.getAt(Tiles.LUMBYSECONDFLOORLADDERTILE.getTile());
				if (Spin.interactWithObject(ladder, "Climb-up")) {
					Timing.waitCondition(Conditions.lumbyThirdFloor(), General.random(2500, 3500));
				}
			}
		} else {
			RSObject[] door = Objects.getAt(Tiles.LUMBYDOORTILE.getTile());
			Spin.status = "Open door.";
			if (Spin.interactWithObject(door, "Open")) {
				Timing.waitCondition(Conditions.lumbyDoorTile(), General.random(2500, 3500));
			}
		}
	}

}
