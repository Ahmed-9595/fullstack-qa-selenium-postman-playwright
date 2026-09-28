package com.ElectroPI.qa.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {


    public Properties loadProperties(String filePath) {

        File file = new File(filePath);
        try {
            InputStream inputStream = new FileInputStream(file);

            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            return properties;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("file Path is wrong");
        } catch (IOException e) {
            throw new RuntimeException("Cannot loading the File");
        }

    }

}
