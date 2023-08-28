package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {

    public static void initializePageObjects(WebDriver drvr) {
        PageFactory.initElements(drvr, Home.class);
    }

    @FindBy (className = "logo")
    public static WebElement logo;

    @FindBy (className = "authorization-link")
    public static WebElement signInLink;

    @FindBy (xpath = "//*[@id=\"ui-id-2\"]/li[1]")
    public static WebElement whatsNewLink;

    @FindBy (xpath = "/html/body/div[1]/header/div[1]/div/ul/li[3]/a")
    public static WebElement createAccountLink;

    @FindBy (id = "search")
    public static WebElement searchBox;

    @FindBy (className = "minicart-wrapper")
    public static WebElement cartButton;


}
