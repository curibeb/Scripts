package scripts.cutter.utilities;

import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;

import scripts.cutter.tasks.Chop;

public class Conditions {

	public static Condition gotAxe = new Condition() {
		@Override
		public boolean active() {
			return Inventory.getCount(1359) > 0;
		}
	};

	public static Condition hovered() {
		return new Condition() {
			@Override
			public boolean active() {
				return Chop.hovered();
			}
		};
	}

	public static Condition bankOpen = new Condition() {
		@Override
		public boolean active() {
			return Banking.isBankScreenOpen();
		}
	};

	public static Condition bankClosed = new Condition() {
		@Override
		public boolean active() {
			return !Banking.isBankScreenOpen();
		}
	};

	public static Condition animating = new Condition() {
		@Override
		public boolean active() {
			return Player.getAnimation() != -1;
		}
	};

	public static Condition idle = new Condition() {
		@Override
		public boolean active() {
			return Player.getAnimation() == -1;
		}
	};

	public static Condition bankedLogs = new Condition() {
		@Override
		public boolean active() {
			return Inventory.getAll().length == 0;
		}
	};

	public static Condition equipmentOn() {
		return new Condition() {
			@Override
			public boolean active() {
				return Equipment.isEquipped(Vars.axeId);
			}
		};
	}

	public static Condition runIsOn() {
		return new Condition() {
			@Override
			public boolean active() {
				return Game.isRunOn();
			}
		};
	}
}
