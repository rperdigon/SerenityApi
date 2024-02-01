package starter.tasks;


import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import starter.pages.SDFactory;


public class IsLoad extends PageObject {
    public static void isLoadPage(String pageName) {
        SDFactory.setCurrentPage(pageName);
        OnStage.theActorInTheSpotlight().should(
                "check that the page is visible: " + pageName,
                GivenWhenThen.seeThat(WebElementQuestion.the(SDFactory.currentPage.getSelector(pageName)),
                        WebElementStateMatchers.isVisible()
                ));
    }
}
