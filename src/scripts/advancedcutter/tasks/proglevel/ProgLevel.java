package scripts.advancedcutter.tasks.proglevel;


import org.tribot.api.General;

import scripts.advancedcutter.Main;
import scripts.advancedcutter.api.taskframework.Task;
import scripts.advancedcutter.api.utilities.Vars;
import scripts.advancedcutter.enums.treeareas.DraynorTrees;
import scripts.advancedcutter.enums.treeareas.LumbridgeTrees;
import scripts.advancedcutter.enums.treeareas.VarrockTrees;
import scripts.advancedcutter.enums.treetypes.TreeTypes;
import scripts.advancedcutter.general.ProgLevelSetup;

public class ProgLevel extends Task {

	private boolean resetTree = true;
	private boolean resetOak = true;
	private boolean resetWillow = true;
	public static String logName;
	private String treeName;

	@Override
	public int priority() {
		return 1;
	}

	@Override
	public boolean validate() {
		return Vars.progressiveLevel;
	}

	private void resetLocation() {
		Main.status = "Reseting area";
		System.out.print("Location reset.");
		Chop.treeArea = null;
		Chop.treeAreaWalkTile = null;
		Chop.locations.clear();
	}

	private void endScript() {
		System.out.print("We performed progressive leveling to level 60, script ending.");
		Vars.start = false;
	}

	private void handleTree() {
		if (this.resetTree) {
			resetLocation();
			this.resetTree = false;
		} else {
			if (Chop.locations.size() == 0) {
				Chop.locations.put(DraynorTrees.NORMAL_SOUTH.getArea(), DraynorTrees.NORMAL_SOUTH.getWalkTile());
				Chop.locations.put(DraynorTrees.NORMAL_NORTH.getArea(), DraynorTrees.NORMAL_NORTH.getWalkTile());
				Chop.locations.put(VarrockTrees.NORMALS_WEST.getArea(), VarrockTrees.NORMALS_WEST.getWalkTile());
				Chop.locations.put(LumbridgeTrees.NORMALS_WEST.getArea(), LumbridgeTrees.NORMALS_WEST.getWalkTile());
				System.out.print("adding normal tree locations.");
			}
			treeName = TreeTypes.NORMAL.getName();
			logName = TreeTypes.NORMAL.getLogs();
			Chop.execute(treeName);
		}
	}

	private void handleOak() {
		if (this.resetOak) {
			resetLocation();
			this.resetOak = false;
		} else {
			if (Chop.locations.size() == 0) {
				Chop.locations.put(DraynorTrees.OAK_EAST.getArea(), DraynorTrees.OAK_EAST.getWalkTile());
				Chop.locations.put(VarrockTrees.OAKS_SOUTH.getArea(), VarrockTrees.OAKS_SOUTH.getWalkTile());
				Chop.locations.put(LumbridgeTrees.OAKS_WEST.getArea(), LumbridgeTrees.OAKS_WEST.getWalkTile());
				System.out.print("adding oak tree locations.");
			}
			treeName = TreeTypes.OAK.getName();
			logName = TreeTypes.OAK.getLogs();
			Chop.execute(treeName);
		}
	}

	private void handleWillow() {
		if (this.resetWillow) {
			resetLocation();
			this.resetWillow = false;
		} else {
			if (Chop.locations.size() == 0) {
				Chop.locations.put(DraynorTrees.WILLOW_SOUTH.getArea(), DraynorTrees.WILLOW_SOUTH.getWalkTile());
				Chop.locations.put(DraynorTrees.WILLOW_WEST.getArea(), DraynorTrees.WILLOW_WEST.getWalkTile());
				System.out.print("adding willow tree locations.");
			}
			treeName = TreeTypes.WILLOW.getName();
			logName = TreeTypes.WILLOW.getLogs();
			Chop.execute(treeName);
		}
	}

	@Override
	public void execute() {
		if (ProgLevelSetup.gotAxe()) {
			switch (ProgLevelSetup.getTree()) {
			case END:
				this.endScript();
				break;
			case NORMAL:
				this.handleTree();
				break;
			case OAK:
				this.handleOak();
				break;
			case WILLOW:
				this.handleWillow();
				break;
			default:
				break;
			}
		} else {
			General.println("Please start progressive leveling mode while having all axe in inventory.");
			Vars.start = false;
		}
	}

}
