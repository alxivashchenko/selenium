import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FirstTest {

    @Test
    public void checkSomething() {
        WebDriver driver = new ChromeDriver();



try { driver.get("https://www.ukr.net/");

    System.out.println(driver.findElements(By.cssSelector("#search-input")).size());
    System.out.println(driver.findElements(By.cssSelector("div > input")).size());

//  //div[@class='Name' and contains(@id, 'City')]/span[contains(text(), 'Хар'])
 //   //span[@id='resolution-val' and contains(text(), 'solved')]

} finally {
    driver.quit();
}







    }


}
