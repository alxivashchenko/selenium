
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCactus {
    WebDriver driver;
    SoftAssert asert;

    @BeforeMethod
    public void startTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // driver.manage().window().maximize();
        driver.get("https://c.ua/");

    }

    @AfterMethod
    public void finishTest() {
        driver.quit();
    }

    @Test(groups = "positive", description = "Checks presence search query in search result")
    public void testSearchPassByNumber() {
        asert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        WebElement searchInput = driver.findElement(By.
                xpath("//input[@autocomplete='off']"));    //   //*[@id='search_query_block']  xPath
        wait.until(ExpectedConditions.visibilityOf(searchInput));

        System.out.println(searchInput.isDisplayed());
        WebElement searchButton = driver.findElement(By.
                xpath("//input[@autocomplete='off']//following-sibling::button"));  //#search_button   css

        System.out.println(searchButton.isDisplayed());

        searchInput.sendKeys("samsung");

        searchButton.submit();

        WebElement counter = driver.findElement(By.
                xpath("//span[@class='heading-counter']"));

        String patternStr = "(\\d)*";
        Pattern pattern = Pattern.compile(patternStr);

        int number = 0;
        String strResult = counter.getText();
        String[] words = strResult.split(" ");

        for (String word : words) {
            Matcher matcher = pattern.matcher(word);
            if (matcher.matches()) {
                number = Integer.parseInt(word);
                break;
            }
        }

        String patternStr2 = "(\\D)*";
        Pattern pattern2 = Pattern.compile(patternStr2);
        String strText = "";

        for (String word : words) {
            Matcher matcher2 = pattern2.matcher(word);
            if (matcher2.matches()) {
                strText = strText + " " + word;
            }
        }

        System.out.println(number);
        System.out.println(strText);
        asert.assertNotEquals(0, number, "Number of goods should be more than 0, but = " + number);
        asert.assertEquals(strText.trim(), "совпадений было найдено.", "Text query is wrong");
        asert.assertAll();

    }


    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @DataProvider(parallel = false)
    public Object[][] getData() {
        return new Object[][]{
                {"samsung"},
                {"apple"},
                {"xiaomi"},
                {"huawei"},
                {"nokia"}
        };
    }


    @Test(dataProvider = "getData", groups = "positive",
            description = "Checks search query by different producers")
    public void testSearchPassByName(String searchWord) {
        asert = new SoftAssert();
        WebElement searchInput = driver.findElement(By.
                xpath("//*[@id='search_query_block'] "));

        WebElement searchButton = driver.findElement(By.
                xpath("//button[@id='search_button']"));

        searchInput.sendKeys(searchWord);

        searchButton.submit();

        WebElement searchWordElement = driver.findElement(By.
                xpath("//span[@class='lighter']"));
        String searchActual = searchWordElement.getText();
        System.out.println(searchActual);

        asert.assertEquals("\"" + searchWord + "\"", searchActual, "Wrong search query = " + searchWord);
        asert.assertAll();
    }

    @Test(groups = "negative",
            description = "Checks incorrect search query with special symbols")
    public void testSearchNegativeSpecSymbol() {
        asert = new SoftAssert();
        WebElement searchInput = driver.findElement(By.
                cssSelector("#search_query_block"));    //   //*[@id='search_query_block']  xPath

        WebElement searchButton = driver.findElement(By.
                xpath("//button[@id='search_button']"));  //#search_button   css

        searchInput.sendKeys("%^$%^_+:\" <>:;~!@g");

        searchButton.submit();

        WebElement counter = driver.findElement(By.
                xpath("//span[@class='heading-counter']"));

        String patternStr = "(\\d)*";
        Pattern pattern = Pattern.compile(patternStr);

        int number = 0;
        String strResult = counter.getText();
        String[] words = strResult.split(" ");

        for (String word : words) {
            Matcher matcher = pattern.matcher(word);
            if (matcher.matches()) {
                number = Integer.parseInt(word);
                break;
            }
        }

        System.out.println(number);
        asert.assertEquals(0, number, "Wrong search spec symbol");
        asert.assertAll();
    }

    @Test(groups = "negative",
            description = "Checks incorrect search query with tag")
    public void testSearchNegativeTag() {
        asert = new SoftAssert();
        WebElement searchInput = driver.findElement(By.
                cssSelector("#search_query_block"));    //   //*[@id='search_query_block']  xPath

        WebElement searchButton = driver.findElement(By.
                xpath("//button[@id='search_button']"));  //#search_button   css

        searchInput.sendKeys("<button>a</button>");

        searchButton.submit();

        WebElement counter = driver.findElement(By.
                xpath("//span[@class='heading-counter']"));

        int number = 0;
        String strResult = counter.getText();
        String[] words = strResult.split(" ");
        for (String word : words) {
            if (isDigit(word)) {
                number = Integer.parseInt(word);
                break;
            }
        }
        System.out.println(number);
        asert.assertEquals(0, number, "Wrong search tag");
        asert.assertAll();
    }

    @Test(groups = "positive",
            description = "Checks correct search by link")
    public void testSearchLinkXiaomi() {
        asert = new SoftAssert();
        WebElement link = driver.findElement(By.
                xpath("//div[@id='sexytopmenu']//a[contains(@title,'Xiaomi')]"));
        link.click();

        WebElement result = driver.findElement(By.
                xpath("(//span[@itemtype='brand']//*[contains(text(),'Xiaomi')])[1]"));

        String searchWord = result.getText();
        System.out.println(searchWord);
        asert.assertEquals("Xiaomi", searchWord, "Link doesn't contain search word");
        asert.assertAll();
    }

    @Test(groups = "positive",
            description = "Checks correct search result in dropdown")
    public void testSearchDropDown() {
        asert = new SoftAssert();
        WebElement searchInput = driver.findElement(By.
                cssSelector("#search_query_block"));    //   //*[@id='search_query_block']  xPath

        searchInput.sendKeys("apple");

        WebElement searchDropDown = driver.findElement(By.
                xpath("//div[@class='ac_results']//li[1]//strong"));

        String searchWord = searchDropDown.getText();
        System.out.println(searchWord);
        asert.assertEquals("Apple", searchWord, "Dropdown doesn't contain search word");
        asert.assertAll();
    }


    @Test(groups = "positive",
            description = "Checks correct choice in dropdown")
    public void testSearchDropDownChoice() {
        asert = new SoftAssert();
        WebElement searchInput = driver.findElement(By.
                cssSelector("#search_query_block"));    //   //*[@id='search_query_block']  xPath

        searchInput.sendKeys("apple");

        WebElement searchDropDown = driver.findElement(By.
                xpath("//div[@class='ac_results']//li[1]//strong"));
        searchDropDown.click();

        WebElement producer = driver.findElement(By.
                xpath("(//p[@id='product_reference']//strong)[2]"));

        String searchWord = producer.getText();
        System.out.println(searchWord);
        asert.assertEquals("Apple", searchWord, "Incorrect producer in dropdown menu");
        asert.assertAll();
    }


    @Test(groups = "positive", description = "Checks correct producer in search group")
    public void testSearchGroup() {
        asert = new SoftAssert();
        WebElement link = driver.findElement(By.
                xpath("//div[@id='sexytopmenu']//a[contains(@title,'Xiaomi')]"));
        link.click();
        String searchWord = "Xiaomi";

        List<WebElement> searchGroupProduct = driver.findElements(By.
                xpath("//div[@class = 'products-list']/div"));
        // boolean isContainSearchWord = false;

        for (WebElement element : searchGroupProduct) {

            WebElement brand = driver.findElement(By.
                    xpath("//span[@class = 'brand']/b"));

            System.out.println(brand.getText());
            asert.assertEquals(brand.getText(), searchWord, "Incorrect producer = " + brand.getText());
        }
        System.out.println(searchGroupProduct.size());
        asert.assertAll();
    }

    @Test(groups = "positive", description = "Checks correct sorting")
    public void testSearchPriceCompare() {
        asert = new SoftAssert();
        WebElement link = driver.findElement(By.
                xpath("//div[@id='sexytopmenu']//a[contains(@title,'Xiaomi')]"));
        link.click();

        List<WebElement> searchGroupPrice = driver.findElements(By.
                xpath("//span[@class='price product-price']"));   //  //div[@class = 'products-list']/div

        List<Integer> priceList = new LinkedList<Integer>();
        for (WebElement element : searchGroupPrice) {
            System.out.println(element.getText());
            int number = 0;
            String priceLine = element.getText();
            String wordResult = "";
            String patternStr = "(\\d)*";
            Pattern pattern = Pattern.compile(patternStr);
            String[] words = priceLine.split(" ");

            for (String word : words) {
                Matcher matcher = pattern.matcher(word);
                if (matcher.matches()) {
                    wordResult = wordResult + word;
                }
            }

            if (!wordResult.equals("")) {
                number = Integer.parseInt(wordResult);
                priceList.add(number);
                System.out.println(number);
            }
        }

        int maxElement = 0;
        boolean max = true;
        for (int i = 0; i < priceList.size(); i++) {
            if ((maxElement <= priceList.get(i))) {
                maxElement = priceList.get(i);
            } else {
                max = false;
                System.out.println(i + "-th element is wrong, price = " + priceList.get(i));
                break;
            }
        }
        System.out.println(max);

        asert.assertTrue(max, "Incorrect sorting");
        asert.assertAll();
    }

    @Test(groups = "positive", description = "Checks add item to basket")
    public void testAddToBasket() {
        asert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement link = driver.findElement(By.
                xpath("//div[@id='sexytopmenu']//a[contains(@title,'Xiaomi')]"));
        link.click();

        WebElement buttonBuy = driver.findElement(By.
                xpath("(//a[@class='le-button ajax_add_to_cart_button'])[1]"));
        buttonBuy.click();
        WebElement continueButton = driver.findElement(By.
                xpath("//span[@class='continue btn btn-default button button-medium']"));
        continueButton.click();
        WebElement basketButton = driver.findElement(By.
                xpath("//div[@class='basket-item-count']"));

        basketButton.click();

        String phoneNameFromBasket = driver.findElement(By.
                xpath("//a[@class='cart_block_product_name']")).getText();
        System.out.println(phoneNameFromBasket);
        String phonePriceFromBasket = driver.findElement(By.
                xpath("//span[@class='price']")).getText();
        System.out.println(phonePriceFromBasket);
        String phoneName1 = driver.findElement(By.
                xpath("(//a[@class='product-name listgrid'])[1]")).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='product-name listgrid'])[1]")));

        String phonePrice1 = driver.findElement(By.
                xpath("(//span[@class='price product-price'])[1]")).getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='price product-price'])[1]")));

        System.out.println(phoneName1 + " " + phonePrice1);
        System.out.println(phoneNameFromBasket + " " + phonePriceFromBasket);
        asert.assertEquals(phonePrice1, phonePriceFromBasket, "Wrong item in basket");
        asert.assertAll();
    }

}
