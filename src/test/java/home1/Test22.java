package home1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test22 {
    @Test
    public void Test22() {
        // System.setProperty("webdriver.chrome.driver", "c://Users//Alex//IdeaProjects//chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://cactus.kh.ua");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        /*
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//input[@class='search_query form-control grey ac_input']")));*/

        WebElement searchInput = driver.findElement(By.
                xpath("//input[@class='search_query form-control grey ac_input']"));
/*
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//button[@id='search_button']")));*/
        WebElement searchButton = driver.findElement(By.
                xpath("//button[@id='search_button']"));

        searchInput.sendKeys("Apple");

        searchButton.submit();

       /* wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.
                xpath("//button[@id='search_button']"))));*/


        WebElement iPhone = driver.findElement(By.
                xpath("//div[@id='subcategories']/ul/li[4]/h5/a"));
        iPhone.click();

        //!!!!!!!!!!!!!!!!!

        WebElement iPhoneX = driver.findElement(By.
                xpath("//div[@id=\"subcategories\"]/ul/div[6]/li/h5/a"));

        iPhoneX.click();

    /*wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.
                xpath("//div[@id=\"subcategories\"]/ul/div[6]/li/h5/a"))));*/

      /*  wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//div[@id=\"product_list\"]/div/div[2]/div/div[4]/div/a")));*/

       WebElement iPhoneX256 = driver.findElement(By.
                xpath("//div[@id=\"product_list\"]/div/div[2]/div/div[4]/div/a"));


        Assert.assertTrue(iPhoneX256.isDisplayed());

        // driver.quit();




    }



}
