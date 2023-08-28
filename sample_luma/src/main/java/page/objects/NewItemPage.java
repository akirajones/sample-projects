package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.NumberHelper;

import java.util.List;

import static ui.selenium.Driver.driver;

public class NewItemPage {

    public static void initializePageObjects(WebDriver drvr) {
        PageFactory.initElements(drvr, NewItemPage.class);
    }

    @FindBy(className = "logo")
    public static WebElement logo;

    @FindBy(id = "page-title-heading")
    public static WebElement title;

    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[4]/div[1]/div[1]/div[3]")
    public static WebElement productsGrid;

    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[4]/div[1]/div[1]/div[3]/div/div/ol/li[1]")
    public static WebElement itemTopLeft;

    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[4]/div[1]/div[1]/div[3]/div/div/ol/li[2]")
    public static WebElement itemTopRight;


    public static void scrollToProductsGrid() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsGrid);
    }

    public static void selectItem() {

        List<WebElement> item = driver.findElements(By.xpath("//*[@id=\"maincontent\"]/div[4]/div[1]/div[1]/div[3]/div/div/ol/li"));
        int i = NumberHelper.generateRandomInteger(item.size());
        item.get(i).click();
    }


    public static String pageTitle = "What's New";



}
