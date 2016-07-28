package scripts.flaxspinner.misc;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Objects;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;

import scripts.flaxspinner.positions.Areas;
import scripts.flaxspinner.positions.Tiles;
import scripts.flaxspinner.tasks.Spin;
import scripts.flaxspinner.utilities.Conditions;

public class Catherby {

	public static String status = "";
	
	public static void handleCatherby() {
		if (Areas.BUILDINGFIRSTFLOOR.getArea().contains(Spin.myTile())) {
			if (Interface.SPININTERFACE.open()) {
				status = "Closing interface";
				Interfaces.closeAll();
			} else {
				decendToGroundFloor();
			}
		}
		if (Areas.BUILDINGGROUNDFLOOR.getArea().contains(Spin.myTile())) {
			headToBankCatherby();
		}
		if (Spin.lost()) {
			status = "We are lost recovering.";
			WebWalking.walkToBank();
		}
	}
	
	public static void decendToGroundFloor() {
		status = "Climbing ladder down.";
		RSObject[] ladder = Objects.getAt(Tiles.CATHERBYFIRSTFLOORLADDERTILE.getTile());
		if (Spin.interactWithObject(ladder, "Climb-down")) {
			if (ladder[0].click("Climb-down")) {
				Timing.waitCondition(Conditions.buildingGroundFloor(), General.random(7500, 10000));
			}
		}
	}
	
	public static void headToBankCatherby() {
		status = "Heading to bank.";
		RSObject[] door = Objects.getAt(Tiles.CATHERBYDOORTILE.getTile());
		if (door.length > 0){
			if (Spin.interactWithObject(door, "Open")) {
				Timing.waitCondition(Conditions.objectAtCatherbyDoorTile(), General.random(7500, 10000));
			}
		}else{
			WebWalking.walkToBank();
		}
	}
	
	public static void accendBuilding() {
		status = "Climbing ladder up.";
		RSObject[] door = Objects.getAt(Tiles.CATHERBYDOORTILE.getTile());
		RSObject[] ladder = Objects.getAt(Tiles.CATHERBYGROUNDFLOORLADDERTILE.getTile());
		if (door.length > 0) {
			if (Spin.interactWithObject(door, "Open")) {
				Timing.waitCondition(Conditions.objectAtCatherbyDoorTile(), General.random(2500, 3500));
			}
		} else {
			if (Spin.interactWithObject(ladder, "Climb-up")) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return Areas.BUILDINGFIRSTFLOOR.getArea().contains(Spin.myTile());
					}
				}, General.random(2500, 3500));
			}
		}
	}

	

}
