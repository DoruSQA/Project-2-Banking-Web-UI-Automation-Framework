package project.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class ConfigEnvReader {

	private static Properties properties;
	
	 static {
	        loadProperties();
	    }

	public static void loadProperties() {

		String env = System.getProperty("env", "qa");

		String fileName = "environments/" + env + "-env.properties";

		properties = new Properties();

		try (InputStream input = ConfigEnvReader.class.getClassLoader().getResourceAsStream(fileName)) {

			if (input == null) {
				throw new RuntimeException("Config file not found: " + fileName);
			}

			properties.load(input);

		} catch (IOException e) {

			throw new RuntimeException("Cannot load config: " + fileName, e);
		}
	}

	public static String get(String key) {

	    if(properties == null) {
	        loadProperties();
	    }

	    String value = properties.getProperty(key);


	    if(value != null && value.startsWith("${") && value.endsWith("}")) {

	        String systemPropertyName =
	                value.substring(2, value.length() - 1);

	        String systemValue =
	                System.getProperty(systemPropertyName);

	        if(systemValue != null) {
	            return systemValue;
	        }
	    }

	    return value;
	}
}