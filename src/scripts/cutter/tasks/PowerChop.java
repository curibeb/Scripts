package scripts.cutter.tasks;

import java.awt.Point;
import java.awt.Rectangle;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSItem;

import scripts.cutter.api.antiban.Antiban;
import scripts.cutter.taskframework.Task;
import scripts.cutter.utilities.Priorities;
import scripts.cutter.utilities.Vars;

public class PowerChop extends Task {

	boolean full = false;

	@Override
	public int priority() {
		return Priorities.POWERCHOP.getPriority();
	}

	@Override
	public boolean validate() {
		return Equipment.isEquipped(Vars.axe_Id);
	}

	@Override
	public void execute() {
		long t = System.currentTimeMillis();

		while (Player.getAnimation() != -1 && (System.currentTimeMillis() - t) < 5000) {
			General.random(500, 800);
			Antiban.timedActions();
		}
		if (Vars.trees_Loc.contains(Player.getPosition())) {
			if (full) {
				dropAll();
				if (Inventory.getAll().length == 0) {
					full = false;
				}
			} else {
				if (Inventory.getAll().length == 28) {
					full = true;
				} else {
					if (Player.getAnimation() != -1 && !Chop.isTreeAtTile()) {
						if (Chop.grabTreeTile()) {
							Chop.cutTree();
						}
					}
					if (Player.getAnimation() == -1) {
						if (Chop.isTreeAtTile()) {
							Chop.cutTree();
						} else {
							Chop.grabTreeTile();
						}
					}
				}
			}
		} else {
			WebWalking.walkTo(Vars.trees_Tile);
		}
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
						Mouse.move(new Point((int) r.getCenterX() + General.random(-10, 10),
								(int) r.getCenterY() + General.random(-10, 10)));
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
		if (ChooseOption.isOpen()) {
			final String[] actions = ChooseOption.getOptions();
			if (actions.length > 0) {
				for (int i = 0; i < actions.length; i++) {
					if (actions[i].toLowerCase().contains("drop"))
						return (int) (ChooseOption.getPosition().getY() + 21 + 16 * i);
				}
			}
		}
		return -1;
	}

	@Override
	public String status() {
		return "Handling powerchop.";
	}
}
