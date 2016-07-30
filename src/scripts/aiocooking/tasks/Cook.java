package scripts.aiocooking.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Game;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;

import scripts.aiocooking.antiban.AntiBan;
import scripts.aiocooking.enums.Objectives;
import scripts.aiocooking.taskframework.Task;
import scripts.aiocooking.utils.Conditions;
import scripts.aiocooking.utils.Vars;

public class Cook extends Task {

	@Override
	public int priority() {
		return Objectives.COOK.get_Priority();
	}

	@Override
	public boolean validate() {
		return Inventory.getCount(Vars.food) > 0;
	}

	@Override
	public void execute() {
		if (!Objectives.COOK.get_Area().contains(Player.getPosition())) {
			Vars.status = "Walking to cooking area.";
			Walking.blindWalkTo(Objectives.COOK.get_Walk_Tile());
		} else {
			while (Player.getAnimation() != -1) {
				General.sleep(500, 800);
				Vars.status = "ABC2 Perform timed actions.";
				AntiBan.timedActions();
			}
			if (range() != null) {
				Vars.range_Tile = range().getPosition();
				if (PathFinding.canReach(Objectives.COOK.get_Walk_Tile(), false)) {
					if (Player.getAnimation() == -1)
						cook();
				} else {
					Vars.status = "Handling obstacle.";
					interact_Object(obstacle(), "Open",
							Conditions.get().can_Reach_Tile(Objectives.COOK.get_Walk_Tile()));
				}
			}
		}
	}

	private void cook() {
		if (Objectives.COOK.interface_Open()) {
			cook_All();
		} else {
			if (Game.getUptext() != null) {
				if (range() != null) {
					if (!range().isOnScreen()) {
						Walking.walkTo(range());
					} else {
						if (Game.getUptext().contains(">")) {
							Vars.status = "Interacting with range.";
							interact_Object(range(), "Use", Conditions.get().cook_Interface_Open());
						} else {
							if (!Objectives.COOK.interface_Open())
								use_Item();
						}
					}
				}
			}
		}
	}

	private void cook_All() {
		Vars.status = "Clicking cook all interface.";
		RSInterface cook_Interface = Interfaces.get(307, 6);
		if (cook_Interface != null && !cook_Interface.isHidden()) {
			if (cook_Interface.click("Cook All")) {
				General.sleep(AntiBan.getReactionTime());
				General.println("Generated reaction time: " + AntiBan.getReactionTime());
				Timing.waitCondition(Conditions.get().animating(), General.random(4000, 7000));
			}
		}
	}

	private void use_Item() {
		Vars.status = "Clicking use on " + Vars.food;
		RSItem[] item = Inventory.find(Vars.food);
		if (item.length > 0) {
			if (item[0].click("Use")) {
				General.sleep(AntiBan.getReactionTime());
				General.println("Generated reaction time: " + AntiBan.getReactionTime());
				Timing.waitCondition(Conditions.get().item_Clicked(), General.random(4000, 5000));
			}
		}
	}

	public static void interact_Object(RSObject object, String action, Condition condition) {
		if (object != null) {
			if (object.isOnScreen()) {
				if (object.click(action)) {
					General.sleep(AntiBan.getReactionTime());
					General.println("Generated reaction time: " + AntiBan.getReactionTime());
					Timing.waitCondition(condition, General.random(4000, 7000));
				}
			} else {
				Walking.walkTo(object);
			}
		}
	}

	private static RSObject[] get_Possible_Obstacles() {
		return Objects.findNearest(Objectives.COOK.get_Area().getAllTiles().length, new Filter<RSObject>() {
			@Override
			public boolean accept(RSObject object) {
				if (object == null) {
					return false;
				}
				if (object.getDefinition() == null) {
					return false;
				}
				if (object.getDefinition().getActions() == null) {
					return false;
				}
				String[] actions = object.getDefinition().getActions();
				if (actions.length == 0) {
					return false;
				}
				for (String a : actions) {
					return a.equals("Open") && Objectives.COOK.get_Area().contains(object);
				}
				return false;
			}
		});
	}

	public static RSObject obstacle() {
		RSObject[] obstacles = get_Possible_Obstacles();
		if (obstacles.length > 0) {
			return obstacles[0];
		}
		return null;
	}

	private static RSObject[] get_Possible_Ranges() {
		return Objects.findNearest(Objectives.COOK.get_Area().getAllTiles().length, new Filter<RSObject>() {
			@Override
			public boolean accept(RSObject object) {
				if (object == null) {
					return false;
				}
				if (object.getDefinition() == null) {
					return false;
				}
				return object.getDefinition().getName().equals("Range") && Objectives.COOK.get_Area().contains(object);
			}
		});
	}

	public static RSObject range() {
		RSObject[] ranges = get_Possible_Ranges();
		if (ranges.length > 0) {
			return get_Possible_Ranges()[0];
		}
		return null;
	}

	@Override
	public String mission() {
		return "COOKING.";
	}

}
