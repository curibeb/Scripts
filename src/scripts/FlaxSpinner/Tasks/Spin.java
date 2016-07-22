package scripts.FlaxSpinner.Tasks;

import java.awt.Color;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Screen;
import org.tribot.api.Timing;
import org.tribot.api.input.Keyboard;
import org.tribot.api.types.colour.Tolerance;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.FlaxSpinner.AntiBan.Antiban;
import scripts.FlaxSpinner.Misc.Catherby;
import scripts.FlaxSpinner.Misc.Interface;
import scripts.FlaxSpinner.Misc.Lumby;
import scripts.FlaxSpinner.Positions.Areas;
import scripts.FlaxSpinner.Positions.Tiles;
import scripts.FlaxSpinner.TaskFramwork.Task;
import scripts.FlaxSpinner.Utilities.Timer;
import scripts.FlaxSpinner.Utilities.Vars;

public class Spin extends Task {

	public static Timer ANIMATION_TIMER;
	public static String status = "";
	public static RSArea bank;

	public Spin(RSArea bank) {
		Spin.bank = bank;
	}

	@Override
	public int priority() {
		return Vars.runPriority;
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
			return !bank.contains(myTile()) && !Areas.BUILDINGFIRSTFLOOR.getArea().contains(myTile())
					&& !Areas.BUILDINGGROUNDFLOOR.getArea().contains(myTile());
		} else {
			return Areas.LUMBYFIRSTFLOOR.getArea().contains(myTile());
		}
	}

	public void handleCatherby() {
		if (bank.contains(myTile()) || lost()) {
			status = "Heading to building.";
			Walking.blindWalkTo(Tiles.CATHERBYWALKTODOORTILE.getTile());
		}
		if (Areas.BUILDINGGROUNDFLOOR.getArea().contains(myTile())) {
			Catherby.accendBuilding();
		}
		if (Areas.BUILDINGFIRSTFLOOR.getArea().contains(myTile())) {
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
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return Player.getAnimation() != -1;
					}

				}, General.random(4000, 7000));
				Antiban.generateRunPercentage();
			} else {
				if (Interface.SPININTERFACE.open()) {
					if (Interface.SPININTERFACE.getInterface().click("Make X")) {
						Timing.waitCondition(makeXInterfaceCondtion(), General.random(2500, 4000));
					}
				} else {
					RSObject[] wheel = Objects.find(25, "Spinning wheel");
					if (interactWithObject(wheel, "Spin")) {
						Timing.waitCondition(spinInterfaceCondtion(), General.random(4000, 7000));
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

	public static Condition spinInterfaceCondtion() {
		return new Condition() {
			@Override
			public boolean active() {
				return Interface.SPININTERFACE.open();
			}
		};
	}

	public static Condition makeXInterfaceCondtion() {
		return new Condition() {
			@Override
			public boolean active() {
				return makeXInterface();
			}
		};
	}

	@Override
	public String status() {
		return status;
	}

}
