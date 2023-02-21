package nt.tshape.automation.PageModal;

import lombok.SneakyThrows;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AgodaHotelDetailPage extends ActionManager {
    WebDriver driver;
    public AgodaHotelDetailPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver = driver;
    }

    //Locator
    private final String agodaHotelDetailPage_HotelNameLocator = "xpath=//div[contains(@class,'HeaderCerebrum')]//*[contains(@data-selenium,'hotel-header-name')]";
    private final String agodaHotelDetailPage_HotelLocationLocator = "xpath=//div[contains(@class,'HeaderCerebrum__Location')]//span[contains(@data-selenium,'hotel-address-map')]";
    private final String agodaHotelDetailPage_HotelBenefitLocatorByName = "xpath=//div[contains(@data-selenium,'RoomGridFilter-filter')]//div[contains(text(),'%s')]";
    private final String agodaHotelDetailPage_HotelFacilitiesLocatorByName = "xpath=//p[contains(text(),'%s')]";
    private final String agodaHotelDetailPage_ButtonFavoriteLocator = "xpath=//*[contains(@data-selenium,'favorite-heart')]";
    private final String agodaHotelDetailPage_ButtonFavoriteSavedFavoriteLocator = "xpath=//div[contains(@type,'bouncing-fav')]";
    private final String agodaHotelDetailPage_UserAvatarLocator = "xpath=//div[contains(@data-element-name,'user-avatar')]";
    private final String agodaHotelDetailPage_UserSettingSubMenuLocatorByName = "xpath=//a[contains(@data-element-name,'favorite-menu')]";

    //Function
    public AgodaHotelDetailPage clickFavoriteButton(){
        mouseMoveToElementAndClick(agodaHotelDetailPage_ButtonFavoriteLocator);
        return this;
    }
    public AgodaHotelDetailPage goToUserFavoriteHotelList(){
        mouseMoveToElementAndClick(agodaHotelDetailPage_UserAvatarLocator);
        waitForElementVisible(agodaHotelDetailPage_UserSettingSubMenuLocatorByName);
        click(agodaHotelDetailPage_UserSettingSubMenuLocatorByName);
        return this;
    }

    //Verify
    @SneakyThrows
    public AgodaHotelDetailPage verifyHotelDetailPageDisplayCorrectHotelName() {
        //Act
        try {
            waitForElementVisible(agodaHotelDetailPage_HotelNameLocator);
        } catch (NoSuchElementException e) {
            switchToNewTab();
        }
        String actualHotelName = getText(agodaHotelDetailPage_HotelNameLocator);
        String expectedHotelName = getTestContext().getAttributeByName("SelectedHotelName");

        //Verify
        assertEqual("Hotel Name", expectedHotelName, actualHotelName);
        return this;
    }

    public AgodaHotelDetailPage verifyHotelDetailPageDisplayCorrectHotelLocation() {
        //Act
        try {
            waitForElementVisible(agodaHotelDetailPage_HotelLocationLocator);
        } catch (NoSuchElementException e) {
            switchToNewTab();
        }
        String actualHotelLocation = getText(agodaHotelDetailPage_HotelLocationLocator);
        String expectedHotelLocation = getTestContext().getAttributeByName("SelectedHotelLocation");

        //Verify
        assertEqual("Hotel Location", expectedHotelLocation, actualHotelLocation);
        return this;
    }

    @SneakyThrows
    public AgodaHotelDetailPage verifyHotelDetailPageDisplaySelectedBenefitByName(String expectedBenefitName) {
        //Arrange
        try{
            waitForElementVisible(agodaHotelDetailPage_HotelBenefitLocatorByName.formatted(expectedBenefitName));
        }catch (NoSuchElementException e){
            switchToNewTab();
        }
        //Verify
        assertElementExist(agodaHotelDetailPage_HotelBenefitLocatorByName.formatted(expectedBenefitName));
        return this;
    }
    @SneakyThrows
    public AgodaHotelDetailPage verifyHotelDetailPageDisplaySelectedFacilitiesByName(String expectedFacilityName) {
        //Arrange
        try{
            mouseHoverToElement(agodaHotelDetailPage_HotelFacilitiesLocatorByName.formatted(expectedFacilityName));
        }catch (NoSuchElementException e){
            switchToNewTab();
        }
        //Verify
        assertElementExist(agodaHotelDetailPage_HotelFacilitiesLocatorByName.formatted(expectedFacilityName));
        return this;
    }
    @SneakyThrows
    public AgodaHotelDetailPage verifyFavoriteSavedCorrectly(){
        //verify
        assertElementExist(agodaHotelDetailPage_ButtonFavoriteSavedFavoriteLocator);
        return this;
    }
}
