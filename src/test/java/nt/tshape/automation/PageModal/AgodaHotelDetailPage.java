package nt.tshape.automation.PageModal;

import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

public class AgodaHotelDetailPage extends ActionManager {
    public AgodaHotelDetailPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String agodaHotelDetailPage_HotelNameLocator = "xpath=//h1[contains(@class,'HeaderCerebrum__Name')]";
    private final String agodaHotelDetailPage_HotelLocationLocator = "xpath=//div[contains(@class,'HeaderCerebrum__Location')]//span[contains(@data-selenium,'hotel-address-map')]";
    private final String agodaHotelDetailPage_HotelBenefitLocatorByName = "xpath=//div[contains(@data-selenium,'RoomGridFilter-filter')]//div[contains(text(),'%s')]";
}
