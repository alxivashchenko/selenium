package pageObjectTect;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage {

    WebDriver driver;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    private By continueButtonLink = By.xpath("//span[@class='continue btn btn-default button button-medium']");

    public SearchResultPage continueBuy() {
        driver.findElement(continueButtonLink).click();
        return new SearchResultPage(driver);
    }


}
