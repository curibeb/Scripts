package scripts.advancedcutter.enums.treeareas;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public enum LumbridgeTrees {

	NORMALS_WEST {
		@Override
		public RSArea getArea() {
			return new RSArea(new RSTile(3179, 3231, 0), new RSTile(3200, 3205, 0));
		}

		@Override
		public RSTile getWalkTile() {
			return new RSTile(3191, 3220, 0);
		}
	},
	OAKS_WEST {
		@Override
		public RSArea getArea() {
			return new RSArea(new RSTile(3214, 3230, 0), new RSTile(3162, 3263, 0));
		}

		@Override
		public RSTile getWalkTile() {
			return new RSTile(3192, 3246, 0);
		}
	},
	YEWS_WEST {
		@Override
		public RSArea getArea() {
			return new RSArea(new RSTile(3198, 3211, 0), new RSTile(3138, 3266, 0));
		}

		@Override
		public RSTile getWalkTile() {
			return new RSTile(3171, 3228, 0);
		}

	};

	public abstract RSArea getArea();

	public abstract RSTile getWalkTile();
}
