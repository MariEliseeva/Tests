package objects;

import elements.ClassNameElement;
import elements.IdElement;
import org.openqa.selenium.WebDriver;

public class SetUpPage {
    private ClassNameElement checkLicense;
    private IdElement passwordField1;
    private IdElement passwordField2;
    private ClassNameElement save;

    public SetUpPage(WebDriver driver) {
        driver.navigate().to("http://localhost:8080/");
        checkLicense = new ClassNameElement(driver, "acceptLicense");
        passwordField1 = new IdElement(driver, "id_l.S.SetupContent.rootPwd.rootPassword2");
        passwordField2 = new IdElement(driver, "id_l.S.SetupContent.rootPwd.confirmRootPassword");
        save = new ClassNameElement(driver, "submit-btn");
    }

    public void run() {
        checkLicense.click();
        passwordField1.setText("1");
        passwordField2.setText("1");
        save.click();
    }
}
