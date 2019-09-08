package glue.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.cucumber.core.api.Scenario;
import org.openqa.selenium.WebDriverException;
import utils.Helper;
import utils.AppiumManager;

public class Hooks {

    @Before
    public void onScenarioStart(Scenario scenario) {
        AppiumManager.getInstance().launchAppWithFreshInstall();

        System.out.println("Scenario: " + scenario.getName() + " started. Time millis: " + System.currentTimeMillis());
    }

    @After
    public void onScenarioEnded(Scenario scenario) {
        System.out.println("Scenario: " + scenario.getName() + " ended. Time millis: " + System.currentTimeMillis());

        if (scenario.isFailed()) {
            try {
                Helper.takeScreenshotForScenario(scenario);
            } catch (WebDriverException exception) {
                AppiumManager.getInstance().restartService();
            }
        }
    }
}