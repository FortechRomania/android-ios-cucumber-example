package utils;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Properties;

public final class ConfigurationManager {
    private static final String APPIUM_LOG_LEVEL = "APPIUM_LOG_LEVEL";

    private static final String DEVICE_NAME = "DEVICE_NAME";
    private static final String PLATFORM_VERSION = "PLATFORM_VERSION";
    private static final String PLATFORM_NAME = "PLATFORM_NAME";
    private static final String APP_PATH = "APP_PATH";

    private static final String PLATFORM = "PLATFORM";

    private static ConfigurationManager instance;
    private final Properties properties;

    private ConfigurationManager() {
        properties = new Properties();

        try {
            loadLocalProperties();
            System.out.println("Using local properties");
        } catch (Exception ex1) {
            loadNonLocalProperties();
            System.out.println("Using non - local properties");
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
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

        capabilities.setCapability(MobileCapabilityType.APP, getValueForKey(APP_PATH));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getValueForKey(DEVICE_NAME));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, getValueForKey(PLATFORM_NAME));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, isOnIOS() ? "XCUITest" : "UIAutomator2");

        final int newCommandTimeoutInSecods = 300;
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, newCommandTimeoutInSecods);

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

    private void loadLocalProperties() {
        loadPropertiesWithPath("config/local/global.properties");

        if (isOnIOS()) {
            loadPropertiesWithPath("config/local/ios.properties");
        } else {
            loadPropertiesWithPath("config/local/android.properties");
        }
    }

    private void loadNonLocalProperties() {
        loadPropertiesWithPath("config/global.properties");

        if (isOnIOS()) {
            loadPropertiesWithPath("config/ios.properties");
        } else {
            loadPropertiesWithPath("config/android.properties");
        }
    }

    private void loadPropertiesWithPath(String path) {
        try {
            properties.load(ConfigurationManager.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}