package lib.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

import lib.enums.PropertiesType;

public abstract class BaseTest {

	Properties p;

	@BeforeClass
	public void beforeClass() throws FileNotFoundException, IOException {
		p = PropertiesReader.getProperties(PropertiesType.TESTCASE);
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
