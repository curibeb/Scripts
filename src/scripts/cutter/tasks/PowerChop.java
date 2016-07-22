package scripts.cutter.tasks;

import java.awt.Point;
import java.awt.Rectangle;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Camera;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.cutter.antiban.Antiban;
import scripts.cutter.taskframework.Task;
import scripts.cutter.utilities.Conditions;
import scripts.cutter.utilities.Priorities;
import scripts.cutter.utilities.Vars;

public class PowerChop extends Task {

	boolean full = false;
	Positionable treeTile;

	@Override
	public int priority() {
		return Priorities.POWERCHOP.getPriority();
	}

	@Override
	public boolean validate() {
		return Equipment.isEquipped(Vars.axeId);
	}

	@Override
	public void execute() {
		if (Vars.treesLoc.contains(Player.getPosition())) {
			if (full) {
				dropAll();
				if (Inventory.getAll().length == 0) {
					full = false;
				}
			} else {
				if (Inventory.getAll().length == 28) {
					full = true;
				} else {
					if (Player.getAnimation() != -1 && !isTreeAtTile()) {
						if (grabTreeTile()) {
							cutTree();
						}
					}
					if (Player.getAnimation() == -1) {
						if (isTreeAtTile()) {
							cutTree();
						} else {
							grabTreeTile();
						}
					}
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

	public static void dropAll() {
		if (GameTab.getOpen() != GameTab.TABS.INVENTORY) {
			GameTab.open(GameTab.TABS.INVENTORY);
		}
		RSItem[] items = to28(Inventory.getAll());
		if (items.length > 0) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 7; j++) {
					if (items[4 * j + i] == null)
						continue;
					Rectangle r = new RSItem(4 * j + i, 0, 0, RSItem.TYPE.INVENTORY).getArea();
					if (!r.contains(Mouse.getPos())) {
						Mouse.move(new Point((int) r.getCenterX() + General.random(-3, 3),
								(int) r.getCenterY() + General.random(-3, 3)));
					}
					if (r.contains(Mouse.getPos())) {
						Mouse.click(3);
						final int yy = getY();
						if (yy == -1) {
							Mouse.click(1);
						} else {
							Mouse.hop(new Point((int) Mouse.getPos().getX(), yy));
							Mouse.click(1);
						}
					}
				}
			}
		}
	}

	private static RSItem[] to28(RSItem[] in) {
		RSItem[] items = new RSItem[28];
		if (items.length > 0) {
			for (int i = 0; i < in.length; i++)
				items[in[i].getIndex()] = in[i];
		}
		return items;
	}

	private static int getY() {
		if (ChooseOption.isOpen() && ChooseOption.getOptions().length > 0) {
			final String[] actions = ChooseOption.getOptions();
			for (int i = 0; i < actions.length; i++) {
				if (actions[i].toLowerCase().contains("drop")) {
					return (int) (ChooseOption.getPosition().getY() + 21 + 16 * i);
				}
			}
		}
		return -1;
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

	public void cutTree() {
		RSObject tree = tree();
		if (tree != null) {
			if (tree.isOnScreen()) {
				if (tree.click("Chop down")) {
					Timing.waitCondition(Conditions.animating, General.random(2500, 4500));
				}
			} else {
				if (Player.getPosition().distanceTo(tree.getPosition()) > 5) {
					Walking.blindWalkTo(tree.getPosition());
				} else {
					if (Antiban.shouldMoveAnticipated()) {
						if (Player.getPosition().distanceTo(tree.getPosition()) > 3) {
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
		return "Handling powerchop.";
	}
}
