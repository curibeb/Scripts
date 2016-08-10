package scripts.pestcontrol.api.entities;

import java.util.Arrays;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Camera;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSNPCDefinition;

public class InteractNpc {

	private String name;
	private String action;
	private RSArea area;
	private boolean allNpcs;

	public InteractNpc(String name, String action, RSArea area, boolean allNpcs) {
		this.area = area;
		this.action = action;
		this.name = name;
		this.allNpcs = allNpcs;
	}

	private Filter<RSNPC> npcFilter() {
		return new Filter<RSNPC>() {
			@Override
			public boolean accept(RSNPC a) {
				RSNPCDefinition def = a.getDefinition();
				if (def == null)
					return false;
				String[] actions = def.getActions();
				if (actions.length == 0)
					return false;
				if (!Arrays.asList(actions).contains(action))
					return false;
				if (allNpcs)
					return area.contains(a);
				else
					return def.getName().equals(name);
			}
		};
	}

	private Filter<RSNPC> areaFilter(RSArea area) {
		return org.tribot.api2007.ext.Filters.NPCs.inArea(area);
	}

	private RSNPC[] npc() {
		return NPCs.findNearest(areaFilter(this.area).combine(this.npcFilter(), false));
	}

	private RSNPC target() {
		RSNPC[] npc = this.npc();
		if (npc.length > 0)
			return npc[0];
		return null;
	}

	private boolean walkTo(RSNPC object) {
		if (object == null)
			return false;
		return Walking.blindWalkTo(object);
	}

	private boolean setCameraAngle(RSNPC object) {
		if (object == null)
			return false;
		Camera.setCameraAngle(General.random(50, 70));
		return object.isClickable();
	}

	public boolean npcAvailable() {
		RSNPC target = this.target();
		return target != null;
	}

	public boolean click() {
		RSNPC target = this.target();
		if (target == null)
			return false;
		if (!target.isOnScreen())
			return this.walkTo(target);
		if (!target.isClickable())
			return this.setCameraAngle(target);
		return DynamicClicking.clickRSNPC(target, action);
	}
}
