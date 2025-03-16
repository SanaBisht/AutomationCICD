package commons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

    public ExtentReports config(){
        String reportPath = System.getProperty("user.dir")+"/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web automation test report");
        reporter.config().setDocumentTitle("Test results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);

        extentReports.setSystemInfo("Tester","Sana Bisht");
        return extentReports;
    }

}
