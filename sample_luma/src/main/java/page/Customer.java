package page;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import page.objects.ItemPage;

public class Customer {

    public static String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String randomFirstName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }
    public static String randomLastName() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }
    public static String randomCompany() {
        Faker faker = new Faker();
        return faker.company().name();
    }
    public static String randomAddressLine1() {
        Faker faker = new Faker();
        return faker.address().streetAddress();
    }
    public static String randomAddressLine2() {
        Faker faker = new Faker();
        return faker.address().buildingNumber();
    }
    public static String randomCity() {
        Faker faker = new Faker();
        return faker.address().city();
    }
    public static String randomState() {
        Faker faker = new Faker();
        return faker.address().stateAbbr();
    }

    public static String randomZip() {
        Faker faker = new Faker();
        return faker.address().zipCode();
    }
    public static String randomPhone() {
        Faker faker = new Faker();
        return faker.phoneNumber().phoneNumber();
    }

}
