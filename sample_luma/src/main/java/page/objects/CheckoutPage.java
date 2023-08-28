package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    public static void initializePageObjects(WebDriver drvr) {
        PageFactory.initElements(drvr, CheckoutPage.class);
    }

    @FindBy(xpath = "//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button")
    public static WebElement placeOrderButton;


    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[1]/h1/span")
    public static WebElement confirmationText;


}
