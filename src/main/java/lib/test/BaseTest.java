package lib.test;

import java.util.Properties;

import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

	Properties p;

	@BeforeClass
	public void beforeClass() {
		PropertiesReader pr = new PropertiesReader();
		p = pr.getTestCaseProperties();
	}

	public void goTo(String url) {
		System.out.println("Visiting: " + url);
		Drivers.driver.get(url);
	}

	public void beforeTest() {
		Drivers.setDriver();
	}

	public void afterTest() {
		Drivers.driver.quit();
	}
}
