package scripts.cutter.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSItem;

import scripts.cutter.antiban.Antiban;
import scripts.cutter.taskframework.Task;
import scripts.cutter.utilities.Conditions;
import scripts.cutter.utilities.Priorities;
import scripts.cutter.utilities.Vars;

public class GearUp extends Task {

	@Override
	public int priority() {
		return Priorities.GEAR.getPriority();
	}

	@Override
	public boolean validate() {
		return !Equipment.isEquipped(Vars.axeId);
	}

	private static int getCurrentBankSpace() {
		RSInterface amount = Interfaces.get(12, 5);
		if (amount != null) {
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

	@Override
	public void execute() {
		if (Vars.bank.contains(Player.getPosition())) {
			if (Inventory.getCount(Vars.axeId) == 0) {
				if (Banking.isBankScreenOpen()) {
					if (getCurrentBankSpace() > 0) {
						if (Banking.find(Vars.axeId) != null) {
							if (Banking.withdraw(1, Vars.axeId)) {
								Timing.waitCondition(Conditions.gotAxe, General.random(3500, 5000));
							}
						} else {
							General.println("Couldnt find axe so we stopped :(");
							Vars.start = false;
						}
					}
				} else {
					if (Banking.openBank()) {
						Timing.waitCondition(Conditions.bankOpen, General.random(3500, 5000));
					}
				}
			} else {
				if (Banking.isBankScreenOpen()) {
					if (Banking.close()) {
						Timing.waitCondition(Conditions.bankClosed, General.random(2500, 3500));
					}
				} else {
					RSItem[] axe = Inventory.find(Vars.axeId);
					if (axe.length > 0) {
						Antiban.setWaitingSince();
						Antiban.get().performReactionTimeWait();
						if (axe[0].click("Wield")) {
							Timing.waitCondition(Conditions.equipmentOn(), General.random(2500, 3000));
						}
					}
				}
			}
		} else {
			Walking.blindWalkTo(Chop.centreTile());
		}
	}

	@Override
	public String status() {
		return "Handle gearing";
	}

}
