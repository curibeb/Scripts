package scripts.advancedcutter.general;


import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Camera;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Game;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;

import scripts.advancedcutter.Main;
import scripts.advancedcutter.api.antiban.Antiban;
import scripts.advancedcutter.api.conditions.Conditions;
import scripts.advancedcutter.api.entities.InteractObject;

public class Methods {

	private static void handleHover(String tree) {
		if (Antiban.should_open_menu) {
			Main.status = "Right clicking next tree";
			if (hovered(tree)) {
				Mouse.click(3);
			} else {
				hover(tree);
			}
		} else {
			if (!hovered(tree)) {
				Main.status = "Hover over next tree";
				Antiban.getABCUtil().rotateCamera();
				hover(tree);
				General.sleep(500, 800);
			}
		}
	}

	public static void hover(String tree) {
		RSObject[] trees = Objects.findNearest(6, tree);
		if (trees.length > 1) {
			for (RSObject t : trees) {
				if (t.getPosition().distanceTo(Player.getPosition()) > 2) {
					if (!t.isOnScreen())
						Camera.turnToTile(trees[1]);
					else
						t.hover();
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

	private static boolean selectOption() {
		return ChooseOption.select("Chop down");
	}

	public static void chop(String tree, RSArea area) {
		if (Player.getAnimation() != -1) {
			if (Antiban.should_hover) {
				handleHover(tree);
			} else {
				Main.status = "ABC2 perform idle actions";
				Antiban.timedActions();
			}
			Antiban.generateTrackers(Antiban.getWaitingTime());
		} else {
			Main.status = "Chopping " + tree;
			int sleep = Antiban.getReactionTime();
			if (hovered(tree)) {
				Mouse.click(1);
				Antiban.getABCUtil().moveMouse();
				Timing.waitCondition(Conditions.get().animating(), General.random(3000, 6000));
				Antiban.sleepReactionTime();
			} else {
				if (ChooseOption.isOpen()) {
					if (selectOption()) {
						Antiban.getABCUtil().moveMouse();
						Timing.waitCondition(Conditions.get().animating(), General.random(3000, 6000));
						Antiban.sleepReactionTime();
					}
					Antiban.generateTrackers(Antiban.getWaitingTime());
				} else {
					if (cut(tree, area)) {
						Antiban.getABCUtil().moveMouse();
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
