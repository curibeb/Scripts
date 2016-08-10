package scripts.api.items;

import java.awt.Point;
import java.awt.Rectangle;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;

public class InteractInv {

	private String name;
	private String action;
	private int id;
	private boolean useName;

	public InteractInv() {

	}

	public InteractInv(boolean useName, String name, int id, String action) {
		this.name = name;
		this.action = action;
		this.id = id;
		this.useName = useName;
	}

	private RSItem[] items() {
		return Inventory.find(new Filter<RSItem>() {
			@Override
			public boolean accept(RSItem a) {
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

	private RSItem item() {
		RSItem[] items = this.items();
		if (items.length == 0)
			return null;
		return items[0];
	}

	public boolean click() {
		RSItem item = this.item();
		if (item == null)
			return false;
		return item.click(action);
	}

	public void dropAll() {
		if (GameTab.getOpen() != GameTab.TABS.INVENTORY) {
			GameTab.open(GameTab.TABS.INVENTORY);
		}
		RSItem[] items = to28(Inventory.getAll());
		if (items.length > 0) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 7; j++) {
					if (items[4 * j + i] == null)
						continue;
					Rectangle r = new RSItem(4 * j + i, 0, 0, RSItem.TYPE.INVENTORY).getArea();
					if (!r.contains(Mouse.getPos())) {
						Mouse.move(new Point((int) r.getCenterX() + General.random(-3, 3),
								(int) r.getCenterY() + General.random(-3, 3)));
					}
					if (r.contains(Mouse.getPos())) {
						Mouse.click(3);
						final int yy = getY();
						if (yy == -1) {
							Mouse.click(1);
						} else {
							Mouse.hop(new Point((int) Mouse.getPos().getX(), yy));
							Mouse.click(1);
						}
					}
				}
			}
		}
	}

	private static RSItem[] to28(RSItem[] in) {
		RSItem[] items = new RSItem[28];
		if (items.length > 0) {
			for (int i = 0; i < in.length; i++)
				items[in[i].getIndex()] = in[i];
		}
		return items;
	}

	private static int getY() {
		if (ChooseOption.isOpen()) {
			final String[] actions = ChooseOption.getOptions();
			if (actions.length > 0) {
				for (int i = 0; i < actions.length; i++) {
					if (actions[i].toLowerCase().contains("drop"))
						return (int) (ChooseOption.getPosition().getY() + 21 + 16 * i);
				}
			}
		}
		return -1;
	}
}
