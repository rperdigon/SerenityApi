package starter.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import starter.tasks.*;

public class GenericStepDef{
    //Actor for scenario
    String actor = "user not logged";

    /**
     * Sets up the stage before each scenario.
     * This method is annotated with @Before, which means it will be executed before each scenario.
     */
    @Before
    public void setTheStage() {
        // Create a new OnlineCast instance and set it as the current stage
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("go to web {string}")
    public void goToWeb(String url) {
        NavigateTo.theWebSite(url, actor);
    }

    @Then("check to {string} has loaded")
    public void checkToHasLoaded(String page) {
        IsLoad.isLoadPage(page +"page");
    }

    @When("click in {string}")
    public void clickIn(String element) {
        ElementInteraction.clickOnTarget(element);
    }

    @When("send text {string} to element {string}")
    public void sendTextToElement(String text, String element) {
        SendTextTo.input(text,element);
    }

    @When("click {string} on item in the list {string} whose title is {string}")
    public void clickOnItemInTheListWhoseTitleIs(String elementClick, String listElement, String titleElement) {
        ElementInteraction.clickOnElementInListWithTitleContaining(elementClick,listElement, titleElement);
    }

    @Then("should see text and view all text divs classes")
    public void should_see_information_about() {
        ElementVisibilityVerifier.viewAllTextDivsClass();
    }
}
