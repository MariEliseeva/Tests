package objects;

import elements.ClassNameElement;
import elements.IdElement;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private IdElement login;
    private IdElement password;
    private ClassNameElement button;
    public LoginPage(WebDriver driver) {
        driver.navigate().to("http://localhost:8080/login");
        login = new IdElement(driver, "id_l.L.login");
        password = new IdElement(driver, "id_l.L.password");
        button = new ClassNameElement(driver, "sb-border3");
    }

    public void login() {
        login.setText("root");
        password.setText("1");
        button.click();
    }
}