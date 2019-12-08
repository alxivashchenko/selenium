import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestActions2 {

    @Test
    public void testAct2() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://dhtmlx.com/docs/products/dhtmlxTree/");
        WebElement source = driver.findElement(By.
                xpath("//div[@id='treebox1']//*[contains(text(), 'Sport')]"));

        WebElement target = driver.findElement(By.
                xpath("//div[@id='treebox2']//*[contains(text(), 'Bestsellers')]"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target)
                .build()
                .perform();

    }



    @Test
    public void testAct3() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://dhtmlx.com/docs/products/dhtmlxTree/");
        WebElement source = driver.findElement(By.
                xpath("//div[@id='treebox1']//*[contains(text(), 'Sport')]"));

        WebElement target = driver.findElement(By.
                xpath("//div[@id='treebox2']//*[contains(text(), 'Bestsellers')]"));



        Actions actions = new Actions(driver);
        actions.clickAndHold(source)
                .pause(2000)
                .moveToElement(target)
                .release()
                .build()
                .perform();

    }

    @Test
    public void testAct4() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://dhtmlx.com/docs/products/dhtmlxTree/");
        WebElement source = driver.findElement(By.
                xpath("//div[@id='treebox1']//*[contains(text(), 'Sport')]"));

        WebElement target = driver.findElement(By.
                xpath("//div[@id='treebox2']//*[contains(text(), 'Bestsellers')]"));

      //  ((JavascriptExecutor)driver).executeScript("alert(222)");
        // ((JavascriptExecutor)driver).executeScript("console.log('Hi!')");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("alert(222)");
      //  jse.executeScript("window.scrollBy(0, 1000)", "");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // jse.executeScript("window.scrollBy(0, -1000)", "");
driver.switchTo().alert().accept();

    }

    @Test
    public void testAct5() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://cactus.kh.ua");

    /*    WebElement searchInput = driver.findElement(By.
                cssSelector("#search_query_block"));    //   //*[@id='search_query_block']  xPath

        WebElement searchButton = driver.findElement(By.
                xpath("//button[@id='search_button']"));  //#search_button   css*/

        WebElement link = driver.findElement(By.
                xpath("//div[@id='sexytopmenu']//a[contains(@title,'Xiaomi')]"));

        Actions actions = new Actions(driver);
        //actions.keyDown("D").build().perform();
        actions.moveToElement(link).build().perform();






              /*  clickAndHold(source)
                .pause(2000)
                .moveToElement(target)
                .release()
                .build()
                .perform();*/

    }

    @Test
    public void testAct6() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://cactus.kh.ua");

        WebElement searchInput = driver.findElement(By.
                cssSelector("#search_query_block"));    //   //*[@id='search_query_block']  xPath

       /* WebElement searchButton = driver.findElement(By.
                xpath("//button[@id='search_button']"));  //#search_button   css*/

        searchInput.sendKeys("samsung");

        searchInput.sendKeys(Keys.ENTER);

    }
}