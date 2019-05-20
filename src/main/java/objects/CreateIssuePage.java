package objects;

import elements.ClassNameElement;
import elements.IdElement;
import org.openqa.selenium.WebDriver;

public class CreateIssuePage {
    private IdElement summary;
    private IdElement description;
    private ClassNameElement button;

    public CreateIssuePage(WebDriver driver) {
        driver.navigate().to("http://localhost:8080/dashboard#newissue=yes");
        summary = new IdElement(driver, "id_l.D.ni.ei.eit.summary");
        description = new IdElement(driver, "id_l.D.ni.ei.eit.description");
        button = new ClassNameElement(driver, "submit-btn");
    }

    public void createIssue(String summary, String description) {
        this.summary.setText(summary);
        this.description.setText(description);
        button.click();
    }
}