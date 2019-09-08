package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class AppiumManager {
    private static AppiumManager instance;

    private AppiumDriverLocalService service;
    private AppiumDriver driver;

    private AppiumManager() {
    }

    public static AppiumManager getInstance() {
        if (instance == null) {
            synchronized (AppiumManager.class) {
                instance = new AppiumManager();
            }
        }

        return instance;
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public void launchAppWithFreshInstall() {
        if (service == null) {
            startService();
        }

        if (driver == null) {
            startDriver();
        }
        else {
            driver.resetApp();
        }
    }

    public void restartService() {
        System.out.println("Appium is restarting. Time " + System.currentTimeMillis());

        closeDriver();
        closeService();
        startService();
        startDriver();
    }

    private void startService() {
        String logLevel = ConfigurationManager.getInstance().getAppiumLogLevel();

        service = new AppiumServiceBuilder().usingAnyFreePort()
                .withArgument(GeneralServerFlag.LOG_LEVEL, logLevel)
                .build();
        service.start();
    }

    private void closeService() {
        if (service != null) {
            try {
                service.stop();
            } catch (WebDriverException exception) {
                exception.printStackTrace();
            } finally {
                service = null;
            }
        }
    }

    private void startDriver() {
        DesiredCapabilities capabilities = ConfigurationManager.getInstance().getDesiredCapabilities();

        if (Platform.isOnIOS()) {
            driver = new IOSDriver(service.getUrl(), capabilities);
        } else {
            driver = new AndroidDriver(service.getUrl(), capabilities);
        }
    }

    private void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException exception) {
                exception.printStackTrace();
            } finally {
                driver = null;
            }
        }
    }
}