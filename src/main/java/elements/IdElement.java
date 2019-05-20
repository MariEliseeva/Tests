package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IdElement {
    private WebElement element;

    public IdElement(WebDriver driver, String id) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    public void setText(String text) {
        element.clear();
        element.sendKeys(text);
    }

    public String getText() {
        return element.getText();
    }
}
