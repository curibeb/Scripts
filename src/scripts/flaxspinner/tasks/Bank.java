package scripts.flaxspinner.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSTile;

import scripts.flaxspinner.misc.Catherby;
import scripts.flaxspinner.misc.Lumby;
import scripts.flaxspinner.positions.Areas;
import scripts.flaxspinner.taskframework.Task;
import scripts.flaxspinner.utilities.Conditions;
import scripts.flaxspinner.utilities.Vars;

public class Bank extends Task {

	String status = "";
	RSArea bankArea;

	public Bank(RSArea bankArea) {
		this.bankArea = bankArea;
	}

	@Override
	public int priority() {
		return Vars.bankPriority;
	}

	@Override
	public boolean validate() {
		return Inventory.getCount("Flax") == 0;
	}

	@Override
	public void execute() {
		if (bankArea.contains(this.myTile())) {
			this.performBanking();
		}
		if (!Vars.lumby) {
			status = Catherby.status;
			Catherby.handleCatherby();
		} else {
			status = Lumby.status;
			if (Areas.LUMBYSECONDFLOOR.getArea().contains(this.myTile())) {
				Lumby.headToBankLumb();
			}
		}
	}

	public boolean lost() {
		if (!Vars.lumby) {
			return !Areas.BANK.getArea().contains(this.myTile())
					&& !Areas.BUILDINGFIRSTFLOOR.getArea().contains(this.myTile())
					&& !Areas.BUILDINGGROUNDFLOOR.getArea().contains(this.myTile());
		} else {
			return Areas.LUMBYFIRSTFLOOR.getArea().contains(this.myTile());
		}
	}

	public RSTile myTile() {
		return Player.getPosition();
	}

	private int getCurrentBankSpace() {
		RSInterface amount = Interfaces.get(12, 5);
		if (amount != null && !amount.isHidden()) {
			String text = amount.getText();
			if (text != null) {
				try {
					int parse = Integer.parseInt(text);
					if (parse > 0)
						return parse;
				} catch (NumberFormatException e) {
					return -1;
				}
			}
		}
		return -1;
	}

	public boolean isBankItemsLoaded() {
		return getCurrentBankSpace() == Banking.getAll().length;
	}

	public void performBanking() {
		this.status = "Grabbing flax.";
		RSItem[] flax = Banking.find("Flax");
		if (Banking.isBankScreenOpen()) {
			if (isBankItemsLoaded()) {
				if (flax.length > 0) {
					if (Inventory.isFull()) {
						if (Banking.depositAll() > 0) {
							Timing.waitCondition(Conditions.inventoryHasNothing(), General.random(7500, 10000));
						}
					} else {
						if (Banking.withdraw(0, "Flax")) {
							Timing.waitCondition(Conditions.inventoryFull(), General.random(7500, 10000));
						}
					}
				} else {
					General.println("flax not found");
					Vars.start = false;
				}
			}
		} else {
			if (Banking.openBank()) {
				Timing.waitCondition(Conditions.bankingScreenOpen(), General.random(7500, 10000));
			}
		}
	}

	@Override
	public String status() {
		return this.status;
	}

}
