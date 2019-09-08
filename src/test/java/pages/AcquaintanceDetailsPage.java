package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Helper;
import utils.Platform;

public class AcquaintanceDetailsPage {

    // region Identifiers
    private static final String ANDROID_ACQUAINTANCE_EDIT_BUTTON = "acquaintanceEditButton";
    private static final String IOS_ACQUAINTANCE_EDIT_BUTTON = "Edit";

    private static final String ANDROID_ACQUAINTANCE_DELETE_BUTTON = "acquaintanceDeleteButton";
    private static final String IOS_ACQUAINTANCE_DELETE_BUTTON = "Delete";

    private static final String ANDROID_TOOLBAR = "toolbar";
    private static final String IOS_TOOLBAR = "XCUIElementTypeNavigationBar";
    // endregion

    @AndroidFindBy(id = ANDROID_ACQUAINTANCE_EDIT_BUTTON)
    @iOSXCUITFindBy(accessibility = IOS_ACQUAINTANCE_EDIT_BUTTON)
    private MobileElement acquaintanceEditButtonElement;

    @AndroidFindBy(id = ANDROID_ACQUAINTANCE_DELETE_BUTTON)
    @iOSXCUITFindBy(accessibility = IOS_ACQUAINTANCE_DELETE_BUTTON)
    private MobileElement acquaintanceDeleteButtonElement;

    @AndroidFindBy(id = ANDROID_TOOLBAR)
    @iOSXCUITFindBy(className = IOS_TOOLBAR)
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