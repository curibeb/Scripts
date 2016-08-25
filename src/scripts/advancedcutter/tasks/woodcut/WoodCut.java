package scripts.advancedcutter.tasks.woodcut;

import scripts.advancedcutter.api.taskframework.Task;
import scripts.advancedcutter.api.utilities.Vars;
import scripts.advancedcutter.general.BankChop;
import scripts.advancedcutter.general.PowerChop;
import scripts.advancedcutter.general.ProgLevelSetup;

public class WoodCut extends Task {

	@Override
	public int priority() {
		return 0;
	}

	@Override
	public boolean validate() {
		return ProgLevelSetup.gotAxe();
	}

	@Override
	public void execute() {
		if (Vars.powerChop)
			PowerChop.execute(Vars.chopArea);
		else
			BankChop.execute(Vars.bankArea, Vars.chopArea);
	}
}
