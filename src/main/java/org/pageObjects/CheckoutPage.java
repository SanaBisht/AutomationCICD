package org.pageObjects;

import commons.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends AbstractComponents {

    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    WebElement checkoutButton;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countrySelectionBar;

    @FindBy(xpath = "//span[@class='ng-star-inserted']")
    List<WebElement> countrySelectionOptions;

    @FindBy(xpath = "//a[contains(text(),'Place Order')]")
    WebElement placeOrderButton;

    @FindBy(css = "#toast-container")
    WebElement toastContainer;

    By countrySelectionOption = By.xpath("//span[@class='ng-star-inserted']");
    By toastContainerby = By.cssSelector("#toast-container");

    public void checkout(String countryName){
        checkoutButton.click();
        countrySelectionBar.sendKeys(countryName);
        waitForVisibility(countrySelectionOption, Duration.ofSeconds(5));
        WebElement countryElement = countrySelectionOptions.stream().filter(country->country.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
        countryElement.click();
    }

    public String placeOrder(){
        placeOrderButton.click();
        waitForVisibility(toastContainerby,Duration.ofSeconds(5));
        return toastContainer.getText();
    }

}
