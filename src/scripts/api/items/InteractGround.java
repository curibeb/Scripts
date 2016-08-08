package scripts.api.items;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Camera;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSGroundItem;

public class InteractGround {

	private String name;
	private String action;

	public InteractGround(String name, String action) {
		this.name = name;
		this.action = action;
	}

	private RSGroundItem[] items() {
		return GroundItems.findNearest(new Filter<RSGroundItem>() {
			@Override
			public boolean accept(RSGroundItem a) {
				if (a == null)
					return false;
				if (a.getDefinition() == null)
					return false;
				return a.getDefinition().getName().equals(name);
			}
		});
	}

	private RSGroundItem item() {
		RSGroundItem[] items = this.items();
		if (items.length > 0)
			return items[0];
		return null;
	}

	private boolean walkTo(RSGroundItem object) {
		if (object == null)
			return false;
		return Walking.blindWalkTo(object);
	}

	private boolean setCameraAngle(RSGroundItem object) {
		if (object == null)
			return false;
		Camera.setCameraAngle(General.random(50, 70));
		return object.isClickable();
	}

	public boolean itemAvailable() {
		RSGroundItem[] items = this.items();
		return items.length > 0;
	}

	public boolean click() {
		RSGroundItem item = this.item();
		if (item == null)
			return false;
		if (!item.isOnScreen())
			return this.walkTo(item);
		if (!item.isClickable())
			return this.setCameraAngle(item);
		return DynamicClicking.clickRSGroundItem(item, action);
	}
}
