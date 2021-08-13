package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    Properties properties=new Properties();
    public PropertyReader(String path) throws IOException {
        InputStream inputStream=new FileInputStream(path);
        properties.load(inputStream);
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
