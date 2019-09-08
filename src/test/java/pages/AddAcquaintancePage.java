package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Helper;

public class AddAcquaintancePage {

    // region Identifiers
    private static final String ANDROID_FIRST_NAME_FIELD = "firstNameField";
    private static final String IOS_FIRST_NAME_FIELD = "_FirstNameField";

    private static final String ANDROID_LAST_NAME_FIELD = "lastNameField";
    private static final String IOS_LAST_NAME_FIELD = "_LastNameField";

    private static final String ANDROID_SAVE_BUTTON = "acquaintanceSaveButton";
    private static final String IOS_SAVE_BUTTON = "Save";
    // endregion

    @AndroidFindBy(id = ANDROID_FIRST_NAME_FIELD)
    @iOSXCUITFindBy(accessibility = IOS_FIRST_NAME_FIELD)
    private MobileElement firstNameFieldElement;

    @AndroidFindBy(id = ANDROID_LAST_NAME_FIELD)
    @iOSXCUITFindBy(accessibility = IOS_LAST_NAME_FIELD)
    private MobileElement lastNameFieldElement;

    @AndroidFindBy(id = ANDROID_SAVE_BUTTON)
    @iOSXCUITFindBy(accessibility = IOS_SAVE_BUTTON)
    private MobileElement saveButtonElement;

    public AddAcquaintancePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void fillInRequiredFields(String firstName, String lastName) {
        firstNameFieldElement.clear();
        firstNameFieldElement.sendKeys(firstName);
        lastNameFieldElement.clear();
        lastNameFieldElement.sendKeys(lastName);
    }

    public void tapOnSaveButton() {
        saveButtonElement.click();
    }

    public void waitUntilPageLoads() {
        Helper.waitUntil(webDriver -> firstNameFieldElement.isDisplayed(),
                "Add acquaintance screen page has not loaded");
    }

}