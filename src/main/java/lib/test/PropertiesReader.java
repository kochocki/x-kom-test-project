package lib.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import lib.enums.PropertiesType;

public abstract class PropertiesReader {
	public static Properties getProperties(PropertiesType pt) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(pt.getPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;
	}

}
