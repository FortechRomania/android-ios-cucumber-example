package glue.steps;

import cucumber.api.java8.En;
import fixture.persistence.ScenarioState;
import pages.AcquaintancesPage;
import utils.ExpectedConditionWrapper;
import utils.Helper;
import utils.AppiumManager;

import static org.junit.Assert.assertFalse;

public class AcquaintancesSteps implements En {

    public AcquaintancesSteps(ScenarioState scenarioState) {
        Given("I tap on the add acquaintance button", () -> {
            AcquaintancesPage acquaintancesPage = new AcquaintancesPage(AppiumManager.getInstance().getDriver());

            acquaintancesPage.waitUntilPageLoads();
            acquaintancesPage.tapOnAddAcquaintanceButton();
        });

        Given("I tap on the new acquaintance", () -> {
            AcquaintancesPage addAcquaintancePage = new AcquaintancesPage(AppiumManager.getInstance().getDriver());
            String formattedAcquaintanceName = String.format("%s, %s", scenarioState.getLastName(), scenarioState.getFirstName());

            Helper.scrollDownFullScreenUntilCondition(ExpectedConditionWrapper.condition(webElement ->
                    addAcquaintancePage.isAcquaintanceWithNameDisplayed(formattedAcquaintanceName)));
            int indexOfAddedAcquaintance = addAcquaintancePage.getAcquaintancesNames().indexOf(formattedAcquaintanceName);

            addAcquaintancePage.tapOnAcquaintanceAtIndex(indexOfAddedAcquaintance);
        });

        Then("I should see the new acquaintance in the list", () -> {
            AcquaintancesPage addAcquaintancePage = new AcquaintancesPage(AppiumManager.getInstance().getDriver());
            String formattedAcquaintanceName = String.format("%s, %s", scenarioState.getLastName(), scenarioState.getFirstName());

            addAcquaintancePage.waitUntilListIsNotEmpty();
            Helper.scrollDownFullScreenUntilCondition(ExpectedConditionWrapper.condition(webElement ->
                    addAcquaintancePage.getAcquaintancesNames().contains(formattedAcquaintanceName)));
        });

        Then("I should see that the acquaintance was deleted", () -> {
            AcquaintancesPage addAcquaintancePage = new AcquaintancesPage(AppiumManager.getInstance().getDriver());
            String formattedAcquaintanceName = String.format("%s, %s", scenarioState.getLastName(), scenarioState.getFirstName());

            addAcquaintancePage.waitUntilListIsNotEmpty();
            assertFalse("Acquaintance was not deleted", addAcquaintancePage.getAcquaintancesNames().contains(formattedAcquaintanceName));
        });
    }
}