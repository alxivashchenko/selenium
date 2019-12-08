package home1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TestSearch {

    @DataProvider(parallel = true)
    public Object[][] getData() {
        return new Object[][] {
                {"1 searchRequest", "2"},
                {"2 searchRequest", "2"},
                {"3 searchRequest", "2"}

        };

    }

    @Test
    public void testSearchPass1() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://cactus.kh.ua");

        WebElement searchInput = driver.findElement(By.
                xpath("//input[@class='search_query form-control grey ac_input']"));

        WebElement searchButton = driver.findElement(By.
                xpath("//button[@id='search_button']"));

        searchInput.sendKeys("samsung");

        searchButton.submit();

        WebElement counter = driver.findElement(By.
                xpath("//span[@class='heading-counter']"));

        int number = 0;
        String strResult = counter.getText();
        String[] words = strResult.split(" ");
        for (String word: words) {
            if (isDigit(word)) {
                number = Integer.parseInt(word);
                break;
            }

        }
        System.out.println(number);
        Assert.assertNotEquals(0, number);

        List<WebElement> elements = driver.findElements(By.
                xpath(" //div[@id='subcategories']//ul"));
        System.out.println(elements.size());


    }
    @Test
    public void testSearchPass2() {


        WebDriver driver = new ChromeDriver();
        driver.get("https://cactus.kh.ua");

        WebElement searchInput = driver.findElement(By.
                xpath("//input[@class='search_query form-control grey ac_input']"));

        WebElement searchButton = driver.findElement(By.
                xpath("//button[@id='search_button']"));

        String search = "samsung";
        searchInput.sendKeys(search);

        searchButton.submit();

        WebElement searchWord = driver.findElement(By.
                xpath("//*[@class='page-heading  product-listing']//span[@class='lighter']"));
        String searchActual = searchWord.getText();
        System.out.println(searchActual);

        Assert.assertEquals(searchActual, "\"" + search + "\"");

    }



    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
