package nt.tshape.automation.PageModal;

import lombok.SneakyThrows;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import nt.tshape.automation.selenium.Utils;
import org.openqa.selenium.WebDriver;

import static nt.tshape.automation.selenium.TestContext.getAttributeByName;

public class AgodaFavoriteListPage extends ActionManager {
    public AgodaFavoriteListPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String agodaFavoriteListPage_CheckInOrOutBoxDateByClass = "xpath=//div[contains(@data-selenium,'%s')]";
    private final String agodaFavoriteListPage_OccupationLocatorByClass = "xpath=//*[contains(@data-selenium,'%s')]";
    private final String agodaFavoriteListPage_SelectedHotelNameLocatorByLink = "xpath=(//a[contains(@href,'%s')]//div[contains(@class,'Typographystyled__TypographyStyled-sc-j18mtu-0')])[1]";
    private final String agodaFavoriteListPage_SelectedHotelLocationLocatorByLink = "xpath=(//a[contains(@href,'%s')]//p[contains(@class,'Typographystyled__TypographyStyled-sc-j18mtu-0')])[1]";
    private final String agodaHotelDetailPage_UserPropertiesListLocatorByName = "xpath=//div[contains(@data-element-name,'favorites-screen-category')]//*[contains(text(),'%s')]";

    //Function
    public AgodaFavoriteListPage openFavoriteListWithLocationName(String locationName) {
        waitForElementVisible(agodaHotelDetailPage_UserPropertiesListLocatorByName.formatted(locationName));
        click(agodaHotelDetailPage_UserPropertiesListLocatorByName.formatted(locationName));
        return this;
    }

    @SneakyThrows
    public AgodaFavoriteListPage verifySavedHotelExistInFavoriteList() {
        String selectedHotelCard = "partialLinkText=" + getAttributeByName("SelectedHotelName");
        mouseHoverToElement(selectedHotelCard);
        //verify
        assertElementExist(selectedHotelCard);
        return this;
    }

    public AgodaFavoriteListPage verifyCheckDateCorrectByName(String checkType) {
        //Act
        String expectedCheckDate = getAttributeByName(checkType);
        expectedCheckDate = expectedCheckDate.substring(expectedCheckDate.indexOf(" "), expectedCheckDate.length()).trim();
        expectedCheckDate = Utils.convertTextToFormattedDate(expectedCheckDate, "MMM dd yyyy", "dd MMM yyyy");
        String actualCheckDate = getText(agodaFavoriteListPage_CheckInOrOutBoxDateByClass.formatted(checkType));

        //Verify
        assertEqual(checkType, expectedCheckDate, actualCheckDate);
        return this;
    }

    public AgodaFavoriteListPage verifyOccupationCorrectByName(String occupationName) {
        //Act
        String actualOccupationValue = getTextByAttribute(agodaFavoriteListPage_OccupationLocatorByClass.formatted(occupationName), "data-value");
        String expectedOccupationValue = getAttributeByName(occupationName);

        //Verity
        assertEqual(occupationName, expectedOccupationValue, actualOccupationValue);
        return this;
    }

    public AgodaFavoriteListPage verifySavedHotelNameSameAsFavoritedOne() {
        //Act
        String actualHotelName = getText(agodaFavoriteListPage_SelectedHotelNameLocatorByLink.formatted(Utils.convertTextToAgodaLinkFormat(getAttributeByName("SelectedHotelName"))));
        String expectedHotelName = getAttributeByName("SelectedHotelName");

        //Verity
        assertEqual("SelectedHotelName", expectedHotelName, actualHotelName);
        return this;
    }
    public AgodaFavoriteListPage verifySavedHotelLocationSameAsFavoritedOne() {
        //Act
        String actualHotelLocation = getText(agodaFavoriteListPage_SelectedHotelLocationLocatorByLink.formatted(Utils.convertTextToAgodaLinkFormat(getAttributeByName("SelectedHotelName"))));
        String expectedHotelLocation = getAttributeByName("SelectedHotelLocation");

        //Verity
        assertEqual("SelectedHotelLocation", expectedHotelLocation, actualHotelLocation);
        return this;
    }
}
