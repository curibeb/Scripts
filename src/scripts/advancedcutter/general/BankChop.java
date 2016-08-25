package scripts.advancedcutter.general;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;

import scripts.advancedcutter.Main;
import scripts.advancedcutter.api.antiban.Antiban;
import scripts.advancedcutter.api.conditions.Conditions;
import scripts.advancedcutter.api.utilities.Vars;
import scripts.advancedcutter.enums.treetypes.TreeTypes;

public class BankChop {

	public static void execute(RSArea bankArea, RSArea treeArea) {
		if (Inventory.isFull()) {
			if (bankArea.contains(Player.getPosition())) {
				handleBank();
			} else {
				Main.status = "Walk to bank.";
				WebWalking.walkTo(bankArea.getRandomTile());
			}
		} else {
			if (treeArea.contains(Player.getPosition())) {
				Methods.chop(Vars.treeType, Vars.chopArea);
			} else {
				Main.status = "Walk to tree area.";
				WebWalking.walkTo(treeArea.getRandomTile());
			}
		}
	}

	private static String logName() {
		for (TreeTypes tree : TreeTypes.values()) {
			if (tree.getName().equals(Vars.treeType)) {
				return tree.getLogs();
			}
		}
		return "";
	}

	private static void handleBank() {
		Main.status = "Banking.";
		if (Banking.isBankScreenOpen()) {
			if (Banking.depositAllExcept(ProgLevelSetup.axeName()) > 0) {
				Timing.waitCondition(Conditions.get().dontHaveItem(logName()), General.random(4000, 6000));
			}
		} else {
			int sleep = Antiban.getReactionTime();
			if (Banking.openBank()) {
				General.println("ABC2 Reaction Time: " + sleep);
				Antiban.sleepReactionTime();
				Timing.waitCondition(Conditions.get().bankOpen(), General.random(4000, 6000));
			}
			Antiban.generateTrackers(Antiban.getWaitingTime());
		}
	}

}
