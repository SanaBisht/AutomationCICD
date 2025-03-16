package org.pageObjects;

import commons.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractComponents {

    WebDriver driver;

    @FindBy(xpath = "//th[@scope='row']")
    WebElement orderId;


    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public String getOrderId(){
        return orderId.getText();
    }

}
