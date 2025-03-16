package example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import testComponenets.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");

        String itemName = "ZARA COAT 3";

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        login(driver);
        addToCart(driver,itemName);

        checkItem(driver,itemName);
        checkout(driver);
        driver.quit();
    }

    public static void login(WebDriver driver){
        driver.findElement(By.cssSelector("#userEmail")).sendKeys("sanakhan@gmail.com");
        driver.findElement(By.cssSelector("#userPassword")).sendKeys("Sana#123456");
        driver.findElement(By.cssSelector("#login")).click();
    }

    public static void addToCart(WebDriver driver, String itemName) throws InterruptedException {
        List<WebElement> items = driver.findElements(By.cssSelector(".card-body"));
        WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(10));
//        items.forEach(item->{
//            if(item.findElement(By.tagName("b")).getText().trim().contains(itemName.trim())){
//                item.findElement(By.cssSelector(".w-10")).click();
//                webDriverWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("#toast-container")),"Product Added To Cart"));
//                String toastMessage = driver.findElement(By.cssSelector("#toast-container")).getText();
//                System.out.println(toastMessage);
//            }
//        });

        WebElement element = items.stream().filter(item-> item.findElement(By.tagName("b")).getText().trim().contains(itemName.trim())).findFirst().orElse(null);
        element.findElement(By.cssSelector(".w-10")).click();
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("#toast-container")),"Product Added To Cart"));
        webDriverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        String toastMessage = driver.findElement(By.cssSelector("#toast-container")).getText();
        System.out.println(toastMessage);

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
    }

    public static void checkItem(WebDriver driver,String itemName){
        List<WebElement> productInCart = driver.findElements(By.cssSelector(".cartWrap h3"));
        boolean ispresent = productInCart.stream().anyMatch(product-> product.getText().contains(itemName));
        Assert.assertTrue(ispresent);
    }

    public static void checkout(WebDriver driver){
        driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
        WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='ng-star-inserted']"))));
        List<WebElement> countries = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
        WebElement element = countries.stream().filter(country->country.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
        element.click();
        driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
        String confirmation = driver.findElement(By.cssSelector("#toast-container")).getText();
        System.out.println(confirmation);
    }
}