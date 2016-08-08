package scripts.cutter.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;

import scripts.cutter.api.antiban.Antiban;
import scripts.cutter.api.items.InteractBank;
import scripts.cutter.api.items.InteractInv;
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
		return !Equipment.isEquipped(Vars.axe_Id);
	}

	@Override
	public void execute() {
		if (Vars.bank.contains(Player.getPosition())) {
			if (Inventory.getCount(Vars.axe_Id) == 0) {
				grabAxe();
			} else {
				if (Banking.isBankScreenOpen()) {
					if (Banking.close())
						Timing.waitCondition(Conditions.bankClosed, General.random(2500, 3500));
				} else {
					Antiban.getReactionTime();
					Antiban.sleepReactionTime();
					if (wieldAxe())
						Antiban.generateTrackers(Antiban.getWaitingTime());
				}
			}
		} else {
			Walking.blindWalkTo(Chop.centreTile());
		}
	}

	private void grabAxe() {
		if (Banking.isBankScreenOpen()) {
			Antiban.getReactionTime();
			Antiban.sleepReactionTime();
			if (this.withdrawAxe())
				Antiban.generateTrackers(Antiban.getWaitingTime());
		} else {
			Antiban.getReactionTime();
			Antiban.sleepReactionTime();
			if (Banking.openBank()) {
				Timing.waitCondition(Conditions.bankOpen, General.random(3500, 5000));
				Antiban.generateTrackers(Antiban.getWaitingTime());
			}
		}
	}

	private boolean withdrawAxe() {
		return new InteractBank(false, null, Vars.axe_Id, 1).withdraw(Vars.start);
	}

	private boolean wieldAxe() {
		return new InteractInv(false, null, Vars.axe_Id, "Wield").click();
	}

	@Override
	public String status() {
		return "Handle gearing";
	}

}
