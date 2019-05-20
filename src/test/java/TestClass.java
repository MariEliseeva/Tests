import objects.*;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class TestClass {
    private static WebDriver driver;
    private static Wait<WebDriver> wait;
    private static final String URL_PREFIX = "http://localhost:8080/";
    private static final String NEW_ISSUE_URL = URL_PREFIX + "dashboard#newissue=yes";

    @BeforeClass
    public static void setup(){
        String pathToChromeDriver = "chromedriver";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 100).ignoring(StaleElementReferenceException.class);

        driver.navigate().to(URL_PREFIX);

        if (driver.getCurrentUrl().equals(URL_PREFIX + "setUp")) {
            new SetUpPage(driver).run();
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(URL_PREFIX + "setUp")));

            new ProjectPage(driver).createProject();
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(URL_PREFIX + "createProject")));
        } else {
            new LoginPage(driver).login();
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(URL_PREFIX + "login")));
        }
    }

    @AfterClass
    public static void teardown(){
        driver.close();
    }

    @Test
    public void testIssuePage(){
        createIssue("summary", "description");
        checkCreated("summary", "description");
    }

    @Test
    public void testIssuePageNotLetters(){
        createIssue("!@#$%^& *()_", "{}[]:;\"'\\|?/,.<>");
        checkCreated("!@#$%^& *()_", "{}[]:;\"'\\|?/,.<>");
    }

    @Test
    public void testIssuePageRussian(){
        createIssue("краткое описание", "описание");
        checkCreated("краткое описание", "описание");
    }

    @Test
    public void testIssuePageChinese(){
        createIssue("摘要", "描述");
        checkCreated("摘要", "描述");
    }

    @Test
    public void testIssuePageNoSummary(){
        createIssue("", "description2");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("error")));
        Assert.assertEquals(NEW_ISSUE_URL, driver.getCurrentUrl());
    }

    @Test
    public void testIssueTwoSameSummaries(){
        createIssue("summary123", "description123");
        checkCreated("summary123", "description123");
        createIssue("summary123", "description11");
        checkCreated("summary123", "description11");
    }


    @Test
    public void testIssueTwoSameDescriptions(){
        createIssue("summary125", "description111");
        checkCreated("summary125", "description111");
        createIssue("summary126", "description111");
        checkCreated("summary126", "description111");
    }

    @Test
    public void testIssuePageNoDescription() {
        createIssue("summary3", "");
        checkCreated("summary3", "No description");
    }

    private void createIssue(String summary, String description) {
        new CreateIssuePage(driver).createIssue(summary, description);
    }

    private void checkCreated(String summary, String description) {
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(NEW_ISSUE_URL)));
        Assert.assertEquals(URL_PREFIX + "issue/test_id-", driver.getCurrentUrl().substring(0, 36));

        String issueId = driver.getCurrentUrl().substring(28);
        assertTrue(new IssuePage(driver, issueId).checkIssue(summary, description));

        IssuesPage issuesPage = new IssuesPage(driver);
        assertTrue(issuesPage.check(issueId, summary));

        issuesPage.go(issueId);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(URL_PREFIX + "issues")));
        Assert.assertEquals(URL_PREFIX + "issue/" + issueId, driver.getCurrentUrl());
    }
}