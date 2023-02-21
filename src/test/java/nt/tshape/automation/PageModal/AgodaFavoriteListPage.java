package nt.tshape.automation.PageModal;

import lombok.SneakyThrows;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import nt.tshape.automation.selenium.Utils;
import org.openqa.selenium.WebDriver;

public class AgodaFavoriteListPage extends ActionManager {
    public AgodaFavoriteListPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String agodaFavoriteListPage_CheckInBoxDate = "xpath=//div[contains(@data-selenium,'checkInBox') and contains(@data-date,'%s')]";

    private final String agodaHotelDetailPage_UserPropertiesListLocatorByName = "xpath=//div[contains(@data-element-name,'favorites-screen-category')]//*[contains(text(),'%s')]";

    //Function
    public AgodaFavoriteListPage openFavoriteListWithLocationName(String locationName){
        waitForElementVisible(agodaHotelDetailPage_UserPropertiesListLocatorByName.formatted(locationName));
        click(agodaHotelDetailPage_UserPropertiesListLocatorByName.formatted(locationName));
        return this;
    }
    @SneakyThrows
    public AgodaFavoriteListPage verifySavedHotelExistInFavoriteList(){
        String selectedHotelCard = "partialLinkText="+getTestContext().getAttributeByName("SelectedHotelName");
        mouseHoverToElement(selectedHotelCard);
        //verify
        assertElementExist(selectedHotelCard);
        return this;
    }
    public AgodaFavoriteListPage verifyCheckInAndCheckOutDateCorrect(){
        String savedCheckInDate = getTestContext().getAttributeByName("Book_Start_Date");
        savedCheckInDate = savedCheckInDate.substring(savedCheckInDate.indexOf(" "),savedCheckInDate.length()).trim();
        //Act
        Utils.convertTextToFormattedDate(savedCheckInDate,"MMM dd yyyy","yyyy-mm-dd");
        return this;
    }
}
