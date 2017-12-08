package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Helper;

import java.util.List;
import java.util.stream.Collectors;

public class AcquaintancePage {

    // region Identifiers
    private static final String ANDROID_ADD_ACQUAINTANCE_BUTTON = "new UiSelector().resourceIdMatches(\".*id/acquaintanceListFloatingActionButton.*\")";
    private static final String IOS_ADD_ACQUAINTANCE_BUTTON = "Add";
    private static final String ANDROID_ACQUAINTANCES_LIST = "new UiSelector().resourceIdMatches(\".*id/nameTextView.*\")";
    private static final String IOS_ACQUAINTANCES_LIST = "AcquaintanceCellNameLabel";
    // endregion

    @AndroidFindBy(uiAutomator = ANDROID_ADD_ACQUAINTANCE_BUTTON)
    @iOSFindBy(accessibility = IOS_ADD_ACQUAINTANCE_BUTTON)
    private MobileElement addAcquaintanceButtonElement;

    @AndroidFindBy(uiAutomator = ANDROID_ACQUAINTANCES_LIST)
    @iOSFindBy(accessibility = IOS_ACQUAINTANCES_LIST)
    private List<MobileElement> acquaintanceElements;

    public AcquaintancePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public List<String> getAcquaintancesNames() {
        return acquaintanceElements.stream()
                .map(RemoteWebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getVisibleAcquaintancesNames() {
        return acquaintanceElements.stream()
                .filter(RemoteWebElement::isDisplayed)
                .map(RemoteWebElement::getText)
                .collect(Collectors.toList());
    }

    public void tapOnAddAcquaintanceButton() {
        addAcquaintanceButtonElement.click();
    }

    public void tapOnAcquaintanceAtIndex(int index) {
        acquaintanceElements.get(index).click();
    }

    public void waitUntilListIsNotEmpty() {
        Helper.waitUntil(webDriver -> !acquaintanceElements.isEmpty(),
                "Acquaintances list is empty");
    }

    public void waitUntilPageLoads() {
        Helper.waitUntil(webDriver -> addAcquaintanceButtonElement.isDisplayed(),
                "Acquaintances page has not loaded");
    }
}