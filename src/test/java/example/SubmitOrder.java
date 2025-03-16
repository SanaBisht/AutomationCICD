package example;

import org.pageObjects.Cart;
import org.pageObjects.CheckoutPage;
import org.openqa.selenium.WebDriver;
import org.pageObjects.OrderPage;
import org.pageObjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;
import testComponenets.BaseTest;

import java.io.IOException;


public class SubmitOrder extends BaseTest{
    WebDriver driver;

    @Test
    public void placeOrder() throws InterruptedException, IOException {
        String username = "sanakhan@gmail.com";
        String password = "Sana#123456";

        ProductCatalogue productCatalogue = landingPage.login(username, password);

        String productName = "ZARA COAT 3";
        Cart cart = productCatalogue.addProductToCart(productName);
        System.out.println(productCatalogue.getToastMessage());
        Assert.assertTrue(!productCatalogue.getToastMessage().isEmpty());

        cart.goToCart();
        Assert.assertTrue(cart.isProductAddedToCart(productName));

        String countryName = "India";
        CheckoutPage checkoutPage = cart.productSuccessfullyAddedToCart();
        checkoutPage.checkout(countryName);
        String confirmationMessage = checkoutPage.placeOrder();
        System.out.println(confirmationMessage);
    }

    @Test
    public void orderHistoryTest(){
        String username = "sanakhan@gmail.com";
        String password = "Sana#123456";
        ProductCatalogue productCatalogue = landingPage.login(username, password);
        OrderPage orderPage = productCatalogue.goToOrderPage();
        orderPage.getOrderId();
        Assert.assertFalse(orderPage.getOrderId().isEmpty());
    }
}
