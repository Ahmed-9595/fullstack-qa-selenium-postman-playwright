package com.ElectroPI.qa.utils;

import java.util.Properties;

public class ConfigUtils {

    private Properties properties ;
    private static  ConfigUtils configUtils;

    private  ConfigUtils (){

        String env = System.getProperty("environment","dev");

        switch (env){
            case "dev" -> {
                properties = new PropertiesUtils().loadProperties("src/main/java/com/ElectroPI/qa/config/dev.properties");
            }

            case "live" -> {
                properties = new PropertiesUtils().loadProperties("src/main/java/com/qa/test/configFiles/live.properties");
            }

            default -> {
                throw new RuntimeException("this environment is not support");
            }

        }
    }

    public static ConfigUtils getInstance(){
        if(configUtils==null){
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    public String getProperty(String key){
        String property = properties.getProperty(key);
        if (property != null) {
            return property;
        }
        throw new RuntimeException(key+ " is not exist in config file");
    }
}
