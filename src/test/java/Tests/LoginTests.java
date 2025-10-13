package Tests;

import factory.DriverFactory;
import factory.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;

public class LoginTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        String browser = ConfigFactory.get("browser");
        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(ConfigFactory.get("url"));
    }

    @Test
    public void loginTest() throws InterruptedException {
        System.out.println("Page Title: " + driver.getTitle());

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.sendKeys(ConfigFactory.get("username"));

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(ConfigFactory.get("password"));

        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"));
        loginButton.click();

        WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[8]/a/span")));
        Assert.assertTrue(dashboard.isDisplayed(), "Login failed or dashboard not displayed.");
        Thread.sleep(3000);
        System.out.println("Login Doneeeeeeeeeeeeeeeeeeeeeee.");

    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
