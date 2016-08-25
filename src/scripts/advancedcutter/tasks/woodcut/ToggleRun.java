package scripts.advancedcutter.tasks.woodcut;

import org.tribot.api2007.Game;

import scripts.advancedcutter.Main;
import scripts.advancedcutter.api.antiban.Antiban;
import scripts.advancedcutter.api.taskframework.Task;

public class ToggleRun extends Task {

	@Override
	public int priority() {
		return 1;
	}

	@Override
	public boolean validate() {
		return Game.getRunEnergy() > Antiban.run_at && !Game.isRunOn();
	}

	@Override
	public void execute() {
		Main.status = "Turn run on.";
		Antiban.getReactionTime();
		Antiban.sleepReactionTime();
		if (Antiban.activateRun())
			Antiban.generateTrackers(Antiban.getWaitingTime());
	}

}
