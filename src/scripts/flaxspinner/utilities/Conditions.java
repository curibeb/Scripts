package scripts.flaxspinner.utilities;

import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;

import scripts.flaxspinner.misc.Interface;
import scripts.flaxspinner.positions.Areas;
import scripts.flaxspinner.positions.Tiles;
import scripts.flaxspinner.tasks.Spin;

public class Conditions {

	public static Condition lumbyThirdFloor() {
		return new Condition() {
			@Override
			public boolean active() {
				return Areas.LUMBY_THIRD_FLOOR.getArea().contains(Spin.myTile());
			}
		};
	}

	public static Condition lumbyDoorTile() {
		return new Condition() {
			@Override
			public boolean active() {
				return !Objects.isAt(Tiles.LUMBYDOORTILE.getTile(), "Door");
			}
		};
	}

	public static Condition lumbySecondFloor() {
		return new Condition() {
			@Override
			public boolean active() {
				return Areas.LUMBY_SECOND_FLOOR.getArea().contains(Spin.myTile());
			}
		};
	}

	public static Condition objectAtLumbyDoorTile() {
		return new Condition() {
			@Override
			public boolean active() {
				return !Objects.isAt(Tiles.LUMBYDOORTILE.getTile(), "Door");
			}
		};
	}

	public static Condition buildingGroundFloor() {
		return new Condition() {
			@Override
			public boolean active() {
				return Areas.BUILDING_GROUND_FLOOR.getArea().contains(Spin.myTile());
			}
		};
	}

	public static Condition objectAtCatherbyDoorTile() {
		return new Condition() {
			@Override
			public boolean active() {
				return !Objects.isAt(Tiles.CATHERBYDOORTILE.getTile(), "Door");
			}
		};
	}

	public static Condition inventoryHasNothing() {
		return new Condition() {
			@Override
			public boolean active() {
				return Inventory.getAll().length == 0;
			}
		};
	}

	public static Condition inventoryFull() {
		return new Condition() {
			@Override
			public boolean active() {
				return Inventory.isFull();
			}
		};
	}

	public static Condition bankingScreenOpen() {
		return new Condition() {
			@Override
			public boolean active() {
				return Banking.isBankScreenOpen();
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

	public static Condition animating() {
		return new Condition() {
			@Override
			public boolean active() {
				return Player.getAnimation() != -1;
			}
		};
	}
	
	public static Condition spinInterface() {
		return new Condition() {
			@Override
			public boolean active() {
				return Interface.SPIN_INTERFACE.open();
			}
		};
	}

	public static Condition makeXInterface() {
		return new Condition() {
			@Override
			public boolean active() {
				return Spin.makeXInterface();
			}
		};
	}

	public static Condition buildingFirstFloor(){
		return new Condition() {
			@Override
			public boolean active() {
				return Areas.BUILDING_FIRST_FLOOR.getArea().contains(Spin.myTile());
			}
		};
	}
}
