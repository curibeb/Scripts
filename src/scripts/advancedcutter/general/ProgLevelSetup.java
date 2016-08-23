package scripts.advancedcutter.general;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSItem;

public class ProgLevelSetup {

	public enum Tree {
		NORMAL, OAK, WILLOW, END;
	};

	public static String axeName() {
		int wcLevel = Skills.getActualLevel(SKILLS.WOODCUTTING);
		if (wcLevel < 6)
			return "Bronze axe";
		if (wcLevel >= 6 && wcLevel < 21)
			return "Steel axe";
		if (wcLevel >= 21 && wcLevel < 31)
			return "Mithril axe";
		if (wcLevel >= 31 && wcLevel < 41)
			return "Adamant axe";
		if (wcLevel >= 31 && wcLevel < 41)
			return "Rune axe";
		return "Bronze axe";
	}

	public static boolean gotAxe() {
		RSItem[] axes = Inventory.find(axeName());
		return axes.length > 0;
	}

	public static Tree getTree() {
		int wcLevel = Skills.getActualLevel(SKILLS.WOODCUTTING);
		if (wcLevel < 15)
			return Tree.NORMAL;
		if (wcLevel >= 15 && wcLevel < 30)
			return Tree.OAK;
		if (wcLevel >= 30 && wcLevel < 60)
			return Tree.WILLOW;
		if (wcLevel >= 60)
			return Tree.END;
		return Tree.NORMAL;
	}

}
