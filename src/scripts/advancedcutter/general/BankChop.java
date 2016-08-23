package scripts.advancedcutter.general;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;

public class BankChop {

	public static void execute(RSArea bankArea, RSArea treeArea) {
		if (Inventory.isFull()) {
			if (bankArea.contains(Player.getPosition()))
				handleBank();
			else
				WebWalking.walkTo(bankArea.getRandomTile());
		} else {
			if (treeArea.contains(Player.getPosition()))
				handleChop();
			else
				WebWalking.walkTo(treeArea.getRandomTile());
		}
	}

	private static void handleChop() {

	}

	private static void handleBank() {

	}

}
