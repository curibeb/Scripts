package scripts.pestcontrol.enums;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.pestcontrol.utilities.Vars;

public enum Areas {

	WEST_GATE {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.WEST_GATE_XTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.WEST_GATE_XTILE.getYOffset()),
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.WEST_GATEY_TILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.WEST_GATEY_TILE.getYOffset()));
		}
	},

	EAST_GATE {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.EAST_GATE_XTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.EAST_GATE_XTILE.getYOffset()),
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.EAST_GATEY_TILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.EAST_GATEY_TILE.getYOffset()));
		}

	},

	SOUTH_GATE {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.SOUTH_GATEX_TILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.SOUTH_GATEX_TILE.getYOffset()),
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.SOUTH_GATE_YTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.SOUTH_GATE_YTILE.getYOffset()));
		}

	},

	GAME_AROUND_VOID_KNIGHT_AREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.AROUND_VOID_KNIGHT_XTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.AROUND_VOID_KNIGHT_XTILE.getYOffset()),
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.AROUND_VOID_KNIGHT_YTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.AROUND_VOID_KNIGHT_YTILE.getYOffset()));
		}

	},

	GAME_VOID_KNIGHT_PROTECT_AREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.VOID_KNIGHT_XTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.VOID_KNIGHT_XTILE.getYOffset()),
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.VOID_KNIGHT_YTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.VOID_KNIGHT_YTILE.getYOffset()));
		}

	},

	GAME_BOAT_AREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.GAME_BOAT_XTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.GAME_BOAT_XTILE.getYOffset()),
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.GAME_BOAT_YTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.GAME_BOAT_YTILE.getYOffset()));
		}

	},

	BLUE_PORTAL_AREA {
		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.BLUE_PORTAL_XTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.BLUE_PORTAL_XTILE.getYOffset()),
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.BLUE_PORTAL_YTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.BLUE_PORTAL_YTILE.getYOffset()));
		}

	},

	YELLOW_PORTAL_AREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.YELLOW_PORTAL_XTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.YELLOW_PORTAL_XTILE.getYOffset()),
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.YELLOW_PORTAL_YTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.YELLOW_PORTAL_YTILE.getYOffset()));
		}

	},

	PINK_PORTAL_AREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.PINK_PORTAL_XTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.PINK_PORTAL_XTILE.getYOffset()),
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.PINK_PORTAL_YTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.PINK_PORTAL_YTILE.getYOffset()));
		}

	},

	PURPLE_PORTAL_AREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.PURPLE_PORTAL_XTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.PURPLE_PORTAL_XTILE.getYOffset()),
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.PURPLE_PORTAL_YTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.PURPLE_PORTAL_YTILE.getYOffset()));
		}

	},

	FULL_GAME_AREA {

		@Override
		public RSArea getArea() {
			return new RSArea(
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.FULL_GAME_XTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.FULL_GAME_YTILE.getXOffset()),
					new RSTile(Vars.void_Knight_Tile.getX() + Offsets.FULL_GAME_YTILE.getXOffset(),
							Vars.void_Knight_Tile.getY() + Offsets.FULL_GAME_YTILE.getYOffset()));
		}

	},

	LOBBY {

		@Override
		public RSArea getArea() {
			return new RSArea(new RSTile(2613, 2681, 0), new RSTile(2701, 2625, 0));
		}
	},

	LOBBY_BOAT {

		@Override
		public RSArea getArea() {
			if (Vars.novice)
				return new RSArea(new RSTile(2660, 2643, 0), new RSTile(2663, 2638, 0));
			if (Vars.intermediate)
				return new RSArea(new RSTile(2638, 2642, 0), new RSTile(2641, 2649, 0));
			if (Vars.veteran)
				return new RSArea(new RSTile(2632, 2649, 0), new RSTile(2635, 2655, 0));
			return null;
		}
	};

	public abstract RSArea getArea();
}
