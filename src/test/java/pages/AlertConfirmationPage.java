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

public class AlertConfirmationPage {

    // region Identifiers
    private static final String ANDROID_CONFIRM_BUTTON = "button1";
    private static final String IOS_DELETE_BUTTONS = "Delete";
    // endregion

    @AndroidFindBy(id = ANDROID_CONFIRM_BUTTON)
    private MobileElement confirmButtonElement;

    @iOSFindBy(id = IOS_DELETE_BUTTONS)
    private List<MobileElement> deleteButtonElements;

    public AlertConfirmationPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void tapOnConfirmButton() {
        getConfirmButtonElement().click();
    }

    private MobileElement getConfirmButtonElement() {
        if (Platform.isOnIOS()) {
            Helper.waitUntil(webDriver -> deleteButtonElements.size() > 1,
                    "Confirm button alert is not displayed", 60);

            return deleteButtonElements.get(1);
        } else {
            Helper.waitUntil(webDriver -> confirmButtonElement.isDisplayed(),
                    "Confirm button alert is not displayed", 60);

            return confirmButtonElement;
        }
    }
}