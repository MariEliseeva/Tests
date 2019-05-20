package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssuesPage {
    private Map<String, WebElement> linkElements = new HashMap<>();

    public IssuesPage(WebDriver driver) {
        driver.navigate().to("http://localhost:8080/issues");
        List<WebElement> issues = driver.findElements(By.className("issueIdAnchor"));

        for (WebElement issue : issues) {
            linkElements.put(issue.getText(), issue);
        }
    }

    public void go(String id) {
        linkElements.get(id).click();
    }

    public boolean check(String id, String summary) {
        if (!linkElements.containsKey(id)) {
            return false;
        }
        return summary.equals(linkElements.get(id).findElement(By.xpath("./..//a[@class='issue-summary']")).getText());
    }
}
