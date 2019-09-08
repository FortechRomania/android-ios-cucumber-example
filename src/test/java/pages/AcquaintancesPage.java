package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Helper;

import java.util.List;
import java.util.stream.Collectors;

public class AcquaintancesPage {

    @AndroidFindBy(id = "acquaintanceListFloatingActionButton")
    @iOSXCUITFindBy(accessibility = "Add")
    private MobileElement addAcquaintanceButtonElement;

    @AndroidFindBy(id = "nameTextView")
    @iOSXCUITFindBy(accessibility = "NameLabel")
    private List<MobileElement> acquaintanceElements;

    public AcquaintancesPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public List<String> getAcquaintancesNames() {
        return acquaintanceElements.stream()
                .map(RemoteWebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isAcquaintanceWithNameDisplayed(String formattedAcquaintanceName) {
        List<String> acquaintancesNames = getAcquaintancesNames();

        if (acquaintancesNames.contains(formattedAcquaintanceName))
        {
            return  acquaintanceElements.get(acquaintancesNames.indexOf(formattedAcquaintanceName)).isDisplayed();
        }

        return false;
    }

    public void tapOnAddAcquaintanceButton() {
        addAcquaintanceButtonElement.click();
    }

    public void tapOnAcquaintanceAtIndex(int index) {
        acquaintanceElements.get(index).click();
    }

    public void waitUntilListIsNotEmpty() {
        Helper.waitUntil(webDriver -> !acquaintanceElements.isEmpty());
    }

    public void waitUntilPageLoads() {
        Helper.waitUntil(webDriver -> addAcquaintanceButtonElement.isDisplayed());
    }
}