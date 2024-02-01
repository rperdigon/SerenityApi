package starter.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import starter.pages.SDFactory;

public class SendTextTo{
    public static void input(String text,String element) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Clear.field(SDFactory.currentPage.getSelector(element)),
                Enter.theValue(text).into(SDFactory.currentPage.getSelector(element))
        );
    }
}
