package scripts.aiocooking.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Game;
import org.tribot.api2007.Options;

import scripts.aiocooking.antiban.AntiBan;
import scripts.aiocooking.taskframework.Task;
import scripts.aiocooking.utils.Conditions;

public class ToggleRun extends Task {

	@Override
	public int priority() {
		return 3;
	}

	@Override
	public boolean validate() {
		return Game.getRunEnergy() > AntiBan.run_at && !Game.isRunOn();
	}

	@Override
	public void execute() {

		if (Options.setRunOn(true)) {
			General.sleep(AntiBan.getReactionTime());
			General.println("Generated reaction time: " + AntiBan.getReactionTime());
			Timing.waitCondition(Conditions.get().run_On(), General.random(3500, 5000));
		}
	}

	@Override
	public String mission() {
		return "TOGGLE RUN.";
	}

}
