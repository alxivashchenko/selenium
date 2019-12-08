import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TestActions1 {


           @Test
        public void testAct1() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://cactus.kh.ua");
        WebElement element = driver.findElement(By.
                id("search_query_block"));


        Actions action = new Actions(driver);
        action
                .click()
                .click()
                .clickAndHold()
                .doubleClick()
                .build()
                .perform();





    }


}
