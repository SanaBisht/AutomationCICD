package org.pageObjects;

import commons.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class Cart extends AbstractComponents {

    public Cart(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    WebDriver driver;

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartIcon;

    @FindBy(css = ".cartWrap h3")
    List<WebElement> productNamesInCart;

    By productNames = By.cssSelector(".cartWrap h3");

    public boolean isProductAddedToCart(String productName) throws InterruptedException {
        waitForVisibility(productNames, Duration.ofSeconds(5));
        productNamesInCart.forEach(product -> waitForElementVisibility(product,Duration.ofSeconds(5)));
        return productNamesInCart.stream().anyMatch(product-> product.getText().contains(productName));
    }

    public CheckoutPage productSuccessfullyAddedToCart(){
        return new CheckoutPage(driver);
    }
}
