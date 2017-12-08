package glue.steps;

import cucumber.api.java8.En;
import fixture.persistence.ScenarioState;
import pages.AcquaintanceDetailsPage;
import pages.AcquaintancePage;
import pages.AlertConfirmationPage;
import utils.ExpectedConditionWrapper;
import utils.Helper;
import utils.TestBase;

public class AcquaintanceDetailsSteps implements En {

    public AcquaintanceDetailsSteps(ScenarioState scenarioState) {
        When("^I tap on the delete acquaintance button and confirm my action$", () -> {
            AcquaintanceDetailsPage acquaintanceDetailsPage = new AcquaintanceDetailsPage(TestBase.getInstance().getDriver());
            AlertConfirmationPage alertConfirmationPage = new AlertConfirmationPage(TestBase.getInstance().getDriver());

            acquaintanceDetailsPage.waitUntilPageLoads();
            acquaintanceDetailsPage.tapOnDeleteAcquaintanceButton();
            alertConfirmationPage.tapOnConfirmButton();
        });

        And("^I tap on the edit acquaintance button$", () -> {
            AcquaintanceDetailsPage acquaintanceDetailsPage = new AcquaintanceDetailsPage(TestBase.getInstance().getDriver());

            acquaintanceDetailsPage.waitUntilPageLoads();
            acquaintanceDetailsPage.tapOnEditAcquaintanceButton();
        });

        Then("^I should see that the first name and last name changed$", () -> {
            AcquaintanceDetailsPage acquaintanceDetailsPage = new AcquaintanceDetailsPage(TestBase.getInstance().getDriver());
            AcquaintancePage addAcquaintancePage = new AcquaintancePage(TestBase.getInstance().getDriver());
            String formattedDetailsAcquaintanceName = String.format("%s %s", scenarioState.getFirstName(), scenarioState.getLastName());
            String formattedListingAcquaintanceName = String.format("%s, %s", scenarioState.getLastName(), scenarioState.getFirstName());

            Helper.waitUntil(webDriver -> acquaintanceDetailsPage.getToolbarTitle().contains(formattedDetailsAcquaintanceName), "Acquaintance name has not changed");
            Helper.navigateBack();
            addAcquaintancePage.waitUntilListIsNotEmpty();
            Helper.scrollDownFullScreenUntilCondition(ExpectedConditionWrapper.condition(webElement ->
                    addAcquaintancePage.getAcquaintancesNames().contains(formattedListingAcquaintanceName)));
        });
    }
}