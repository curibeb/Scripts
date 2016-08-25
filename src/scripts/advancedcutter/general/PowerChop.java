package scripts.advancedcutter.general;

import org.tribot.api.General;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Inventory.DROPPING_PATTERN;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;

import scripts.advancedcutter.Main;
import scripts.advancedcutter.api.antiban.Antiban;
import scripts.advancedcutter.api.utilities.Vars;

public class PowerChop {

	public static void execute(RSArea treeArea) {
		if (Inventory.isFull()) {
			drop();
		} else {
			if (treeArea.contains(Player.getPosition())) {
				Methods.chop(Vars.treeType, Vars.chopArea);
			} else {
				Main.status = "Walk to tree area.";
				WebWalking.walkTo(treeArea.getRandomTile());
			}
		}
	}

	private static void drop() {
		Main.status = "Dropping logs.";
		DROPPING_PATTERN[] patterns = DROPPING_PATTERN.values();
		if (patterns.length > 0) {
			Inventory.setDroppingPattern(patterns[General.random(0, patterns.length - 1)]);
		}
		int sleep = Antiban.getReactionTime();
		if (Inventory.dropAllExcept(new String[] { ProgLevelSetup.axeName() }) > 0) {
			General.println("ABC2 generated reaction time: " + sleep);
			Antiban.sleepReactionTime();
		}
		Antiban.generateTrackers(Antiban.getWaitingTime());
	}
}
