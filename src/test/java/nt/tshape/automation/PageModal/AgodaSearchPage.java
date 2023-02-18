package nt.tshape.automation.PageModal;

import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

public class AgodaSearchPage extends ActionManager {
    public AgodaSearchPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String agodaSearchPage_SortBar = "id=sort-bar";
    private final String agodaSearchPage_HotelDestinationByIndex = "xpath=(//div[contains(@class,'PropertyCard__Container')]//span[contains(@data-selenium,'area-city-text')]//span)[%s]";
    private final String agodaSearchPage_HotelNameByIndex = "xpath=(//div[contains(@class,'PropertyCard__Container')]//h3[contains(@data-selenium,'hotel-name')])[%s]";
    private final String agodaSearchPage_HotelRoomBenefitsByIndex = "xpath=(//div[contains(@data-selenium,'pill-container')])[%s]//div//span";
    private final String agodaSearchPage_SideFilterMenuRoomOfferItemByName = "xpath=//span[contains(@data-component,'fliter-label')]//span[contains(text(),'Room offers')]//following::div[contains(@class,'filter-items')][1]//span[contains(@data-selenium,'filter-item-text') and contains(text(),'%s')]";
}
