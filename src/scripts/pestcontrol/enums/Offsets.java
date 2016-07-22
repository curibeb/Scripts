package scripts.pestcontrol.enums;

public enum Offsets {
	
	
	AROUNDVOIDKNIGHTXTILE(14,-7),
	AROUNDVOIDKNIGHTyTILE(-13,16),
	VOIDKNIGHTXTILE(8,-5),
	VOIDKNIGHTYTILE(-5,5),
	GAMEBOATXTILE(0,23),
	GAMEBOATYTILE(3,15),
	VOIDKNIGHTTILE(1,-15),
	FULLGAMEXTILE(+100,-100),
	FULLGAMEYTILE(-100,+100),
	BLUEPORTALXTILE(30, 2),
	BLUEPORTALYTILE(15 ,-8),
	YELLOWPORTALXTILE(20 ,-28),
	YELLOWPORTALYTILE(6,-15),
	PINKPORTALXTILE(-3,-31),
	PINKPORTALYTILE(-15,-15),
	PURPLEPORTALXTILE(-34, -10),
	PURPLEPORTALYTILE(-18 ,5),
	SOUTHGATEXTILE(4 ,-10),
	SOUTHGATEYTILE(-2 ,-4),
	EASTGATEXTILE(18 ,-5),
	EASTGATEYTILE(11 ,5),
	WESTGATEXTILE(-17 ,5),
	WESTGATEYTILE(-10 ,-5),;
	private int xOffset;
	private int yOffset;
	
	Offsets(final int xOffset, final int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public int getXOffset(){
		return this.xOffset;
	}
	
	public int getYOffset(){
		return this.yOffset;
	}
	
}
