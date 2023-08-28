package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.NumberHelper;

import java.util.List;

import static ui.selenium.Driver.driver;

public class ItemPage {

    public static void initializePageObjects(WebDriver drvr) {
        PageFactory.initElements(drvr, ItemPage.class);
    }

    @FindBy(className = "logo")
    public static WebElement logo;

    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[1]/div[2]/div/div")
    public static WebElement itemAddedMessage;

    @FindBy(className = "minicart-wrapper")
    public static WebElement cartButton;

    @FindBy(id = "ui-id-1")
    public static WebElement cartDropdown;

    @FindBy(id = "top-cart-btn-checkout")
    public static WebElement checkoutButton;

    @FindBy(id = "product-addtocart-button")
    public static WebElement addToCartButton;

    public static void selectSize() {
        List<WebElement> size = driver.findElements(By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[1]/div/div"));
        int i = NumberHelper.generateRandomInteger(size.size());
        size.get(i).click();
    }

    public static void selectColor() {

        List<WebElement> color = driver.findElements(By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[2]/div/div"));
        int i = NumberHelper.generateRandomInteger(color.size());
        color.get(i).click();
    }

}