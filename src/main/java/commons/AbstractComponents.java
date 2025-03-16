package commons;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObjects.OrderPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cartIcon;

    @FindBy(css = "button[routerlink*='myorders']")
    WebElement orderPageButton;

    public AbstractComponents(WebDriver driver){
        this.driver = driver;
    }


    public void waitForVisibility(By element, Duration duration){
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void waitForElementVisibility(WebElement element, Duration duration){
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element, Duration duration){
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToDisappear(By element, Duration duration){
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public void waitForElementToDisappear(WebElement element, Duration duration){
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForVisibilityOfText(WebElement element, Duration duration,String str){
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(element,str));
    }

    public void goToCart(){
        waitForElementToBeClickable(cartIcon,Duration.ofSeconds(5));
        cartIcon.click();
    }

    public OrderPage goToOrderPage(){
        waitForElementVisibility(orderPageButton,Duration.ofSeconds(2));
        orderPageButton.click();
        return new OrderPage(driver);
    }

    public String getScreenShot(String testCaseName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destinationFilePath = System.getProperty("user.dir") + "/src/test/java/screenshots/"
                +testCaseName+".png";
        File destination = new File(destinationFilePath);
        FileUtils.copyFile(source,destination);
        return destinationFilePath;
    }

}
