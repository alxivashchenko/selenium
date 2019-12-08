package pageObjectTect;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class CactusSearchTest {

    private WebDriver driver;
    SoftAssert asert;
    private MainPage mainPage;


    @BeforeMethod
    public void startTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //  driver.manage().window().maximize();
        driver.get("https://cactus.kh.ua");
        mainPage = new MainPage(driver);
    }

    @AfterMethod
    public void finishTest() {
        driver.quit();
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


    @Test(groups = "positive", description = "Checks presence search query in search result")
    public void testSearchPassByNumber() {
        asert = new SoftAssert();
        SearchResultPage searchResultPage = mainPage.searchQuery("samsung");
        int number = searchResultPage.getNumberOfGoods();
        asert.assertNotEquals(0, number, "Number of goods should be more than 0, but = " + number);
        asert.assertAll();
    }


    @Test(dataProvider = "getData", groups = "positive",
            description = "Checks search query by different producers")
    public void testSearchPassByName(String searchWord) {
        asert = new SoftAssert();
        SearchResultPage searchResultPage = mainPage.searchQuery(searchWord);
        String searchActual = searchResultPage.getSearchWord();
        asert.assertEquals("\"" + searchWord + "\"", searchActual, "Wrong search query = " + searchWord);
        asert.assertAll();
    }

    @Test(groups = "negative",
            description = "Checks incorrect search query with special symbols")
    public void testSearchNegativeSpecSymbol() {
        asert = new SoftAssert();
        SearchResultPage searchResultPage = mainPage.searchQuery("%^$%^_+:\" <>:;~!@g");
        int number = searchResultPage.getNumberOfGoods();
        asert.assertEquals(0, number, "Wrong search spec symbol");
        asert.assertAll();
    }

    @Test(groups = "negative",
            description = "Checks incorrect search query with tag")
    public void testSearchNegativeTag() {
        asert = new SoftAssert();
        SearchResultPage searchResultPage = mainPage.searchQuery("<button>a</button>");
        int number = searchResultPage.getNumberOfGoods();
        asert.assertEquals(0, number, "Wrong search tag");
        asert.assertAll();
    }

    @Test(groups = "positive",
            description = "Checks correct search by link")
    public void testSearchLinkXiaomi() {
        asert = new SoftAssert();
        SearchResultPage searchResultPage = mainPage.clickXiaomiLink();
        String searchWord = searchResultPage.getProducerNameXiaomi();
        System.out.println(searchWord);
        asert.assertEquals("Xiaomi", searchWord, "Link doesn't contain search word");
        asert.assertAll();
    }

    @Test(groups = "positive",
            description = "Checks correct search result in dropdown")
    public void testSearchDropDown() {
        asert = new SoftAssert();
        mainPage.searchQueryDropDown("apple");
        String searchWord = mainPage.getSearchWord();
        System.out.println(searchWord);
        asert.assertEquals("Apple", searchWord, "Dropdown doesn't contain search word");
        asert.assertAll();
    }

    @Test(groups = "positive",
            description = "Checks correct choice in dropdown")
    public void testSearchDropDownChoice() {
        asert = new SoftAssert();
        mainPage.searchQueryDropDown("apple");
        SearchResultPage searchResultPage = mainPage.clickDropDownImac();
        String searchWord = searchResultPage.getProducerNameImac();
        System.out.println(searchWord);
        asert.assertEquals("Apple", searchWord, "Incorrect producer in dropdown menu");
        asert.assertAll();
    }

    @Test(groups = "positive", description = "Checks correct producer in search group")
    public void testSearchGroup() {
        asert = new SoftAssert();
        SearchResultPage searchResultPage = mainPage.clickXiaomiLink();
        String searchWord = "Xiaomi";
        asert = searchResultPage.checkSearchGroup(searchWord, asert);
        asert.assertAll();
    }

    @Test(groups = "positive", description = "Checks correct sorting")
    public void testSearchPriceCompare() {
        asert = new SoftAssert();
        SearchResultPage searchResultPage = mainPage.clickXiaomiLink();
        asert.assertTrue(searchResultPage.checkSortByPrice(), "Incorrect sorting");
        asert.assertAll();
    }


    @Test(groups = "positive", description = "Checks add item to basket")
    public void testAddToBasket() {
        asert = new SoftAssert();
        SearchResultPage searchResultPage = mainPage.clickXiaomiLink();
        BasketPage basketPage = searchResultPage.buyPhone();
        searchResultPage = basketPage.continueBuy();
        asert = searchResultPage.checkBasket(asert);
        asert.assertAll();
    }


}
