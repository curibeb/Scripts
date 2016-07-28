package scripts.pestcontrol.enums;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.pestcontrol.utilities.Vars;

public enum Areas {

	WESTGATE {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.WESTGATEXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.WESTGATEXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.WESTGATEYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.WESTGATEYTILE.getYOffset()));
		}
	},

	EASTGATE {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.EASTGATEXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.EASTGATEXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.EASTGATEYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.EASTGATEYTILE.getYOffset()));
		}

	},

	SOUTHGATE {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.SOUTHGATEXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.SOUTHGATEXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.SOUTHGATEYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.SOUTHGATEYTILE.getYOffset()));
		}

	},

	GAMEAROUNDVOIDKNIGHTAREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.AROUNDVOIDKNIGHTXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.AROUNDVOIDKNIGHTXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.AROUNDVOIDKNIGHTyTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.AROUNDVOIDKNIGHTyTILE.getYOffset()));
		}

	},

	GAMEVOIDKNIGHTPROTECTAREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.VOIDKNIGHTXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.VOIDKNIGHTXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.VOIDKNIGHTYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.VOIDKNIGHTYTILE.getYOffset()));
		}

	},

	GAMEBOATAREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.GAMEBOATXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.GAMEBOATXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.GAMEBOATYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.GAMEBOATYTILE.getYOffset()));
		}

	},

	BLUEPORTALAREA {
		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.BLUEPORTALXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.BLUEPORTALXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.BLUEPORTALYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.BLUEPORTALYTILE.getYOffset()));
		}

	},

	YELLOWPORTALAREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.YELLOWPORTALXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.YELLOWPORTALXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.YELLOWPORTALYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.YELLOWPORTALYTILE.getYOffset()));
		}

	},

	PINKPORTALAREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.PINKPORTALXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.PINKPORTALXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.PINKPORTALYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.PINKPORTALYTILE.getYOffset()));
		}

	},

	PURPLEPORTALAREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.PURPLEPORTALXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.PURPLEPORTALXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.PURPLEPORTALYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.PURPLEPORTALYTILE.getYOffset()));
		}

	},

	FULLGAMEAREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.FULLGAMEXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.FULLGAMEYTILE.getXOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.FULLGAMEYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.FULLGAMEYTILE.getYOffset()));
		}

	};

	public abstract RSArea getArea();
}
