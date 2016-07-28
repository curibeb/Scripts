package scripts.cutter.tasks;

import java.awt.Point;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api.types.generic.CustomRet_0P;
import org.tribot.api2007.Camera;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.cutter.antiban.Antiban;
import scripts.cutter.taskframework.Task;
import scripts.cutter.utilities.Conditions;
import scripts.cutter.utilities.Priorities;
import scripts.cutter.utilities.Vars;

public class Chop extends Task {

	static Positionable treeTile;

	@Override
	public int priority() {
		return Priorities.CHOP.getPriority();
	}

	@Override
	public boolean validate() {
		return !Inventory.isFull() && Equipment.isEquipped(Vars.axe_Id);
	}

	@Override
	public void execute() {
		while (Player.getAnimation() != -1) {
			General.random(500, 800);
			Antiban.doIdleActions();
		}
		if (Vars.trees_Loc.contains(Player.getPosition())) {
			if (Player.getAnimation() == -1)
				this.chopWhenIdle();
			else
				this.chopWhenHovered();
		} else {
			Walking.blindWalkTo(Vars.trees_Tile);
		}
	}

	public void chopWhenIdle() {
		if (isTreeAtTile()) {
			if (hovered()) {
				click();
			} else {
				cutTree();
			}
		} else {
			grabTreeTile();
		}
	}

	public void chopWhenHovered() {
		if (!isTreeAtTile()) {
			if (grabTreeTile()) {
				if (hovered()) {
					click();
				} else {
					cutTree();
				}
			}
		}
	}

	public static RSTile centreTile() {
		return Vars.trees_Loc.polygon.npoints > 0 ? new RSTile((int) Math.round(avg(Vars.trees_Loc.polygon.xpoints)),
				(int) Math.round(avg(Vars.trees_Loc.polygon.ypoints))) : null;
	}

	private static double avg(final int... nums) {
		long total = 0;
		for (int i : nums) {
			total += (long) i;
		}
		return (double) total / (double) nums.length;
	}

	public static boolean hovered() {
		return Game.getUptext() != null && Game.getUptext().contains("Chop");
	}

	public static void hover() {
		RSObject[] tree = Objects.findNearest(8, Vars.tree);
		if (tree.length > 1) {
			if (tree[1].isOnScreen()) {
				Antiban.setWaitingSince();
				Antiban.get().performReactionTimeWait();
				if (tree[1].hover()) {
					Timing.waitCondition(Conditions.hovered(), General.random(4000, 7000));
				}
			} else {
				Camera.turnToTile(tree[0]);
			}
		}
	}

	public static boolean isTreeAtTile() {
		return Objects.isAt(treeTile, Vars.tree);
	}

	public static boolean grabTreeTile() {
		RSObject[] tree = Objects.findNearest(Vars.trees_Loc.getAllTiles().length, Vars.tree);
		if (tree != null && tree.length > 0) {
			if (Vars.trees_Loc.contains(tree[0].getPosition())) {
				treeTile = Antiban.determineNextTarget(tree);
			}
		}
		return true;
	}

	public static RSObject tree() {
		RSObject[] tree = Objects.getAt(treeTile);
		if (tree.length > 0) {
			return tree[0];
		}
		return null;
	}

	public void click() {
		if (Antiban.shouldOpenMenuNext()) {
			if (ChooseOption.getOptions() != null && ChooseOption.isOpen()) {
				Antiban.setWaitingSince();
				Antiban.get().performReactionTimeWait();
				if (ChooseOption.select("Chop down")) {
					Timing.waitCondition(Conditions.animating, General.random(4000, 7000));
					if (Antiban.shouldHoverNext()) {
						hover();
					}
				}
			} else {
				Mouse.click(3);
			}
		} else {
			Antiban.setWaitingSince();
			Antiban.get().performReactionTimeWait();
			if (DynamicClicking.clickPoint(new CustomRet_0P<Point>() {
				@Override
				public Point ret() {
					return new Point(Mouse.getPos().x, Mouse.getPos().y);
				}
			}, 1)) {
				Timing.waitCondition(Conditions.animating, General.random(4000, 7000));
				if (Antiban.shouldHoverNext()) {
					hover();
				}
			}
		}
	}

	public static void cutTree() {
		RSObject tree = tree();
		if (tree != null) {
			if (tree.isOnScreen()) {
				Antiban.setWaitingSince();
				Antiban.get().performReactionTimeWait();
				if (tree.click("Chop down")) {
					Timing.waitCondition(Conditions.animating, General.random(2500, 4500));
					if (Antiban.shouldHoverNext()) {
						hover();
					}
				}
			} else {
				if (Player.getPosition().distanceTo(tree.getPosition()) > 8) {
					Walking.blindWalkTo(tree.getPosition());
				} else {
					if (Antiban.shouldMoveAnticipated()) {
						if (Player.getPosition().distanceTo(tree.getPosition()) < 5) {
							Camera.setCameraAngle(tree.getModel().getClickHeight());
							Camera.turnToTile(tree.getPosition());
						} else {
							Walking.walkTo(tree);
						}
					}
				}
			}
		}
	}

	@Override
	public String status() {
		return "Handle chopping.";
	}

}
