package org.pageObjects;

import commons.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class ProductCatalogue extends AbstractComponents {

    WebDriver driver;
    String ToastMessage;

    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".card-body")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement loader;

    @FindBy(css = ".w-10")
    WebElement addToCart;

    @FindBy(css = "#toast-container")
    WebElement toastContainerElement;

    By productsBy = By.cssSelector(".card-body");
    By toastContainer = By.cssSelector("#toast-container");

    private List<WebElement> getProductList(){
        waitForVisibility(productsBy, Duration.ofSeconds(10));
        return products;
    }

    private WebElement selectProduct(String productName){
        return getProductList().stream().filter(item-> item.findElement(By.tagName("b")).getText().trim().contains(productName.trim())).findFirst().orElse(null);
    }


    public Cart addProductToCart(String productName){
        WebElement productToBeAddedToCart = selectProduct(productName);
        productToBeAddedToCart.findElement(By.cssSelector(".w-10")).click();
        waitForVisibilityOfText(toastContainerElement,Duration.ofSeconds(5),"Product Added To Cart");
//        waitForElementToDisappear(loader,Duration.ofSeconds(2));
        ToastMessage = driver.findElement(toastContainer).getText();
        return new Cart(driver);
    }

    public String getToastMessage(){
        return  ToastMessage;
    }


}
