package glue.steps;

import cucumber.api.java8.En;
import fixture.persistence.ScenarioState;
import pages.AddAcquaintancePage;
import utils.Constants;
import utils.TestBase;

public class AddAcquaintanceSteps implements En {

    public AddAcquaintanceSteps(ScenarioState scenarioState) {
        Given("I fill in the required fields and tap on the Save button", () ->
                fillInRequiredFieldsAndTapSave(scenarioState, Constants.FIRST_NAME, Constants.LAST_NAME));

        And("I set the first name to: {string} and last name to: {string} and tap the Save button", (String firstName, String lastName) ->
                fillInRequiredFieldsAndTapSave(scenarioState, firstName, lastName));
    }

    private void fillInRequiredFieldsAndTapSave(ScenarioState scenarioState, String firstName, String lastName) {
        AddAcquaintancePage addAcquaintancePage = new AddAcquaintancePage(TestBase.getInstance().getDriver());

        scenarioState.setFirstName(firstName);
        scenarioState.setLastName(lastName);

        addAcquaintancePage.waitUntilPageLoads();
        addAcquaintancePage.fillInRequiredFields(firstName, lastName);
        addAcquaintancePage.tapOnSaveButton();
    }
}