package lib.enums;

public enum PropertiesType {
	BROWSER(System.getProperty("user.dir") + "/src/resources/config/browser.properties"), TESTCASE(
			System.getProperty("user.dir") + "/src/resources/config/test.properties");

	private final String path;

	private PropertiesType(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
