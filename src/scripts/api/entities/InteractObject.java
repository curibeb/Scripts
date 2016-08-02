package scripts.api.entities;

import java.util.Arrays;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;

public class InteractObject {

	private String name;
	private int distance;
	private String action;
	private RSArea area;

	public InteractObject(RSArea area, int distance, String name, String action) {
		this.name = name;
		this.distance = distance;
		this.action = action;
		this.area = area;
	}

	private RSObject[] objects() {
		return Objects.findNearest(distance, new Filter<RSObject>() {
			@Override
			public boolean accept(RSObject o) {
				if (o == null)
					return false;
				if (o.getDefinition() == null)
					return false;
				String[] actions = o.getDefinition().getActions();
				if (actions.length == 0)
					return false;
				if (!Arrays.asList(actions).contains(action))
					return false;
				return o.getDefinition().getName().equals(name) && area.contains(o);
			}
		});
	}

	private RSObject target() {
		RSObject[] objects = this.objects();
		if (objects.length == 0)
			return null;
		return objects[0];
	}

	private boolean walkTo(RSObject object) {
		return Walking.blindWalkTo(object);
	}

	private boolean setCameraAngle(RSObject object) {
		if (object == null)
			return false;
		Camera.setCameraAngle(General.random(50, 70));
		return object.isClickable();
	}

	public boolean click() {
		RSObject target = this.target();
		if (target == null)
			return false;
		if (!target.isOnScreen())
			return this.walkTo(target());
		if (!target.isClickable())
			return this.setCameraAngle(target());
		return DynamicClicking.clickRSObject(target(), action);
	}

}
