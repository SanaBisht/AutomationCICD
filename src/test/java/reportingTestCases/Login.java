package reportingTestCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import example.DataProviders;
import org.openqa.selenium.WebDriver;
import org.pageObjects.Cart;
import org.pageObjects.CheckoutPage;
import org.pageObjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testComponenets.BaseTest;
import testComponenets.Retry;

import java.io.IOException;
import java.util.HashMap;

public class Login extends BaseTest {

//    @BeforeTest
//    public void config(){
//        String reportPath = System.getProperty("user.dir")+"/reports/index.html";
//        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
//        reporter.config().setReportName("Web automation test report");
//        reporter.config().setDocumentTitle("Test results");
//
//        extentReports = new ExtentReports();
//        extentReports.attachReporter(reporter);
//
//        extentReports.setSystemInfo("Tester","Sana Bisht");
//    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "getCorrectLoginCredsAsMap")
    public void loginWithCorrectCreds(HashMap<String,String> inputData){
        ProductCatalogue productCatalogue = landingPage.login(inputData.get("email"), inputData.get("pwd"));
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "getIncorrectLoginCredsAsMap")
    public void loginWithIncorrectCreds(HashMap<String,String> inputData){
        ProductCatalogue productCatalogue = landingPage.login(inputData.get("email"), inputData.get("pwd"));
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "getIncorrectLoginCredsAsMap", retryAnalyzer = Retry.class)
    public void loginWithIntentionalFailure(HashMap<String,String> inputData){
        ProductCatalogue productCatalogue = landingPage.login(inputData.get("email"), inputData.get("pwd"));
        Assert.fail();
    }
}


