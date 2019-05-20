package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ComboBoxElement {

    public ComboBoxElement(WebDriver driver, String id) {
        driver.findElement(By.id(id)).click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.DOWN).build().perform();
        actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
    }
}
