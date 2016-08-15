package scripts.advancedcutter.general;

import java.awt.Point;
import java.awt.Rectangle;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Camera;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Game;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;

import scripts.advancedcutter.api.antiban.Antiban;
import scripts.advancedcutter.api.conditions.Conditions;
import scripts.advancedcutter.api.entities.InteractObject;

public class Methods {

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

	private static void hover(String tree) {
		if (Antiban.should_open_menu) {
			if (hovered(tree)) {
				Mouse.click(3);
			}
		} else {
			if (!hovered(tree)) {
				RSObject[] trees = Objects.findNearest(6, tree);
				if (trees.length > 1) {
					if (!trees[1].isOnScreen())
						Camera.turnToTile(trees[1]);
					else
						trees[1].hover();
				}
			}
		}
	}

	private static boolean hovered(String tree) {
		String uptext = Game.getUptext();
		if (uptext == null)
			return false;
		return !ChooseOption.isOpen() && uptext.contains(tree);
	}

	private static void selectOption() {
		if (ChooseOption.select("Chop down")) {
			Timing.waitCondition(Conditions.get().animating(), General.random(3000, 6000));
			Antiban.sleepReactionTime();
		}
		Antiban.generateTrackers(Antiban.getWaitingTime());
	}

	public static void chop(String tree, RSArea area) {
		if (Player.getAnimation() != -1) {
			if (Antiban.should_hover)
				hover(tree);
			else
				Antiban.timedActions();
		} else {
			int sleep = Antiban.getReactionTime();
			if (hovered(tree)) {
				Mouse.click(1);
				Timing.waitCondition(Conditions.get().animating(), General.random(3000, 6000));
				Antiban.sleepReactionTime();
			} else {
				if (ChooseOption.isOpen()) {
					selectOption();
				} else {
					if (cut(tree, area)) {
						Timing.waitCondition(Conditions.get().animating(), General.random(3000, 6000));
						Antiban.sleepReactionTime();
						General.println("ABC2 Reaction time generated: " + sleep);
					}
				}
			}
			Antiban.generateTrackers(Antiban.getWaitingTime());
		}
	}

	private static boolean cut(String tree, RSArea treeArea) {
		return new InteractObject(false, null, treeArea, 30, tree, "Chop down").click();
	}
}
