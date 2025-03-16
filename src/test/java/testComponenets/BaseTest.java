package testComponenets;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.pageObjects.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public String browswer;
    public LandingPage landingPage;

    private WebDriver initializeClass() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/GlobalData.properties");
        properties.load(fileInputStream);
        // GET BROWSER NAME EITHER FROM PROPERTY FILE OR FROM MAVEN COMMAND
        String browserName = System.getProperty("browser")!=null ?
                System.getProperty("browser"):properties.getProperty("browser");

        // GET BROWSER NAME ONLY FROM PROPERTY FILE
//        String browserName = properties.getProperty("browser");

        //RUN TEST WIT HEADLESS MODE
        if(browserName.contains("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            if(browserName.contains("headless")){
                chromeOptions.addArguments("headless");
            }
            driver = new ChromeDriver(chromeOptions);
            // OPTIONAL BUT USED TO RUN IN MAX SCREEN WHILE IN FULL SCREEN MODE
            driver.manage().window().setSize(new Dimension(1440,900));
        }

        //RUN TEST WITHOUT HEADLESS MODE
//        if(browserName.equalsIgnoreCase("chrome")){
//            driver = new ChromeDriver();
//        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        driver =  initializeClass();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod
    public void quiteDriver(){
        driver.close();
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
