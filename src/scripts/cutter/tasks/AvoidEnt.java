package scripts.cutter.tasks;

import org.tribot.api2007.NPCs;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSNPC;

import scripts.cutter.api.antiban.Antiban;
import scripts.cutter.taskframework.Task;
import scripts.cutter.utilities.Priorities;
import scripts.cutter.utilities.Vars;

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
		Antiban.getReactionTime();
		Antiban.sleepReactionTime();
		if (Walking.blindWalkTo(Vars.trees_Loc.getRandomTile()))
			Antiban.generateTrackers(Antiban.getWaitingTime());
	}

	@Override
	public String status() {
		return "Handling ent.";
	}

}
