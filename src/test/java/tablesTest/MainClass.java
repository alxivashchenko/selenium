package tablesTest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MainClass {

    static WebDriver driver;


    public static void main(String[] args) {

        //System.setProperty("webdriver.gecko.driver", "C:\\Users\\student\\IdeaProjects\\testselenium\\drivers\\geckodriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       // driver.manage().window().maximize();

        driver.get("http://www.w3schools.com/html/html_tables.asp");

        WebElement tableElement = driver.findElement(By.xpath("//table[@id=\"customers\"]"));

        Table table = new Table(tableElement, driver);

        System.out.println("Rows number is: " + table.getRows().size());

       // System.out.println(table.getValueFromCell(2, 3));
       // System.out.println(table.getValueFromCell(4, 1));

       // System.out.println(table.getValueFromCell(4, "Company"));
       // System.out.println(table.getValueFromCell(1, "Country"));
        //System.out.println(table.getValueFromCell(2, "Contact"));
        //
        System.out.println(table.getRows());
        System.out.println(table.getHeadings());
        System.out.println(table.getRowsWithColumnsByHeadings());
        System.out.println(table.getValueFromCell(1, 2));
        System.out.println(table.getValueFromCell(1, "Company"));










        driver.quit();

    }
}
