package scripts.aiocooking.enums;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSTile;

import scripts.aiocooking.utils.Vars;

public enum Objectives {
	BANK {
		@Override
		public RSTile get_Walk_Tile() {
			return Vars.bank_Walk_Tile;
		}

		@Override
		public RSArea get_Area() {
			return new RSArea(Vars.first_Bank_Area_tile, Vars.second_Bank_Area_tile);
		}

		@Override
		public int get_Priority() {
			return 0;
		}

		@Override
		public boolean interface_Open() {
			return Banking.isBankScreenOpen();
		}

	},
	COOK {
		@Override
		public RSTile get_Walk_Tile() {
			return Vars.cook_Walk_Tile;
		}

		@Override
		public RSArea get_Area() {
			return new RSArea(Vars.first_Cook_Area_Tile, Vars.second_Cook_Area_Tile);
		}

		@Override
		public int get_Priority() {
			return 1;
		}

		@Override
		public boolean interface_Open() {
			RSInterface cook_Inteface = Interfaces.get(307, 7);
			return cook_Inteface != null && !cook_Inteface.isHidden();
		}

	};

	public abstract int get_Priority();

	public abstract RSTile get_Walk_Tile();

	public abstract RSArea get_Area();

	public abstract boolean interface_Open();
}
