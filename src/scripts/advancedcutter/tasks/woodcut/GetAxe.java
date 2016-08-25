package scripts.advancedcutter.tasks.woodcut;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;

import scripts.advancedcutter.Main;
import scripts.advancedcutter.api.antiban.Antiban;
import scripts.advancedcutter.api.conditions.Conditions;
import scripts.advancedcutter.api.items.InteractBank;
import scripts.advancedcutter.api.taskframework.Task;
import scripts.advancedcutter.api.utilities.Vars;
import scripts.advancedcutter.general.ProgLevelSetup;

public class GetAxe extends Task {

	@Override
	public int priority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean validate() {
		return !ProgLevelSetup.gotAxe();
	}

	@Override
	public void execute() {
		Main.status = "Attempting to get " + ProgLevelSetup.axeName();
		if (Vars.bankArea.contains(Player.getPosition()))
			this.handleBank();
		else
			WebWalking.walkTo(Vars.bankArea.getRandomTile());
	}

	private boolean withdrawAxe() {
		return new InteractBank(true, ProgLevelSetup.axeName(), 0, 1).withdraw(Vars.start);
	}

	private void handleBank() {
		if (Banking.isBankScreenOpen()) {
			int sleep = Antiban.getReactionTime();
			if (this.withdrawAxe()) {
				Timing.waitCondition(Conditions.get().bankOpen(), General.random(4000, 6000));
				General.println("ABC2 Reaction time: " + sleep);
				Antiban.sleepReactionTime();
			}
			Antiban.generateTrackers(Antiban.getWaitingTime());
		} else {
			int sleep = Antiban.getReactionTime();
			if (Banking.openBank()) {
				Timing.waitCondition(Conditions.get().bankOpen(), General.random(4000, 6000));
				General.println("ABC2 Reaction time: " + sleep);
				Antiban.sleepReactionTime();
			}
		}
		Antiban.generateTrackers(Antiban.getWaitingTime());
	}

}
