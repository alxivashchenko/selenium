package pageObjectTect;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResultPage {
    WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    private By counterOfMatches = By.xpath("//span[@class='heading-counter']");
    private By searchResultWord = By.xpath("//span[@class='lighter']");
    private By producerNameXiaomi = By.xpath("(//span[@itemtype='brand']//*[contains(text(),'Xiaomi')])[1]");
    private By searchGroupProductLink = By.xpath("//div[@class = 'products-list']/div");
    private By brandLink = By.xpath("//span[@class = 'brand']/b");
    private By searchGroupPriceLink = By.xpath("//span[@class='price product-price']");
    private By producerNameImac = By.xpath("(//p[@id='product_reference']//strong)[2]");
    private By phoneName1Link = By.xpath("(//a[@class='product-name listgrid'])[1]");
    private By phonePrice1Link = By.xpath("(//span[@class='price product-price'])[1]");
    private By buttonBuyLink = By.xpath("(//a[@class='le-button ajax_add_to_cart_button'])[1]");
    private By basketButtonLink = By.xpath("//div[@class='basket-item-count']");
    private By phoneNameFromBasketLink = By.xpath("//a[@class='cart_block_product_name']");
    private By phonePriceFromBasketLink = By.xpath("//span[@class='price']");


    public BasketPage buyPhone() {
        driver.findElement(buttonBuyLink).click();
        return new BasketPage(driver);
    }

    public SoftAssert checkBasket(SoftAssert asert) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(basketButtonLink).click();
        String phoneNameFromBasket = driver.findElement(phoneNameFromBasketLink).getText();
        System.out.println(phoneNameFromBasket);
        String phonePriceFromBasket = driver.findElement(phonePriceFromBasketLink).getText();
        System.out.println(phonePriceFromBasket);
        String phoneName1 = driver.findElement(phoneName1Link).getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='product-name listgrid'])[1]")));
        String phonePrice1 = driver.findElement(phonePrice1Link).getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='price product-price'])[1]")));
        System.out.println(phoneName1 + " " + phonePrice1);
        System.out.println(phoneNameFromBasket + " " + phonePriceFromBasket);
        asert.assertEquals(phonePrice1, phonePriceFromBasket, "Wrong item in basket");
        return asert;
    }


    public int getNumberOfGoods() {
        String patternStr = "(\\d)*";
        Pattern pattern = Pattern.compile(patternStr);

        int number = 0;
        String strResult = driver.findElement(counterOfMatches).getText();
        String[] words = strResult.split(" ");

        for (String word : words) {
            Matcher matcher = pattern.matcher(word);
            if (matcher.matches()) {
                number = Integer.parseInt(word);
                break;
            }
        }
        System.out.println(number);
        return number;
    }

    public String getSearchWord() {
        return driver.findElement(searchResultWord).getText();
    }

    public String getProducerNameXiaomi() {
        return driver.findElement(producerNameXiaomi).getText();
    }

    public String getProducerNameImac() {
        return driver.findElement(producerNameImac).getText();
    }

    public SoftAssert checkSearchGroup(String searchWord, SoftAssert asert) {
        List<WebElement> searchGroup = driver.findElements(searchGroupProductLink);

        for (WebElement element : searchGroup) {
            WebElement brand = driver.findElement(brandLink);
            System.out.println(brand.getText());
            asert.assertEquals(brand.getText(), searchWord, "Incorrect producer = " + brand.getText());
        }
        System.out.println(searchGroup.size());
        return asert;
    }

    public boolean checkSortByPrice() {
        List<WebElement> searchGroupPrice = driver.findElements(searchGroupPriceLink);
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
        return max;
    }


}
