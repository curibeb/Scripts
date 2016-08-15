package scripts.advancedcutter.enums.treetypes;

public enum TreeTypes {
	NORMAL("Tree", "Logs"), OAK("Oak", "Oak logs"), WILLOW("Willow", "Willow logs"), YEW("Yew", "Yew logs");

	private String name;
	private String logs;

	TreeTypes(String name, String logs) {
		this.name = name;
		this.logs = logs;
	}

	public String getName() {
		return this.name;
	}

	public String getLogs() {
		return this.logs;
	}
}
