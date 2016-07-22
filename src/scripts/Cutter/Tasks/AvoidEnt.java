package scripts.Cutter.Tasks;

import org.tribot.api2007.NPCs;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSNPC;

import scripts.Cutter.AntiBan.Antiban;
import scripts.Cutter.TaskFramwork.Task;
import scripts.Cutter.Utilities.Priorities;
import scripts.Cutter.Utilities.Vars;

public class AvoidEnt extends Task {

	@Override
	public int priority() {
		return Priorities.ENT.getPriority();
	}

	@Override
	public boolean validate() {
		RSNPC[] ent = NPCs.findNearest(Vars.tree);
		if (ent.length > 0) {
			for (RSNPC e : ent) {
				return e.isInteractingWithMe();
			}
		}
		return false;
	}

	@Override
	public void execute() {
		Antiban.setWaitingSince();
		Antiban.get().performReactionTimeWait();
		Walking.blindWalkTo(Vars.treesLoc.getRandomTile());
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Handling ent.";
	}

}
