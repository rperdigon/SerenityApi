package starter.tasks;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.targets.Target;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebElement;
import starter.pages.SDFactory;

import java.util.List;

public class ElementVisibilityVerifier {
    public static void viewAllTextDivsClass() {
        List<WebElement> listAllTextDivsClass = Serenity.getDriver().findElements(SDFactory.currentPage.getSelector("allTextDivsClass"));
        WaitInteractions.waitListElementsVisible(listAllTextDivsClass);
        listAllTextDivsClass.forEach(webElement -> {
            Serenity.recordReportData().withTitle("Text in div").andContents(webElement.getText());
        });
        listAllTextDivsClass.forEach(webElement -> {
            OnStage.theActorInTheSpotlight().should(
                    "check div is visible: " + webElement.getText(),
                    GivenWhenThen.seeThat(
                            Visibility.of(SDFactory.getWebElementSelector(webElement))
                    ).orComplainWith(AssertionError.class, "Element is not visible: " + webElement.getText())
            );
        });
    }
}
