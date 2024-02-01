package starter.pages;

import org.openqa.selenium.By;

import java.util.Map;

public class BcncgroupWhoWeArePage extends AbstractPage {
    @Override
    public Map<String, By> mapSelectors() {
        mapSelectors.put("WHO WE AREpage", By.tagName("h1"));
        mapSelectors.put("allTextDivsClass", By.cssSelector("div.text"));
        return mapSelectors;
    }

}
