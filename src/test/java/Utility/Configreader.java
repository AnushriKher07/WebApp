package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configreader {
	private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream inputStream = new FileInputStream("C:\\Users\\anush\\eclipse-workspace\\WebApp\\src\\test\\java\\Config\\config.properties");
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyValue(String propertyName) {
        return properties.getProperty(propertyName);
    }
    public static String getEmail() {
        return properties.getProperty("email");
    }
    public static String getPassword() {
        return properties.getProperty("password");
    }

}
