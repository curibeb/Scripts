package scripts.cutter.api.items;

import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;

public class InteractBank {

	private String name;
	private int amount;
	private int id;
	private boolean useName;

	public InteractBank(boolean useName, String name, int id, int amount) {
		this.name = name;
		this.amount = amount;
		this.id = id;
		this.useName = useName;
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

	private boolean isBankItemsLoaded() {
		return getCurrentBankSpace() == Banking.getAll().length;
	}

	private RSItem[] items() {
		return Banking.find(new Filter<RSItem>() {
			@Override
			public boolean accept(RSItem a) {
				if (a == null)
					return false;
				RSItemDefinition def = a.getDefinition();
				if (def == null)
					return false;
				if (useName)
					return def.getName().equals(name);
				else
					return def.getID() == id;

			}
		});
	}

	private RSItem target() {
		RSItem[] items = this.items();
		if (items.length == 0)
			return null;
		return items[0];
	}

	private boolean itemRanOut() {
		RSItem target = this.target();
		if (!this.isBankItemsLoaded())
			return false;
		if (target == null)
			return true;
		return target.getStack() < 1;
	}

	public boolean withdraw(boolean start) {
		if (itemRanOut()) {
			start = false;
			return false;
		}
		if (useName)
			return Banking.withdraw(amount, name);
		else
			return Banking.withdraw(amount, id);

	}
}
