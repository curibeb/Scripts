package scripts.advancedcutter.tasks.proglevel;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

import scripts.advancedcutter.api.taskframework.Task;
import scripts.advancedcutter.api.utilities.Vars;
import scripts.advancedcutter.enums.treeareas.LumbridgeTrees;
import scripts.advancedcutter.enums.treeareas.VarrockTrees;
import scripts.advancedcutter.enums.treetypes.TreeTypes;

public class ProgLevel extends Task {

	@Override
	public int priority() {
		return 1;
	}

	@Override
	public boolean validate() {
		return Vars.progressiveLevel;
	}

	public enum State {
		NORMAL, OAK, WILLOW, END;
	};

	public static State getState() {
		int wcLevel = Skills.getActualLevel(SKILLS.WOODCUTTING);
		if (wcLevel < 15)
			return State.NORMAL;
		if (wcLevel >= 15 && wcLevel < 30)
			return State.OAK;
		if (wcLevel >= 30 && wcLevel < 60)
			return State.WILLOW;
		if (wcLevel >= 60)
			return State.END;
		return null;
	}

	private void endScript() {
		System.out.print("We performed progressive leveling to level 60, script ending.");
		Vars.start = false;
	}

	@Override
	public void execute() {
		switch (getState()) {
		case END:
			this.endScript();
			break;
		case NORMAL:
			Chop.locations.put(VarrockTrees.NORMALS_WEST.getArea(), VarrockTrees.NORMALS_WEST.getWalkTile());
			Chop.locations.put(LumbridgeTrees.NORMALS_WEST.getArea(), LumbridgeTrees.NORMALS_WEST.getWalkTile());
			Chop.execute(TreeTypes.NORMAL.getName());
			break;
		case OAK:
			Chop.locations.put(LumbridgeTrees.OAKS_WEST.getArea(), LumbridgeTrees.OAKS_WEST.getWalkTile());
			Chop.execute(TreeTypes.OAK.getName());
			break;
		case WILLOW:

			break;
		default:
			break;
		}
	}

}
