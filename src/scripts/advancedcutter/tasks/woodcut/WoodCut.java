package scripts.advancedcutter.tasks.woodcut;

import scripts.advancedcutter.api.taskframework.Task;
import scripts.advancedcutter.api.utilities.Vars;
import scripts.advancedcutter.general.BankChop;
import scripts.advancedcutter.general.PowerChop;

public class WoodCut extends Task {

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
			BankChop.execute(Vars.bankArea,Vars.chopArea);
	}
}
