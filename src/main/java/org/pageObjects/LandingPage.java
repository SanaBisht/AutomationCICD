package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    @FindBy(css = "#userEmail")
    WebElement userName;

    @FindBy(css = "#userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement login;


    public ProductCatalogue login(String username, String password){
        this.userName.sendKeys(username);
        this.password.sendKeys(password);
        login.click();
        return new ProductCatalogue(driver);
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
