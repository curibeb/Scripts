package scripts.advancedcutter.enums.treeareas;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public enum DraynorTrees {

	OAK_EAST {
		@Override
		public RSArea getArea() {
			return new RSArea(new RSTile(3158, 3287, 0), new RSTile(3182, 3261, 0));
		}

		@Override
		public RSTile getWalkTile() {
			return new RSTile(3169, 3277, 0);
		}

	},
	WILLOW_SOUTH {
		@Override
		public RSArea getArea() {
			return new RSArea(new RSTile(3073, 3240, 0), new RSTile(3097, 3218, 0));
		}

		@Override
		public RSTile getWalkTile() {
			return new RSTile(3087, 3236, 0);
		}
	},

	WILLOW_WEST {

		@Override
		public RSArea getArea() {
			return new RSArea(new RSTile(3053, 3260, 0), new RSTile(3065, 3250, 0));
		}

		@Override
		public RSTile getWalkTile() {
			return new RSTile(3058, 3255, 0);
		}
	},

	NORMAL_SOUTH {

		@Override
		public RSArea getArea() {
			return new RSArea(new RSTile(3095, 3237, 0), new RSTile(3127, 3212, 0));
		}

		@Override
		public RSTile getWalkTile() {
			return new RSTile(3109, 3227, 0);
		}

	},
	NORMAL_NORTH {

		@Override
		public RSArea getArea() {
			return new RSArea(new RSTile(3082, 3317, 0), new RSTile(3112, 3285, 0));
		}

		@Override
		public RSTile getWalkTile() {
			return new RSTile(3100, 3301, 0);
		}

	};

	public abstract RSArea getArea();

	public abstract RSTile getWalkTile();
}
