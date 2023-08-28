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

    @FindBy(xpath = "//*[@id=\"shipping-new-address-form\"]/div[1]")
    public static WebElement firstNameTextBox;

    @FindBy(id = "MS95DOK")
    public static WebElement lastNameTextBox;

    @FindBy(id = "CS007H1")
    public static WebElement companyTextBox;

    @FindBy(id = "H79NKLA")
    public static WebElement address1TextBox;

    @FindBy(id = "HRRC00N")
    public static WebElement address2TextBox;

    @FindBy(id = "MLDCOYV")
    public static WebElement address3TextBox;

    @FindBy(id = "TPIK8O0")
    public static WebElement cityTextBox;

    @FindBy(id = "HGBAGUO")
    public static WebElement stateDropdown;

    @FindBy(id = "GCHUT34")
    public static WebElement zipTextBox;

    @FindBy(id = "GSV6W3A")
    public static WebElement countryDropdown;

    @FindBy(id = "KDTYRX0")
    public static WebElement phoneTextBox;

    @FindBy(xpath = "//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr[1]/td[1]/input")
    public static WebElement fixedRadioButton;

    @FindBy(xpath = "//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr[2]/td[1]/input")
    public static WebElement tableRateRadioButton;

    @FindBy(xpath = "//*[@id=\"shipping-method-buttons-container\"]/div/button")
    public static WebElement nextButton;

}