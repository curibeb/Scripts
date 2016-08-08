package scripts.cutter.tasks;

import org.tribot.api2007.Game;

import scripts.cutter.api.antiban.Antiban;
import scripts.cutter.taskframework.Task;
import scripts.cutter.utilities.Priorities;

public class ToggleRun extends Task {

	@Override
	public int priority() {
		return Priorities.RUN.getPriority();
	}

	@Override
	public boolean validate() {
		return Game.getRunEnergy() > Antiban.run_at && !Game.isRunOn();
	}

	@Override
	public void execute() {
		Antiban.getReactionTime();
		Antiban.sleepReactionTime();
		if (Antiban.activateRun())
			Antiban.generateTrackers(Antiban.getWaitingTime());
	}

	@Override
	public String status() {
		return "Handling toggle run on.";
	}

}
