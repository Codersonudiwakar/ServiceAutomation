package com.ServiceAutomation.project.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "src\\main\\resources\\config.properties";
    
    static {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(CONFIG_FILE);
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

    public static String getBaseUrl() {
        return getProperty("api.base.url");
    }
}
