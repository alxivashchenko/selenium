import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test5 {

    @Test
    public void Test1() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://cactus.kh.ua/");

        WebElement search = driver.findElement(By.xpath("//input[@class='search_query form-control grey ac_input']"));
        search.sendKeys("apple");
        driver.findElement(By.xpath("//button[@id=\"search_button\"]")).click();
        System.out.println(driver.findElement(By.xpath("//h1[@class=\"page-heading  product-listing\"]")).isDisplayed());



    }


}
