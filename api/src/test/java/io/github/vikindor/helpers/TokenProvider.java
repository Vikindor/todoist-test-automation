package io.github.vikindor.helpers;

import java.io.InputStream;
import java.util.Properties;

public class TokenProvider {

    private static final String FILE = "auth.properties";
    private static final String TOKEN = "accessToken";

    public static String getToken() {
        try (InputStream inputStream = TokenProvider.class.getClassLoader().getResourceAsStream(FILE)) {

            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(TOKEN);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load access token", e);
        }
    }
}
