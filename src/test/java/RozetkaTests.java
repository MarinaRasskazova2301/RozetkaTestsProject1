import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class RozetkaTests {

    @Test
    public void verifyProductInCart() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rozetka.com.ua/");
        driver.manage().window().maximize();

        WebElement searchField = driver.findElement(By.name("search"));
        searchField.sendKeys("MD506Z/A\n");
        // searchField.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'product-trade')]/ul//button[contains(@class,'buy-button')]")));
        WebElement buyButton = driver.findElement(By.xpath("//div[contains(@class,'product-trade')]/ul//button[contains(@class,'buy-button')]"));
        buyButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal__close")));
        WebElement closeButton = driver.findElement(By.className("modal__close"));
        WebElement logoImg = driver.findElement(By.xpath("//img[@alt='Rozetka Logo']"));
        WebElement cartImg = driver.findElement(By.xpath("//rz-cart[@class='header-actions__component']/button"));
        Assert.assertEquals(cartImg.getText(), "1");

        driver.quit();
    }


}
