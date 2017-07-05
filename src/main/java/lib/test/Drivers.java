package lib.test;

import java.security.InvalidParameterException;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Drivers {
	public static WebDriver driver;
	private final static String ffDriverFile = "geckodriver.exe";
	private final static String chromeDriverFile = "chromedriver.exe";
	private final static String phantomjsDriverFile = "phantomjs.exe";

	private static String driverPath = System.getProperty("user.dir") + "/src/resources/webdrivers/";
	static Properties p = getBrowserProps();

	public static void setDriver() {
		System.out.println("Starting driver");
		switch (p.getProperty("browser").toUpperCase()) {
		case "FIREFOX":
			setFirefoxDriver();
			break;
		case "CHROME":
			setChromeDriver();
			break;
		case "HTMLUNIT":
			setHtmlUnitDriver();
			break;
		case "PHANTOMJS":
			setPhantomJsDriver();
			break;
		default:
			throw new InvalidParameterException("Unknown browser: " + p.getProperty("browser"));
		}
		Drivers.driver.manage().window().setSize(new Dimension(Integer.parseInt(p.getProperty("resolutionWidth")),
				Integer.parseInt(p.getProperty("resolutionHeight"))));
		System.out.println("Driver started");
	}

	private static Properties getBrowserProps() {
		PropertiesReader pr = new PropertiesReader();
		return pr.getBrowserProperties();
	}

	private static void setFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", driverPath + ffDriverFile);
		driverPath += ffDriverFile;
		Drivers.driver = new FirefoxDriver();

	}

	private static void setChromeDriver() {
		System.setProperty("webdriver.chrome.driver", driverPath + chromeDriverFile);
		Drivers.driver = new ChromeDriver();
	}

	private static void setHtmlUnitDriver() {
		Drivers.driver = new HtmlUnitDriver();
	}

	private static void setPhantomJsDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("phantomjs.binary.path", driverPath + phantomjsDriverFile);
		Drivers.driver = new PhantomJSDriver(caps);
	}

}
