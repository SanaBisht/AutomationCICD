package example;

import org.openqa.selenium.WebDriver;
import org.pageObjects.Cart;
import org.pageObjects.CheckoutPage;
import org.pageObjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;
import testComponenets.BaseTest;

import java.io.IOException;
import java.util.HashMap;

public class Login extends BaseTest {

    WebDriver driver;

    @Test(dataProviderClass = DataProviders.class,dataProvider = "getCorrectLoginCredsAsMap")
    public void loginWithCorrectCreds(HashMap<String,String> inputData){
        ProductCatalogue productCatalogue = landingPage.login(inputData.get("email"), inputData.get("pwd"));
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "getIncorrectLoginCredsAsMap")
    public void loginWithIncorrectCreds(HashMap<String,String> inputData){
        ProductCatalogue productCatalogue = landingPage.login(inputData.get("email"), inputData.get("pwd"));
    }
}
