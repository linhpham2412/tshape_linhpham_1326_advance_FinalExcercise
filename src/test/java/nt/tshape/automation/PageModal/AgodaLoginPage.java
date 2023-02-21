package nt.tshape.automation.PageModal;

import lombok.SneakyThrows;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

public class AgodaLoginPage extends ActionManager {
    public AgodaLoginPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String agodaLoginPage_EmailTextBoxLocator = "id=email";
    private final String agodaLoginPage_PasswordTextBoxLocator = "id=password";
    private final String agodaLoginPage_LoginIframeLocator = "xpath=//iframe[contains(@title,'Universal login')]";
    private final String agodaLoginPage_SignInButtonLocator = "xpath=//button[contains(@type,'submit')]//span[contains(text(),'Sign in')]";

    //Function
    @SneakyThrows
    public AgodaLoginPage inputUsernameAndPasswordAndLogin(String username, String password){
        switchToIframeByWebElement(findElement(agodaLoginPage_LoginIframeLocator));
        waitForElementVisible(agodaLoginPage_EmailTextBoxLocator);
        sendKeys(agodaLoginPage_EmailTextBoxLocator,username);
        sendKeys(agodaLoginPage_PasswordTextBoxLocator,password);
        click(agodaLoginPage_SignInButtonLocator);
        waitForMediumTime();
        return this;
    }
}
