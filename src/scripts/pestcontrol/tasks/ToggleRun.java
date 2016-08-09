package scripts.pestcontrol.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Game;

import scripts.pestcontrol.api.antiban.Antiban;
import scripts.pestcontrol.api.conditions.Conditions;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;

public class ToggleRun extends Task {

	@Override
	public int priority() {
		return Priorities.RUN_AT.getPriority();
	}

	@Override
	public boolean validate() {
		return Game.getRunEnergy() > Antiban.run_at && !Game.isRunOn();
	}

	@Override
	public void execute() {
		Antiban.getReactionTime();
		Antiban.sleepReactionTime();
		if (Antiban.activateRun()) {
			Antiban.generateTrackers(Antiban.getWaitingTime());
			Timing.waitCondition(Conditions.get().runIsOn(), General.random(3500, 5000));
		}
	}

}
