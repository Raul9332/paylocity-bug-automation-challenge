package com.raul.paylocity.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) throw new RuntimeException("config.properties not found in src/test/resources");
            PROPS.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private Config() {}

    public static String get(String key) {
        String val = System.getProperty(key);
        if (val != null && !val.isBlank()) return val;
        val = PROPS.getProperty(key);
        if (val == null) throw new IllegalArgumentException("Missing config key: " + key);
        return val.trim();
    }

    public static boolean getBool(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
