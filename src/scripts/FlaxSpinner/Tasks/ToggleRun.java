package scripts.FlaxSpinner.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Game;
import org.tribot.api2007.Options;

import scripts.FlaxSpinner.AntiBan.Antiban;
import scripts.FlaxSpinner.TaskFramwork.Task;
import scripts.FlaxSpinner.Utilities.Vars;

public class ToggleRun extends Task {

	@Override
	public int priority() {
		return Vars.runPriority;
	}

	@Override
	public boolean validate() {
		return Game.getRunEnergy() > Antiban.getRunPercentage() && !Game.isRunOn();
	}

	@Override
	public void execute() {
		Antiban.setWaitingSince();
		if (Options.setRunOn(true)) {
			Antiban.performReactionTimeWait();
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					return Game.isRunOn();
				}

			}, General.random(3500, 5000));
			Antiban.generateRunPercentage();
		}
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Handling toggle run on.";
	}

}
