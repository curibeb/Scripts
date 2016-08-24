package scripts.advancedcutter.api.conditions;

import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Equipment.SLOTS;
import org.tribot.api2007.Game;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;
import org.tribot.api2007.types.RSTile;

import com.allatori.annotations.DoNotRename;

@DoNotRename
public class Conditions {

	private static Conditions conditions = null;

	public static Conditions get() {
		return conditions != null ? conditions : new Conditions();
	}

	public Condition bankOpen() {
		return new Condition() {
			@Override
			public boolean active() {
				return Banking.isBankScreenOpen();
			}
		};
	}

	public Condition bankClosed() {
		return new Condition() {
			@Override
			public boolean active() {
				return !Banking.isBankScreenOpen();
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

	public Condition notAnimating() {
		return new Condition() {
			@Override
			public boolean active() {
				return Player.getAnimation() != -1;
			}
		};
	}

	public Condition playerMoving() {
		return new Condition() {
			@Override
			public boolean active() {
				return Player.isMoving();
			}
		};
	}

	public Condition playerIdle() {
		return new Condition() {
			@Override
			public boolean active() {
				return !Player.isMoving();
			}
		};
	}

	public Condition playerInteracting() {
		return new Condition() {
			@Override
			public boolean active() {
				return Player.getRSPlayer().getInteractingCharacter() != null;
			}
		};
	}

	public Condition canReachTile(RSTile tile) {
		return new Condition() {
			@Override
			public boolean active() {
				return PathFinding.canReach(tile, false);
			}
		};
	}

	public Condition isInArea(RSArea area) {
		return new Condition() {
			@Override
			public boolean active() {
				return area.contains(Player.getPosition());
			}
		};
	}

	public Condition inventoryEmpty() {
		return new Condition() {
			@Override
			public boolean active() {
				return Inventory.getAll().length == 0;
			}
		};
	}

	public Condition inventoryFull() {
		return new Condition() {
			@Override
			public boolean active() {
				return Inventory.isFull();
			}
		};
	}

	public Condition runIsOn() {
		return new Condition() {
			@Override
			public boolean active() {
				return Game.isRunOn();
			}
		};
	}

	public Condition haveItem(String item, int amount) {
		return new Condition() {
			@Override
			public boolean active() {
				return Inventory.getCount(item) >= amount;
			}
		};
	}

	public Condition dontHaveItem(String item) {
		return new Condition() {
			@Override
			public boolean active() {
				return Inventory.find(item).length == 0;
			}
		};
	}

	public Condition uptext_Contains(String text) {
		return new Condition() {
			@Override
			public boolean active() {
				String uptext = Game.getUptext();
				if (uptext == null)
					return false;
				return uptext.contains(text);
			}
		};
	}

	public Condition interface_Open(int parent, int child) {
		return new Condition() {
			@Override
			public boolean active() {
				RSInterfaceChild temp = Interfaces.get(parent, child);
				if (temp == null)
					return false;
				return !temp.isHidden();
			}
		};
	}

	public Condition equipmentOn(SLOTS slot, String item) {
		return new Condition() {
			@Override
			public boolean active() {
				RSItem[] item = Equipment.find(slot);
				if (item.length == 0)
					return false;
				RSItemDefinition def = item[0].getDefinition();
				if (def == null)
					return false;
				return def.getName().equals(item);
			}
		};
	}

}
