package scripts.aiocooking.utils;

import org.tribot.api.rs3.Player;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.types.RSTile;

import scripts.aiocooking.enums.Food;
import scripts.aiocooking.enums.Objectives;

public class Conditions {

	public Conditions() {

	}

	private static Conditions con = null;

	public static Conditions get() {
		return con != null ? con : new Conditions();
	}

	public Condition bank_open() {
		return new Condition() {
			@Override
			public boolean active() {
				return Banking.isBankScreenOpen();
			}
		};
	}

	public Condition got_Item() {
		return new Condition() {
			@Override
			public boolean active() {
				return Inventory.getCount(Food.SHRIMP.getName()) == 28;
			}
		};
	}

	public Condition inventory_Empty() {
		return new Condition() {
			@Override
			public boolean active() {
				return Inventory.getAll().length == 0;
			}
		};
	}

	public Condition can_Reach_Tile(RSTile tile) {
		return new Condition() {
			@Override
			public boolean active() {
				return PathFinding.canReach(tile, false);
			}
		};
	}

	public Condition animating() {
		return new Condition() {
			@Override
			public boolean active() {
				return Player.getAnimation() != -1;
			}
		};
	}

	public Condition item_Clicked() {
		return new Condition() {
			@Override
			public boolean active() {
				return Game.getUptext() != null && Game.getUptext().contains(">");
			}
		};
	}

	public Condition cook_Interface_Open() {
		return new Condition() {
			@Override
			public boolean active() {
				return Objectives.COOK.interface_Open();
			}
		};
	}
	
	public Condition run_On() {
		return new Condition() {
			@Override
			public boolean active() {
				return Objectives.COOK.interface_Open();
			}
		};
	}

}
