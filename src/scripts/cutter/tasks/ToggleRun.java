package scripts.cutter.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Game;
import org.tribot.api2007.Options;

import scripts.cutter.antiban.Antiban;
import scripts.cutter.taskframework.Task;
import scripts.cutter.utilities.Conditions;
import scripts.cutter.utilities.Priorities;

public class ToggleRun extends Task {

	@Override
	public int priority() {
		return Priorities.RUN.getPriority();
	}

	@Override
	public boolean validate() {
		return Game.getRunEnergy() > Antiban.getRunPercentage() && !Game.isRunOn();
	}

	@Override
	public void execute() {
		Antiban.setWaitingSince();
		Antiban.get().performReactionTimeWait();
		if (Options.setRunOn(true)) {
			Timing.waitCondition(Conditions.runIsOn(), General.random(3500, 5000));
			Antiban.generateRunPercentage();
		}
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Handling toggle run on.";
	}

}
