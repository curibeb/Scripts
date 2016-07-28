package scripts.flaxspinner.misc;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterfaceChild;

public enum Interface {

	SPIN_INTERFACE(459, 91),;

	private int parent;
	private int child;

	Interface(int parent, int child) {
		this.parent = parent;
		this.child = child;
	}
	
	public RSInterfaceChild getInterface(){
		return Interfaces.get(this.parent, this.child);
	}

	public boolean open() {
		RSInterfaceChild _interface = Interfaces.get(this.parent, this.child);
		return _interface != null && !_interface.isHidden();
	}

}
