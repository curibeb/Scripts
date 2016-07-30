package scripts.aiocooking.enums;

public enum Food {
	
	MEAT("Meat",30),
	SHRIMP("Raw shrimps",30),
	CHICKEN("Chicken",30),
	SARDINE("Sardine",40),
	HERRING("Herring",50),
	TROUT("Trout",70),
	PIKE("Pike",80),
	SALMON("Salmon",90),
	TUNA("Tuna",100),
	LOBSTER("Lobster",120),
	SWORDFISH("Swordfish",140),;
	
	private String name;
	private int xp;
	
	Food (String name, int xp){
		this.name = name;
		this.xp = xp;
	}
	
	public String getName(){
		return name;
	}
	
	public int getXp(){
		return xp;
	}
	
}
