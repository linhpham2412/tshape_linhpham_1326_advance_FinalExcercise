package nt.tshape.automation.PageModal;

import lombok.SneakyThrows;
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
    private final String agodaSearchPage_SideFilterMenuRoomOfferItemByName = "xpath=//span[contains(@class,'filter-item-content') and contains(text(),'%s')]";
    private final String agodaSearchPage_SideFilterMenuRoomOfferTitleByName = "xpath=//span[contains(@data-component,'fliter-label')]//span[contains(text(),'%s')]";
    private final String agodaSearchPage_SideFilterSeparatorLocator = "//following::div[contains(@class,'FilterSeparator')][1]";

    //Function
    @SneakyThrows
    public AgodaSearchPage checkSearchResultsNumberShouldContainsLocationValue(int numberOfResult, String locationName) {
        waitForElementVisible(agodaSearchPage_SortBar);
        String hotelLocation = "";
        for (int i = 1; i < numberOfResult + 1; i++) {
            mouseHoverToElement(agodaSearchPage_HotelDestinationByIndex.formatted(String.valueOf(i)));
            hotelLocation = getText(agodaSearchPage_HotelDestinationByIndex.formatted(String.valueOf(i)));
            assertConditionTrue("Hotel no " + i + " with location " + hotelLocation + " contains " + locationName, hotelLocation.contains(locationName));
        }
        return this;
    }

    public AgodaSearchPage filterHotelBenefitWithBenefitTitleAndOptionName(String benefitTitle, String benefitName) {
        mouseHoverToElement(agodaSearchPage_SideFilterMenuRoomOfferTitleByName.formatted(benefitTitle) + agodaSearchPage_SideFilterSeparatorLocator);
        click(agodaSearchPage_SideFilterMenuRoomOfferItemByName.formatted(benefitName));
        return this;
    }

    @SneakyThrows
    public AgodaSearchPage clickOnHotelByIndex(String numberIndex) {
        waitForShortTime();
        waitForElementVisible(agodaSearchPage_HotelNameByIndex.formatted(numberIndex));
        TestContext.setAttribute("SelectedHotelName", getText(agodaSearchPage_HotelNameByIndex.formatted(numberIndex)));
        TestContext.setAttribute("SelectedHotelLocation", getText(agodaSearchPage_HotelDestinationByIndex.formatted(numberIndex)));
        mouseMoveToElementAndClick(agodaSearchPage_HotelNameByIndex.formatted(numberIndex));
        return this;
    }
}
