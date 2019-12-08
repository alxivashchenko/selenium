package pageObjectTect;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By searchInput = By.xpath("//*[@id='search_query_block']");
    private By searchButton = By.xpath("//button[@id='search_button']");
    private By linkSmartPhoneXiaomi = By.xpath("//div[@id='sexytopmenu']//a[contains(@title,'Xiaomi')]");
    private By searchDropDown = By.xpath("//div[@class='ac_results']//li[1]//strong");

    public SearchResultPage searchQuery(String requestWord){
        driver.findElement(searchInput).sendKeys(requestWord);
        driver.findElement(searchButton).submit();
        return new SearchResultPage(driver);
    }

    public SearchResultPage clickXiaomiLink(){
        driver.findElement(linkSmartPhoneXiaomi).click();
        return new SearchResultPage(driver);
    }

    public String getSearchWord() {
        return driver.findElement(searchDropDown).getText();
    }

    public SearchResultPage clickDropDownImac(){
        driver.findElement(searchDropDown).click();
        return new SearchResultPage(driver);
    }

    public MainPage searchQueryDropDown(String requestWord){
        driver.findElement(searchInput).sendKeys(requestWord);
        return this;
    }

}
