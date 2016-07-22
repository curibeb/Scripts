package scripts.cutter.tasks;

import java.awt.Point;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.CustomRet_0P;
import org.tribot.api2007.Camera;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.cutter.antiban.Antiban;
import scripts.cutter.taskframework.Task;
import scripts.cutter.utilities.Conditions;
import scripts.cutter.utilities.Priorities;
import scripts.cutter.utilities.Vars;

public class Chop extends Task {

	Positionable treeTile;

	@Override
	public int priority() {
		return Priorities.CHOP.getPriority();
	}

	@Override
	public boolean validate() {
		return !Inventory.isFull() && Equipment.isEquipped(Vars.axeId);
	}

	@Override
	public void execute() {
		if (Vars.treesLoc.contains(Player.getPosition())) {
			if (Player.getAnimation() == -1) {
				if (isTreeAtTile()) {
					if (hovered()) {
						click();
					} else {
						cutTree();
					}
				} else {
					grabTreeTile();
				}
			} else {
				if (!isTreeAtTile()) {
					if (grabTreeTile()) {
						if (hovered()) {
							click();
						} else {
							cutTree();
						}
					}
				} else {
					Antiban.doIdleActions();
				}
			}
		} else {
			WebWalking.walkTo(centreTile());
		}
	}

	public RSTile centreTile() {
		return Vars.treesLoc.polygon.npoints > 0 ? new RSTile((int) Math.round(avg(Vars.treesLoc.polygon.xpoints)),
				(int) Math.round(avg(Vars.treesLoc.polygon.ypoints))) : null;
	}

	private double avg(final int... nums) {
		long total = 0;
		for (int i : nums) {
			total += (long) i;
		}
		return (double) total / (double) nums.length;
	}

	public boolean hovered() {
		return Game.getUptext() != null && Game.getUptext().contains("Chop");
	}

	public void hover() {
		RSObject[] tree = Objects.findNearest(8, Vars.tree);
		if (tree.length > 1) {
			if (tree[1].isOnScreen()) {
				Antiban.setWaitingSince();
				Antiban.get().performReactionTimeWait();
				if (tree[1].hover()) {
					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							return hovered();
						}
					}, General.random(4000, 7000));
				}

			} else {
				Camera.turnToTile(tree[0]);
			}
		}
	}

	public boolean isTreeAtTile() {
		return Objects.isAt(treeTile, Vars.tree);
	}

	public boolean grabTreeTile() {
		RSObject[] tree = Objects.findNearest(100, Vars.tree);
		if (tree != null && tree.length > 0) {
			if (Vars.treesLoc.contains(tree[0].getPosition())) {
				treeTile = Antiban.determineNextTarget(tree);
			}
		}
		return true;
	}

	public RSObject tree() {
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

	public void cutTree() {
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
				if (Player.getPosition().distanceTo(tree.getPosition()) > 5) {
					Walking.blindWalkTo(tree.getPosition());
				} else {
					if (Antiban.shouldMoveAnticipated()) {
						if (Player.getPosition().distanceTo(tree.getPosition()) < 3) {
							Walking.generateStraightScreenPath(tree.getPosition());
						} else {
							Camera.turnToTile(tree.getPosition());
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
