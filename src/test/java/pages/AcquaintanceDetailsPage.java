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

    @AndroidFindBy(id = "acquaintanceEditButton")
    @iOSXCUITFindBy(accessibility = "Edit")
    private MobileElement acquaintanceEditButtonElement;

    @AndroidFindBy(id = "acquaintanceDeleteButton")
    @iOSXCUITFindBy(accessibility = "Delete")
    private MobileElement acquaintanceDeleteButtonElement;

    @AndroidFindBy(id = "toolbar")
    @iOSXCUITFindBy(className = "XCUIElementTypeNavigationBar")
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
        Helper.waitUntil(webDriver -> !getToolbarTitleText().isEmpty());
    }

    private String getToolbarTitleText() {
        if (Platform.isOnIOS()) {
            return toolbarElement.getAttribute("name");
        } else {
            return toolbarElement.findElementsByClassName("android.widget.TextView").get(0).getText();
        }
    }
}