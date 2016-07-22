package scripts.FlaxSpinner.Misc;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.FlaxSpinner.Positions.Areas;
import scripts.FlaxSpinner.Positions.Tiles;
import scripts.FlaxSpinner.Tasks.Spin;

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
					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							return !Objects.isAt(Tiles.LUMBYDOORTILE.getTile(), "Door");
						}
					}, General.random(2500, 3500));
				}
			}
		}
	}

	public static void bankLumby(RSTile tile, String action) {
		RSObject[] ladder = Objects.getAt(tile);
		if (Spin.interactWithObject(ladder, action)) {
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					return Areas.LUMBYSECONDFLOOR.getArea().contains(Spin.myTile());
				}
			}, General.random(2500, 3500));
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
					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							return Areas.LUMBYTHIRDFLOOR.getArea().contains(Spin.myTile());
						}
					}, General.random(2500, 3500));
				}
			}
		} else {
			RSObject[] door = Objects.getAt(Tiles.LUMBYDOORTILE.getTile());
			Spin.status = "Open door.";
			if (Spin.interactWithObject(door, "Open")) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return !Objects.isAt(Tiles.LUMBYDOORTILE.getTile(), "Door");
					}
				}, General.random(2500, 3500));
			}
		}
	}

}
