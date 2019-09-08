package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Helper;

public class AddAcquaintancePage {

    @AndroidFindBy(id = "firstNameField")
    @iOSXCUITFindBy(accessibility = "_FirstNameField")
    private MobileElement firstNameFieldElement;

    @AndroidFindBy(id = "lastNameField")
    @iOSXCUITFindBy(accessibility = "_LastNameField")
    private MobileElement lastNameFieldElement;

    @AndroidFindBy(id = "acquaintanceSaveButton")
    @iOSXCUITFindBy(accessibility = "Save")
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
        Helper.waitUntil(webDriver -> firstNameFieldElement.isDisplayed());
    }

}