package lib.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	private String browserPropsPath = System.getProperty("user.dir") + "/src/resources/config/browser.properties";
	private String testCasePropsFilename = System.getProperty("user.dir") + "/src/resources/config/test.properties";

	public PropertiesReader() {
		System.out.println(browserPropsPath);
		System.out.println(testCasePropsFilename);

	}

	public Properties getTestCaseProperties() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(testCasePropsFilename));
		} catch (IOException e) {

		}
		return props;
	}

	public Properties getBrowserProperties() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(browserPropsPath));
		} catch (IOException e) {
		}
		return props;
	}

}
