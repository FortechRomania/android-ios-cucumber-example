package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.core.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

public final class Helper {
    private static final long ONE_SECOND_IN_MILLIS = 1000;
    private static final int DURATION_FOR_ELEMENT_TO_LOAD_SECONDS = 15;

    private Helper() {
    }

    public static void navigateBack() {
        final int numberOfBackSteps = 1;

        for (int i = 0; i < numberOfBackSteps; i++) {

            if (Platform.isOnIOS()) {
                By leftTopButtonXpath = By.xpath("//XCUIElementTypeNavigationBar[1]/XCUIElementTypeButton[1]");
                waitForElementToLoadBy(leftTopButtonXpath);

                getDriver().findElement(leftTopButtonXpath).click();
            } else {
                getDriver().navigate().back();
            }
        }
    }

    public static void waitUntil(ExpectedCondition<?> condition) {
        waitUntil(condition, DURATION_FOR_ELEMENT_TO_LOAD_SECONDS);
    }

    public static void waitUntil(ExpectedCondition<?> condition, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);

        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).until(condition);
    }

    public static void takeScreenshotForScenario(Scenario scenario) throws WebDriverException {
        String currentWorkingDirectory = System.getProperty("user.dir");
        String fileName = scenario.getId().replace("/", "") + ".png";
        String outputLocation = currentWorkingDirectory + "/failed_screenshots/" + fileName;

        File screenshot = getDriver().getScreenshotAs(OutputType.FILE);
        scenario.embed(Helper.toByteArray(screenshot), "image/png");

        try {
            FileUtils.copyFile(screenshot, new File(outputLocation));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void scrollDownFullScreenUntilCondition(ExpectedConditionWrapper condition) {
        int timeOutInSeconds = 120;
        long scrollStartTimeInMillis = System.currentTimeMillis();

        while (condition.apply(getDriver()) != Boolean.TRUE) {
            scrollDownFullScreen();

            long timePassedInSeconds = (System.currentTimeMillis() - scrollStartTimeInMillis) / ONE_SECOND_IN_MILLIS;

            if (timePassedInSeconds > timeOutInSeconds) {
                Assert.fail("Scrolling down timed out");
            }
        }
    }

    private static void scrollDownFullScreen() {
        final double heightMultiplier = 0.8;
        Dimension dimensions = getWindowSize();
        int centerX = dimensions.getWidth() / 2;

        int scrollStart = (int) (dimensions.getHeight() * heightMultiplier);
        int scrollEnd = 0;

        final int swipeDurationInMillis = 1200;
        swipe(centerX, scrollStart, centerX, scrollEnd, swipeDurationInMillis);
    }

    private static Dimension getWindowSize() {
        return getDriver().manage().window().getSize();
    }

    private static void swipe(int startx, int starty, int endx, int endy, int duration) {
        TouchAction touchAction = new TouchAction(getDriver());
        Duration swipeDuration = Duration.ofMillis(duration);

        touchAction.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(swipeDuration)).moveTo(PointOption.point(endx, endy)).release();

        touchAction.perform();
    }

    private static AppiumDriver getDriver() {
        return AppiumManager.getInstance().getDriver();
    }

    private static void waitForElementToLoadBy(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DURATION_FOR_ELEMENT_TO_LOAD_SECONDS);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    private static byte[] toByteArray(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            return IOUtils.toByteArray(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}