package scripts.advancedcutter.tasks.custom;

import scripts.advancedcutter.api.taskframework.Task;
import scripts.advancedcutter.api.utilities.Vars;

public class Custom extends Task {

	@Override
	public int priority() {
		return 0;
	}

	@Override
	public boolean validate() {
		return Vars.customChop;
	}

	@Override
	public void execute() {
		if (Vars.powerChop)
			PowerChop.execute();
		else
			BankChop.execute();
	}
}
