package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShippingPage {

    public static void initializePageObjects(WebDriver drvr) {
        PageFactory.initElements(drvr, ShippingPage.class);
    }

    @FindBy(id = "customer-email")
    public static WebElement emailTextBox;

    @FindBy(id = "customer-password")
    public static WebElement passwordTextBox;

    @FindBy(name = "firstname")
    public static WebElement firstNameTextBox;

    @FindBy(name = "lastname")
    public static WebElement lastNameTextBox;

    @FindBy(name = "company")
    public static WebElement companyTextBox;

    @FindBy(name = "street[0]")
    public static WebElement address1TextBox;

    @FindBy(name = "street[1]")
    public static WebElement address2TextBox;

    @FindBy(name = "street[2]")
    public static WebElement address3TextBox;

    @FindBy(name = "city")
    public static WebElement cityTextBox;

    @FindBy(name = "region_id")
    public static WebElement stateDropdown;

    @FindBy(name = "postcode")
    public static WebElement zipTextBox;

    @FindBy(id = "GSV6W3A")
    public static WebElement countryDropdown;

    @FindBy(name = "telephone")
    public static WebElement phoneTextBox;

    @FindBy(xpath = "//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr[1]/td[1]/input")
    public static WebElement fixedRadioButton;

    @FindBy(xpath = "//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr[2]/td[1]/input")
    public static WebElement tableRateRadioButton;

    @FindBy(xpath = "//*[@id=\"shipping-method-buttons-container\"]/div/button")
    public static WebElement nextButton;

}