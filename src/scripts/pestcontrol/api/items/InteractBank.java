package scripts.pestcontrol.api.items;

import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSItem;

public class InteractBank {

	private String name;
	private int amount;

	public InteractBank(String name, int amount) {
		this.name = name;
		this.amount = amount;
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
				if (a.getDefinition() == null)
					return false;

				return a.getDefinition().getName().equals(name);
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
		return Banking.withdraw(amount, name);
	}
}
