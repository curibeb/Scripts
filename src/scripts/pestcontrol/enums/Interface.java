package scripts.pestcontrol.enums;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;

public enum Interface {

	YELLOPORTAL(408, 14), BLUEPORTAL(408, 15), PINKPORTAL(408, 16), PURPLEPORTAL(408, 17), VOIDKNIGHTHP(408, 3),
	DAMAGECOLLECTED(408,3), WONGGAMEMSG(231, 3),LOSTGAMEMSG(229,0), INSIDEGAME(231,3);

	private int parent;
	private int child;

	Interface(final int parent, final int child) {
		this.parent = parent;
		this.child = child;
	}
	
	public String text(){
		RSInterface _interface = Interfaces.get(parent, child);
		if (_interface != null && !_interface.isHidden()) {
			return _interface.getText();
		}
		return "";
	}

	public int getValue() {
		RSInterface _interface = Interfaces.get(parent, child);
		if (_interface != null && !_interface.isHidden()) {
			String pt = _interface.getText();
			pt = pt.replaceAll("[^\\d.]", "");
			if (pt != null) {
				int pts = Integer.parseInt(pt);
				if (pts >= 0)
					return pts;
			}
		}
		return 0;
	}

}
