package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Helper;
import utils.Platform;

import java.util.List;

public class AlertConfirmationPage {

    @AndroidFindBy(id = "android:id/button1")
    private MobileElement confirmButtonElement;

    @iOSXCUITFindBy(id = "Delete")
    private List<MobileElement> deleteButtonElements;

    public AlertConfirmationPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void tapOnConfirmButton() {
        getConfirmButtonElement().click();
    }

    private MobileElement getConfirmButtonElement() {
        if (Platform.isOnIOS()) {
            Helper.waitUntil(webDriver -> deleteButtonElements.size() > 1);

            return deleteButtonElements.get(1);
        } else {
            Helper.waitUntil(webDriver -> confirmButtonElement.isDisplayed());

            return confirmButtonElement;
        }
    }
}