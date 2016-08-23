package scripts.advancedcutter.tasks.normal;

import scripts.advancedcutter.api.taskframework.Task;
import scripts.advancedcutter.api.utilities.Vars;

public class Normal extends Task {

	@Override
	public int priority() {
		return 0;
	}

	@Override
	public boolean validate() {
		return Vars.normalChop;
	}

	@Override
	public void execute() {
		
	}

}
