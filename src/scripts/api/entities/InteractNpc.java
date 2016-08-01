package scripts.api.entities;

import java.util.Arrays;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Camera;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSNPC;

public class InteractNpc {

	private String name;
	private String action;
	private RSArea area;

	public InteractNpc(String name, String action, RSArea area) {
		this.area = area;
		this.action = action;
		this.name = name;
	}

	private RSNPC[] npc() {
		return NPCs.findNearest(new Filter<RSNPC>() {
			@Override
			public boolean accept(RSNPC a) {
				if (a == null)
					return false;
				if (a.getDefinition() == null)
					return false;
				String[] actions = a.getDefinition().getActions();
				if (actions.length == 0)
					return false;
				if (!Arrays.asList(actions).contains(action))
					return false;

				return a.getName().equals(name) && area.contains(a);
			}
		});
	}

	private RSNPC target() {
		RSNPC[] npc = this.npc();
		if (npc.length > 0)
			return npc[0];
		return null;
	}

	private boolean walkTo(RSNPC object) {
		return Walking.blindWalkTo(object);
	}

	private boolean setCameraAngle(RSNPC object) {
		if (object == null)
			return false;
		Camera.setCameraAngle(General.random(50, 70));
		return object.isClickable();
	}

	public boolean click() {
		if (target() == null)
			return false;
		if (!target().isOnScreen())
			return this.walkTo(target());
		if (!target().isClickable())
			return this.setCameraAngle(target());
		return DynamicClicking.clickRSNPC(target(), action);
	}

}
