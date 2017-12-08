package glue.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriverException;
import utils.Helper;
import utils.TestBase;

public class Hooks {

    @Before
    public void onScenarioStart(Scenario scenario) {
        TestBase.getInstance().launchAppWithCleanData();
        System.out.println("Scenario: " + scenario.getName() + " started. Time millis: " + System.currentTimeMillis());
    }

    @After
    public void onScenarioEnded(Scenario scenario) {
        System.out.println("Scenario: " + scenario.getName() + " ended. Time millis: " + System.currentTimeMillis());

        if (scenario.isFailed()) {
            try {
                Helper.takeScreenshotForScenario(scenario);
            } catch (WebDriverException exception) {
                exception.printStackTrace();

                TestBase.getInstance().restartService();
            }
        }
    }
}