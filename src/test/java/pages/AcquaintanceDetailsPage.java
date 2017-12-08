package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Helper;
import utils.Platform;

public class AcquaintanceDetailsPage {

    // region Identifiers
    private static final String ANDROID_ACQUAINTANCE_EDIT_BUTTON = "new UiSelector().resourceIdMatches(\".*id/acquaintanceEditButton.*\")";
    private static final String IOS_ACQUAINTANCE_EDIT_BUTTON = "Edit";
    private static final String ANDROID_ACQUAINTANCE_DELETE_BUTTON = "new UiSelector().resourceIdMatches(\".*id/acquaintanceDeleteButton.*\")";
    private static final String IOS_ACQUAINTANCE_DELETE_BUTTON = "Delete";
    private static final String ANDROID_TOOLBAR = "new UiSelector().resourceIdMatches(\".*id/toolbar.*\")";
    private static final String IOS_TOOLBAR = "//XCUIElementTypeNavigationBar[1]";
    // endregion

    @AndroidFindBy(uiAutomator = ANDROID_ACQUAINTANCE_EDIT_BUTTON)
    @iOSFindBy(accessibility = IOS_ACQUAINTANCE_EDIT_BUTTON)
    private MobileElement acquaintanceEditButtonElement;

    @AndroidFindBy(uiAutomator = ANDROID_ACQUAINTANCE_DELETE_BUTTON)
    @iOSFindBy(accessibility = IOS_ACQUAINTANCE_DELETE_BUTTON)
    private MobileElement acquaintanceDeleteButtonElement;

    @AndroidFindBy(uiAutomator = ANDROID_TOOLBAR)
    @iOSFindBy(xpath = IOS_TOOLBAR)
    private MobileElement toolbarElement;

    public AcquaintanceDetailsPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void tapOnEditAcquaintanceButton() {
        acquaintanceEditButtonElement.click();
    }

    public void tapOnDeleteAcquaintanceButton() {
        acquaintanceDeleteButtonElement.click();
    }

    public String getToolbarTitle() {
        waitUntilPageLoads();

        return getToolbarTitleText();
    }

    public void waitUntilPageLoads() {
        Helper.waitUntil(webDriver -> !getToolbarTitleText().isEmpty(),
                "Acquaintance details page has not loaded");
    }

    private String getToolbarTitleText() {
        if (Platform.isOnIOS()) {
            return toolbarElement.getAttribute("name");
        } else {
            return toolbarElement.findElementsByClassName("android.widget.TextView").get(0).getText();
        }
    }
}