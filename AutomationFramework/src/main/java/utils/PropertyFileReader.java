package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/*
 * This class contains the code for reading the property file
 *
 */
public class PropertyFileReader {
    /**
     * This method will return the Config file.
     *
     * @param key
     * @return config file
     */
    public static String readPropertyValues(final String key) {
        Properties prop;
        FileInputStream data = null;
        try {
            data = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        prop = new Properties();
        try {
            prop.load(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
