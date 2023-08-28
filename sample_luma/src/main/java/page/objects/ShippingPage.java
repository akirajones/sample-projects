package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ShippingPage {

    public static void initializePageObjects(WebDriver drvr) {
        PageFactory.initElements(drvr, ShippingPage.class);
    }


}