package scripts.flaxspinner.tasks;

import java.awt.Color;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Screen;
import org.tribot.api.Timing;
import org.tribot.api.input.Keyboard;
import org.tribot.api.types.colour.Tolerance;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.flaxspinner.antiban.Antiban;
import scripts.flaxspinner.misc.Catherby;
import scripts.flaxspinner.misc.Interface;
import scripts.flaxspinner.misc.Lumby;
import scripts.flaxspinner.positions.Areas;
import scripts.flaxspinner.positions.Tiles;
import scripts.flaxspinner.taskframework.Task;
import scripts.flaxspinner.utilities.Conditions;
import scripts.flaxspinner.utilities.Timer;
import scripts.flaxspinner.utilities.Vars;

public class Spin extends Task {

	public static Timer ANIMATION_TIMER;
	public static String status = "";
	public static RSArea bank;

	public Spin(RSArea bank) {
		Spin.bank = bank;
	}

	@Override
	public int priority() {
		return Vars.run_Priority;
	}

	@Override
	public boolean validate() {
		return Inventory.getCount("Flax") > 0;
	}

	@Override
	public void execute() {
		if (Vars.lumby) {
			Lumby.handleLumby();
		} else {
			this.handleCatherby();
		}
	}

	public static boolean needToResume(long idleTimeout, Timer t) {
		checkAnimationTimer(t);
		if (t.getElapsed() >= idleTimeout)
			return true;
		else
			return false;
	}

	public static void checkAnimationTimer(Timer timerToUpdate) {
		if (Player.getAnimation() != -1) {
			timerToUpdate.reset();
		}
	}

	public static RSTile myTile() {
		return Player.getPosition();
	}

	public static boolean lost() {
		if (!Vars.lumby) {
			return !bank.contains(myTile()) && !Areas.BUILDING_FIRST_FLOOR.getArea().contains(myTile())
					&& !Areas.BUILDING_GROUND_FLOOR.getArea().contains(myTile());
		} else {
			return Areas.LUMBY_FIRST_FLOOR.getArea().contains(myTile());
		}
	}

	public void handleCatherby() {
		if (bank.contains(myTile()) || lost()) {
			status = "Heading to building.";
			Walking.blindWalkTo(Tiles.CATHERBYWALKTODOORTILE.getTile());
		}
		if (Areas.BUILDING_GROUND_FLOOR.getArea().contains(myTile())) {
			Catherby.accendBuilding();
		}
		if (Areas.BUILDING_FIRST_FLOOR.getArea().contains(myTile())) {
			spin();
		}
	}

	public static boolean interactWithObject(RSObject[] object, String action) {
		if (object.length > 0) {
			if (object[0].isOnScreen()) {
				while (Player.isMoving()) {
					General.sleep(General.random(50, 80));
				}
				return DynamicClicking.clickRSObject(object[0], action);
			} else {
				Walking.blindWalkTo(object[0]);
			}
		}
		return false;
	}

	public static boolean makeXInterface() {
		Color c = Screen.getColourAt(259, 428);
		Color b = new Color(0, 0, 128);
		return Screen.coloursMatch(b, c, new Tolerance(10));
	}

	public static void spin() {
		status = "Spining flax.";
		if (needToResume(2000L, ANIMATION_TIMER)) {
			if (makeXInterface()) {
				Keyboard.typeSend(String.valueOf(Antiban.getRunPercentage() + 10));
				Timing.waitCondition(Conditions.animating(), General.random(4000, 7000));
				Antiban.generateRunPercentage();
			} else {
				if (Interface.SPIN_INTERFACE.open()) {
					if (Interface.SPIN_INTERFACE.getInterface().click("Make X")) {
						Timing.waitCondition(Conditions.makeXInterface(), General.random(2500, 4000));
					}
				} else {
					RSObject[] wheel = Objects.find(25, "Spinning wheel");
					if (interactWithObject(wheel, "Spin")) {
						Timing.waitCondition(Conditions.spinInterface(), General.random(4000, 7000));
					}
				}
			}
		} else {
			status = "Idling.";
			if (!Tiles.LUMBYSECONDFLOORLADDERTILE.getTile().isOnScreen()) {
				Camera.setCameraRotation(General.random(78, 82));
			}
			Antiban.doIdleActions();
		}
	}

	@Override
	public String status() {
		return status;
	}

}
