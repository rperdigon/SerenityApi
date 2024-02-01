package starter.tasks;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import starter.pages.SDFactory;

import java.time.Duration;
import java.util.List;

public class WaitInteractions extends PageObject {

    public static WebElement waitElementIsPresent(WebElement element){
        WebDriverWait wait = new WebDriverWait(Serenity.getDriver(), Duration.ofSeconds(10), Duration.ofSeconds(1));
        return wait.until(ExpectedConditions.presenceOfElementLocated(SDFactory.getWebElementSelector(element)));
    }

    public static WebElement waitElementVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(Serenity.getDriver(), Duration.ofSeconds(10), Duration.ofSeconds(1));
        return wait.until(ExpectedConditions.visibilityOf((element)));
    }

    public static List<WebElement> waitListElementsVisible(List<WebElement> elements){
        WebDriverWait wait = new WebDriverWait(Serenity.getDriver(), Duration.ofSeconds(10), Duration.ofSeconds(1));
        return wait.until(ExpectedConditions.visibilityOfAllElements((elements)));
    }
}
