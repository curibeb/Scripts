package scripts.pestcontrol.enums;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;

public enum Interface {

	YELLO_PORTAL(408, 14), BLUE_PORTAL(408, 15), PINK_PORTAL(408, 16), PURPLE_PORTAL(408, 17), VOID_KNIGHT_HP(408,
			3), DAMAGE_COLLECTED(408, 3), WON_GAME_MSG(231, 3), LOST_GAME_MSG(229, 0), INSIDE_GAME(231, 3);

	private int parent;
	private int child;

	Interface(final int parent, final int child) {
		this.parent = parent;
		this.child = child;
	}
	
	public int getParent(){
		return this.parent;
	}

	public int getChild(){
		return this.child;
	}
	
	public String text() {
		RSInterface _interface = Interfaces.get(parent, child);
		if (_interface != null && !_interface.isHidden()) {
			String msg = _interface.getText();
			if (msg != null)
				return _interface.getText();
		}
		return "";
	}

	public int getValue() {
		RSInterface _interface = Interfaces.get(parent, child);
		if (_interface != null && !_interface.isHidden()) {
			String pt = _interface.getText();
			if (pt != null) {
				pt = pt.replaceAll("[^\\d.]", "");
				int pts = Integer.parseInt(pt);
				if (pts >= 0)
					return pts;
			}
		}
		return 0;
	}

}
