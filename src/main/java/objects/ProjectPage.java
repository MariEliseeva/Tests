package objects;

import elements.*;
import org.openqa.selenium.WebDriver;

public class ProjectPage {
    private IdElement name;
    private IdElement id;
    private ClassNameElement button;

    public ProjectPage(WebDriver driver) {
        driver.navigate().to("http://localhost:8080/createProject");
        name = new IdElement(driver, "id_l.C.EditProjectMain.projectName");
        id = new IdElement(driver, "id_l.C.EditProjectMain.shortName");
        new ComboBoxElement(driver, "id_l.C.EditProjectMain.projectLead");
        button = new ClassNameElement(driver, "submit-btn");
    }

    public void createProject() {
        this.name.setText("project");
        id.setText("test_id");
        button.click();
    }
}