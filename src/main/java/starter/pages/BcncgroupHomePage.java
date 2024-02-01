package starter.pages;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class BcncgroupHomePage extends AbstractPage {
    @Override
    public Map<String, By> mapSelectors() {
        mapSelectors.put("HOMEpage", By.tagName("h1"));
        mapSelectors.put("Accept", By.linkText("Accept"));
        mapSelectors.put("allTextDivsClass", By.cssSelector("div.text"));
        mapSelectors.put("WHO WE ARE", By.cssSelector("a[href='https://bcncgroup.com/who-we-are/']"));
        return mapSelectors;
    }

}
