package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClassNameElement {
    private WebElement element;

    public ClassNameElement(WebDriver driver, String className) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
    }

    public void click() {
        element.click();
    }

    public String getText() {
        return element.getText();
    }
}