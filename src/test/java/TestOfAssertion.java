import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestOfAssertion {

    @Test
    public void Test31() {

        WebDriver driver = new ChromeDriver();
        SoftAssert softAssert = new SoftAssert();

        try {

            driver.manage().window().maximize();

            driver.get("https://cactus.kh.ua");

            driver.findElement(By.id("search_query_block")).sendKeys("apple");
            driver.findElement(By.cssSelector(".icon-search")).click();


            WebElement element = driver.findElement(By.cssSelector(".product-listing"));
            softAssert.assertTrue(element.isDisplayed(), "Check element is disp");
            System.out.println("I'm here");
            Assert.assertNotEquals(element.getText(), "Результат поиска  \"apple\"\n" +
                    "85933 совпадений было найдено.", "Check search result text");
            softAssert.assertAll();

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.textToBePresentInElement( driver.findElement(By.cssSelector(".product-listing")),
                    "Результат поиска"));

            System.out.println(element.getText());

            Assert.assertNotEquals(element.getText(), "Результат поиска  \"apple\"\n" +
                    "85933 совпадений было найдено.", "Check search result text");

           System.out.println(element.getAttribute("fgsfdg"));
        }

           finally {
           driver.quit();
        }


    }


    public int sum(int a, int b) {
        return 5;
    }


    @Test
    public  void checkSum() {
        MyObject myObject1 = MyObject.getMyObject();
        MyObject myObject2 = MyObject.getMyObject();

        Assert.assertEquals(myObject1, myObject2, "Check equals of object");
        Assert.assertSame(myObject1, myObject2, "Check equals of object");
    }

    @Test
    public  void checkAssert() {
        Integer[] expected = {1,2,3};
        Integer[] actual = {2,1,3};

        Assert.assertEqualsNoOrder(expected, actual, "Check equals of array ");
    }

}
