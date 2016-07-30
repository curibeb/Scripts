package scripts.aiocooking.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSItem;

import scripts.aiocooking.enums.Objectives;
import scripts.aiocooking.antiban.AntiBan;
import scripts.aiocooking.enums.Food;
import scripts.aiocooking.taskframework.Task;
import scripts.aiocooking.utils.Conditions;
import scripts.aiocooking.utils.Vars;

public class Bank extends Task {

	@Override
	public int priority() {
		return Objectives.BANK.get_Priority();
	}

	@Override
	public boolean validate() {
		return Inventory.getCount(Food.SHRIMP.getName()) == 0;
	}

	@Override
	public void execute() {
		if (!Objectives.BANK.get_Area().contains(Player.getPosition())) {
			if (PathFinding.canReach(Objectives.BANK.get_Walk_Tile(), false)) {
				Vars.status = "Walking to bank.";
				Walking.blindWalkTo(Objectives.BANK.get_Walk_Tile());
			} else {
				Vars.status = "Handling obstacle.";
				Cook.interact_Object(Cook.obstacle(), "Open",
						Conditions.get().can_Reach_Tile(Objectives.BANK.get_Walk_Tile()));
			}
		} else {
			bank();
		}
	}

	private int getCurrentBankSpace() {
		RSInterface amount = Interfaces.get(12, 5);
		if (amount != null && !amount.isHidden()) {
			String text = amount.getText();
			if (text != null) {
				try {
					int parse = Integer.parseInt(text);
					if (parse > 0)
						return parse;
				} catch (NumberFormatException e) {
					return -1;
				}
			}
		}
		return -1;
	}

	public boolean isBankItemsLoaded() {
		return getCurrentBankSpace() == Banking.getAll().length;
	}

	private void bank() {
		if (!Objectives.BANK.interface_Open()) {
			Vars.status = "Opening bank.";
			if (Banking.openBank()) {
				General.sleep(AntiBan.getReactionTime());
				General.println("Generated reaction time: " + AntiBan.getReactionTime());
				Timing.waitCondition(Conditions.get().bank_open(), General.random(4000, 7000));
			}
		} else {
			General.sleep(500, 800);
			RSItem[] item = Banking.find(Food.SHRIMP.getName());
			if (isBankItemsLoaded()) {
				if (item.length == 0) {
					General.println("We ran out of " + Food.SHRIMP.getName() + " so we stopped.");
					Vars.start = false;
				} else {
					withdraw();
				}
			}
		}
	}

	private void withdraw() {
		if (Inventory.isFull() && Inventory.getCount(Food.SHRIMP.name()) == 0) {
			Vars.status = "Depositing items.";
			if (Banking.depositAll() > 0) {
				General.sleep(AntiBan.getReactionTime());
				General.println("Generated reaction time: " + AntiBan.getReactionTime());
				Timing.waitCondition(Conditions.get().inventory_Empty(), General.random(4000, 7000));
			}
		} else {
			Vars.status = "Withdrawing " + Vars.food;
			if (Banking.withdraw(0, Food.SHRIMP.getName())) {
				General.sleep(AntiBan.getReactionTime());
				General.println("Generated reaction time: " + AntiBan.getReactionTime());
				Timing.waitCondition(Conditions.get().got_Item(), General.random(4000, 7000));
			}
		}
	}

	@Override
	public String mission() {
		return "BANKING.";
	}

}
