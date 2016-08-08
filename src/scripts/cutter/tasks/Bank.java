package scripts.cutter.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;

import scripts.cutter.api.antiban.Antiban;
import scripts.cutter.taskframework.Task;
import scripts.cutter.utilities.Conditions;
import scripts.cutter.utilities.Priorities;
import scripts.cutter.utilities.Vars;

public class Bank extends Task {

	@Override
	public int priority() {
		return Priorities.BANK.getPriority();
	}

	@Override
	public boolean validate() {
		return Inventory.isFull();
	}

	@Override
	public void execute() {
		if (Vars.bank.contains(Player.getPosition())) {
			Antiban.getReactionTime();
			Antiban.sleepReactionTime();
			if (Banking.isBankScreenOpen()) {
				if (Banking.depositAll() > 0) {
					Timing.waitCondition(Conditions.bankedLogs, General.random(2500, 4500));
					Antiban.generateTrackers(Antiban.getWaitingTime());
				}
			} else {
				Antiban.getReactionTime();
				Antiban.sleepReactionTime();
				if (Banking.openBank()) {
					Timing.waitCondition(Conditions.bankOpen, General.random(2500, 4500));
					Antiban.generateTrackers(Antiban.getWaitingTime());
				}
			}
		} else {
			Walking.blindWalkTo(Chop.centreTile());
		}
	}

	@Override
	public String status() {
		return "Handle banking.";
	}

}
