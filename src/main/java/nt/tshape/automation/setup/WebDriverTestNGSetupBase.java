package nt.tshape.automation.setup;

import nt.tshape.automation.reportmanager.HTMLReporter;
import nt.tshape.automation.selenium.TestContext;
import nt.tshape.automation.selenium.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class WebDriverTestNGSetupBase {
    private TestContext testContext = new TestContext();

    @AfterSuite
    public static void afterSuite() {
        HTMLReporter.getHtmlReporter().getExtentReports().flush();
        WebDriverManager.getDriver().quit();
    }

    public WebDriver getDriver() {
        return WebDriverManager.getDriver();
    }

    public TestContext getTestContext() {
        return testContext;
    }

    @BeforeSuite
    public void beforeSuiteSetUp() {
        HTMLReporter.initHTMLReporter("HTML_TestingReport.html", "HTMLReport_Test_Output_On_");
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void beforeMethod(Method method, @Optional("chrome") String browser) throws MalformedURLException {
        WebDriverManager.iniDriver(browser);
        HTMLReporter.getHtmlReporter().createReportNode(method.getName(), method.toGenericString());
    }

    @AfterMethod
    public void afterMethod(){
        getDriver().quit();
    }

    @BeforeTest
    public void beforeTest() {
        HTMLReporter.getHtmlReporter().createReportClass(getClass().getSimpleName(), getClass().getName());
    }
}
