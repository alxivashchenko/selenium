import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test6 {

    @Test
    public void Test1() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://cactus.kh.ua/");
        WebElement webElement =  (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("din_id")));

       // WebElement webElement2 =  (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElement(By.id("din_id")));



    }


}
