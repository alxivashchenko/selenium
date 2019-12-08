package exam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.util.concurrent.TimeUnit;

public class ExamTest {

    private WebDriver driver;
    SoftAssert asert;
    private MainPage mainPage;


    @BeforeMethod
    public void startTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       // driver.manage().window().maximize();
        driver.get("https://www.ukr.net/");
       // mainPage = new MainPage(driver);
        asert = new SoftAssert();
    }

    @AfterMethod
    public void finishTest() {
        driver.quit();
    }


    @Test(groups = "positive", description = "Checks USD rate NBU")
    public void testUsdCurrencyPresence() throws InterruptedException {
       // asert = new SoftAssert();
         WebElement currencyTab = driver.findElement(By.
                 xpath("//li[@data-tab='currency']"));
        currencyTab.click();
        WebElement rateUsdNbu = driver.findElement(By.
                xpath("//td[text()='USD']/following-sibling::td[@class='nbu']"));
        float usdRate = Float.parseFloat(rateUsdNbu.getText());
        Thread.sleep(2000);
        asert.assertTrue(usdRate > 0, "USD should be more than 0, but = " + usdRate);
        asert.assertAll();
    }

    @Test(groups = "positive", description = "Checks that politics page is opened")
    public void politicsPageIsOpened() {
        //asert = new SoftAssert();
        WebElement langField = driver.findElement(By.
                xpath("//span[@class='act-lang']"));
        String lang = langField.getText();
        WebElement politicsSection = driver.findElement(By.
                xpath("//*[@class='feed__section--title']//a[contains(@href, 'politika')]"));
        politicsSection.click();

        WebElement politicsField;
        if (lang.equals("Українською")) {
            politicsField = driver.findElement(By.
                    xpath("//div[@id='update-feed']/following-sibling::h2[contains(text(), 'Політичні')]"));
        } else {
            politicsField = driver.findElement(By.
                    xpath("//div[@id='update-feed']/following-sibling::h2[contains(text(), 'Политические')]"));
        }
        asert.assertTrue(politicsField.isDisplayed(), "Politics page is not displayed");
        asert.assertAll();
    }

    @DataProvider(parallel = false)
    public Object[][] getData() {
        return new Object[][] {
                {"Політичні новини країни", "https://www.ukr.net/news/politika.html"},

        };
    }

    @Test(groups = "positive", dataProvider = "getData", description = "Checks that politics page is opened")
    public void politicsPageIsOpened2(String expectedName, String expectedUrl) {
        //asert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement politicsSection = driver.findElement(By.
                xpath("//*[@class='feed__section--title']//a[contains(@href, 'politika')]"));
        politicsSection.click();

        System.out.println(driver.getCurrentUrl());
        WebElement politicsField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//div[@id='update-feed']/following-sibling::h2[contains(text(), 'Політичні')]")));
        String currentName = politicsField.getText();
        String currentUrl = driver.getCurrentUrl();

        asert.assertEquals(currentName, expectedName, "Politics page is not displayed");
        asert.assertEquals(currentUrl, expectedUrl, "Politics page is not displayed");
        asert.assertAll();
    }

    @Test(groups = "positive", description = "Checks shift from UA to RU")
    public void shiftFromUaToRu() {
        //asert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement langField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//span[@class='act-lang']")));
        langField.click();

        WebElement ruLangField =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//a[contains(text(), 'на Русском')]")));
        ruLangField.click();
        langField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//span[@class='act-lang']")));
        System.out.println(langField.getText());
        asert.assertEquals(langField.getText(), "на Русском", "Doesn't shift language");
        asert.assertAll();
    }


}
