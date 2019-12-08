import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Test3 {


    @Test
    public void Test1() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
       // WebElement element = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
        WebElement element = driver.findElement(By.className("gb_P"));
       // element.sendKeys("java");
       // element.submit();
        element.click();
        System.out.println(driver.getTitle());



    }


}
