package scripts.cutter.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSTile;

import scripts.cutter.antiban.Antiban;
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
			if (Banking.isBankScreenOpen()) {
				if (Banking.depositAll() > 0) {
					Timing.waitCondition(Conditions.bankedLogs, General.random(2500, 4500));
				}
			} else {
				Antiban.setWaitingSince();
				Antiban.get().performReactionTimeWait();
				if (Banking.openBank()) {
					Timing.waitCondition(Conditions.bankOpen, General.random(2500, 4500));
				}
			}
		} else {
			Walking.blindWalkTo(centreTile());
		}
	}

	@Override
	public String status() {
		return "Handle banking.";
	}

	public RSTile centreTile() {
		return Vars.bank.polygon.npoints > 0 ? new RSTile((int) Math.round(avg(Vars.bank.polygon.xpoints)),
				(int) Math.round(avg(Vars.bank.polygon.ypoints))) : null;
	}

	private double avg(final int... nums) {
		long total = 0;
		for (int i : nums) {
			total += (long) i;
		}
		return (double) total / (double) nums.length;
	}

}
