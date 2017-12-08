package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Helper;
import utils.Platform;

import java.util.List;

public class AddAcquaintancePage {

    // region Identifiers
    private static final String ANDROID_FIRST_NAME_FIELD = "new UiSelector().resourceIdMatches(\".*id/firstNameField.*\")";
    private static final String ANDROID_LAST_NAME_FIELD = "new UiSelector().resourceIdMatches(\".*id/lastNameField.*\")";
    private static final String ANDROID_SAVE_BUTTON = "new UiSelector().resourceIdMatches(\".*id/acquaintanceSaveButton.*\")";
    private static final String IOS_SAVE_BUTTON = "Save";
    private static final String IOS_TEXT_FIELDS = "XCUIElementTypeTextField";
    // endregion

    @AndroidFindBy(uiAutomator = ANDROID_FIRST_NAME_FIELD)
    private MobileElement firstNameFieldElement;

    @AndroidFindBy(uiAutomator = ANDROID_LAST_NAME_FIELD)
    private MobileElement lastNameFieldElement;

    @AndroidFindBy(uiAutomator = ANDROID_SAVE_BUTTON)
    @iOSFindBy(accessibility = IOS_SAVE_BUTTON)
    private MobileElement saveButtonElement;

    @iOSFindBy(className = IOS_TEXT_FIELDS)
    private List<MobileElement> textFieldElements;

    public AddAcquaintancePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void fillInRequiredFields(String firstName, String lastName) {
        getFirstNameFieldElement().clear();
        getFirstNameFieldElement().sendKeys(firstName);
        getLastNameFieldElement().clear();
        getLastNameFieldElement().sendKeys(lastName);
    }

    public void tapOnSaveButton() {
        saveButtonElement.click();
    }

    public void waitUntilPageLoads() {
        Helper.waitUntil(webDriver -> getFirstNameFieldElement().isDisplayed(),
                "Add acquaintance screen page has not loaded");
    }

    private MobileElement getFirstNameFieldElement() {
        if (Platform.isOnIOS()) {
            return textFieldElements.get(0);
        } else {
            return firstNameFieldElement;
        }
    }

    private MobileElement getLastNameFieldElement() {
        if (Platform.isOnIOS()) {
            return textFieldElements.get(1);
        } else {
            return lastNameFieldElement;
        }
    }
}