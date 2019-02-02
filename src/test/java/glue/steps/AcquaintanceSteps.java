package glue.steps;

import cucumber.api.java8.En;
import fixture.persistence.ScenarioState;
import pages.AcquaintancePage;
import utils.ExpectedConditionWrapper;
import utils.Helper;
import utils.TestBase;

import static org.junit.Assert.assertFalse;

public class AcquaintanceSteps implements En {

    public AcquaintanceSteps(ScenarioState scenarioState) {
        Given("^I tap on the add acquaintance button$", () -> {
            AcquaintancePage acquaintancePage = new AcquaintancePage(TestBase.getInstance().getDriver());

            acquaintancePage.waitUntilPageLoads();
            acquaintancePage.tapOnAddAcquaintanceButton();
        });

        Given("^I tap on the new acquaintance$", () -> {
            AcquaintancePage addAcquaintancePage = new AcquaintancePage(TestBase.getInstance().getDriver());
            String formattedAcquaintanceName = String.format("%s, %s", scenarioState.getLastName(), scenarioState.getFirstName());

            Helper.scrollDownFullScreenUntilCondition(ExpectedConditionWrapper.condition(webElement ->
                    addAcquaintancePage.isAcquaintanceWithNameDisplayed(formattedAcquaintanceName)));
            int indexOfAddedAcquaintance = addAcquaintancePage.getAcquaintancesNames().indexOf(formattedAcquaintanceName);

            addAcquaintancePage.tapOnAcquaintanceAtIndex(indexOfAddedAcquaintance);
        });

        Then("^I should see the new acquaintance in the list$", () -> {
            AcquaintancePage addAcquaintancePage = new AcquaintancePage(TestBase.getInstance().getDriver());
            String formattedAcquaintanceName = String.format("%s, %s", scenarioState.getLastName(), scenarioState.getFirstName());

            addAcquaintancePage.waitUntilListIsNotEmpty();
            Helper.scrollDownFullScreenUntilCondition(ExpectedConditionWrapper.condition(webElement ->
                    addAcquaintancePage.getAcquaintancesNames().contains(formattedAcquaintanceName)));
        });

        Then("^I should see that the acquaintance was deleted$", () -> {
            AcquaintancePage addAcquaintancePage = new AcquaintancePage(TestBase.getInstance().getDriver());
            String formattedAcquaintanceName = String.format("%s, %s", scenarioState.getLastName(), scenarioState.getFirstName());

            addAcquaintancePage.waitUntilListIsNotEmpty();
            assertFalse("Acquaintance was not deleted", addAcquaintancePage.getAcquaintancesNames().contains(formattedAcquaintanceName));
        });
    }
}