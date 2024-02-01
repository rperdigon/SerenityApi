package starter.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPage {
    public Map<String, By> mapSelectors = new HashMap<String, By>();
    public abstract Map<String, By> mapSelectors();
    
    public By getSelector(String selector) {
        return mapSelectors().get(selector);
    }
}
