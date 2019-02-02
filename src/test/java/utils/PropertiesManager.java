package utils;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesManager {
    private static final String APPIUM_LOG_LEVEL = "APPIUM_LOG_LEVEL";

    private static final String DEVICE_NAME = "DEVICE_NAME";
    private static final String PLATFORM_VERSION = "PLATFORM_VERSION";
    private static final String PLATFORM_NAME = "PLATFORM_NAME";
    private static final String APP_PATH = "APP_PATH";

    private static final String PLATFORM = "PLATFORM";

    private static PropertiesManager instance;
    private final Properties properties;

    private PropertiesManager() {
        properties = new Properties();

        try {
            loadLocalProperties();
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                loadNonLocalProperties();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
        }

        return instance;
    }

    boolean isOnIOS() {
        return getPlatform().equalsIgnoreCase(MobilePlatform.IOS);
    }

    String getAppiumLogLevel() {
        return getValueForKey(APPIUM_LOG_LEVEL);
    }

    DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String deviceName = getValueForKey(DEVICE_NAME);

        capabilities.setCapability(MobileCapabilityType.APP, getValueForKey(APP_PATH));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, getValueForKey(PLATFORM_NAME));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);

        final int fiveMinutesInSeconds = 300;
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, fiveMinutesInSeconds);

        return capabilities;
    }

    private String getPlatformVersion() {
        return getValueForKey(PLATFORM_VERSION);
    }

    private String getPlatform() {
        return getValueForKey(PLATFORM);
    }

    private String getValueForKey(String key) {
        return properties.getProperty(key);
    }

    private void loadLocalProperties() throws IOException {
        loadPropertiesWithPath("config/local/environment.properties");

        if (isOnIOS()) {
            loadPropertiesWithPath("config/local/ios.properties");
        } else {
            loadPropertiesWithPath("config/local/android.properties");
        }
    }

    private void loadNonLocalProperties() throws IOException {
        loadPropertiesWithPath("config/environment.properties");

        if (isOnIOS()) {
            loadPropertiesWithPath("config/ios.properties");
        } else {
            loadPropertiesWithPath("config/android.properties");
        }
    }

    private void loadPropertiesWithPath(String path) throws IOException {
        properties.load(PropertiesManager.class.getClassLoader().getResourceAsStream(path));
    }
}