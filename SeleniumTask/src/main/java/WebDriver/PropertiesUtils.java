package WebDriver;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by khanh.le on 1/17/19.
 */
public class PropertiesUtils {
    public static String getPropAsString(String property) {
        try {
            Properties prop = new Properties();
            FileInputStream inputStream = new FileInputStream("config.properties");
            prop.load(inputStream);
            inputStream.close();
            return prop.getProperty(property);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

}
