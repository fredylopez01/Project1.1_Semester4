package co.edu.uptc.project11.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

public class PersistenceProperties {
    @Value("${RUTA_ARCHIVO_PROPERTIES}")
    static String routeProperties = "src\\main\\resources\\static\\config.properties";

    public static String read(String property){
        Properties propertiesFile = new Properties();
        try {
            propertiesFile.load(new FileReader(routeProperties));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesFile.getProperty(property);
    }
}
