package objects;

import elements.ClassNameElement;
import elements.IdElement;
import org.openqa.selenium.WebDriver;

public class IssuePage {

    private IdElement summary;
    private ClassNameElement description;

    public IssuePage(WebDriver driver, String id) {
        driver.navigate().to("http://localhost:8080/issue/" + id);
        summary = new IdElement(driver, "id_l.I.ic.icr.it.issSum");
        description = new ClassNameElement(driver, "description_fsi");
    }

    public boolean checkIssue(String summary, String description) {
        return this.summary.getText().equals(summary) &&
                this.description.getText().equals(description);
    }
}