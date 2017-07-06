package lib.test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeClass;

import lib.enums.PropertiesType;

public abstract class BaseTest {

	Properties p;

	@BeforeClass
	public void beforeClass() {
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
		System.out.println("Driver closed");
	}

	public void takeScreenhot(String name) {
		File screenshot = ((TakesScreenshot) Drivers.driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("c:\\tmp\\screenshot" + name + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
