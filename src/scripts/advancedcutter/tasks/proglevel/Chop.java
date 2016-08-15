package scripts.advancedcutter.tasks.proglevel;

import java.util.LinkedHashMap;

import org.tribot.api.General;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.advancedcutter.general.Methods;

public class Chop {

	private static RSArea treeArea = null;
	private static RSTile treeAreaWalkTile = null;
	public static LinkedHashMap<RSArea, RSTile> locations = new LinkedHashMap<RSArea, RSTile>();

	private static boolean ready() {
		return treeArea != null && treeAreaWalkTile != null;
	}

	private static void getRandomLocation() {
		int random = General.random(0, locations.size() - 1);
		RSArea[] areas = locations.keySet().toArray(new RSArea[locations.size()]);
		treeArea = areas[random];
		if (treeArea != null)
			treeAreaWalkTile = locations.get(treeArea);
	}

	public static void execute(String tree) {
		if (ready()) {
			train(tree);
		} else {
			if (locations.size() > 0)
				getRandomLocation();
		}
	}

	private static boolean inTreeArea() {
		return treeArea.contains(Player.getPosition());
	}

	private static void train(String tree) {
		if (inTreeArea()) {
			if (Inventory.isFull())
				Methods.dropAll();
			else
				Methods.chop(tree, treeArea);
		} else {
			WebWalking.walkTo(treeAreaWalkTile);
		}
	}
}
