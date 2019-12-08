package home1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test21 {

    @Test(enabled = false)
    public void checkSearch() {


        WebDriver driver = new ChromeDriver();
        driver.get("https://cactus.kh.ua");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//input[@class='search_query form-control grey ac_input']")));
        WebElement searchInput = driver.findElement(By.
                xpath("//input[@class='search_query form-control grey ac_input']"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//button[@id='search_button']")));
        WebElement searchButton = driver.findElement(By.
                xpath("//button[@id='search_button']"));

        searchInput.sendKeys("Apple");

        searchButton.submit();

       /* wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.
                xpath("//button[@id='search_button']"))));*/

        WebElement iPhone = driver.findElement(By.
                xpath("//h5//a[@title='Apple iPhone']"));

        Assert.assertTrue(iPhone.isDisplayed());
        // driver.quit();




    }


}
