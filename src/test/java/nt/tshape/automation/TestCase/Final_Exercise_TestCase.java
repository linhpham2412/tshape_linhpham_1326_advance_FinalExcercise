package nt.tshape.automation.TestCase;

import lombok.SneakyThrows;
import nt.tshape.automation.PageModal.AgodaHomePage;
import nt.tshape.automation.PageModal.AgodaHotelDetailPage;
import nt.tshape.automation.PageModal.AgodaSearchPage;
import nt.tshape.automation.setup.WebDriverTestNGSetupBase;
import org.testng.annotations.Test;

public class Final_Exercise_TestCase extends WebDriverTestNGSetupBase {
    @SneakyThrows
    @Test(alwaysRun = true)
    public void TC_1_Add_Hotel_Into_Favourite_Successfully() {
        AgodaHomePage agodaHomePage = new AgodaHomePage(getDriver(),getTestContext());
        AgodaHotelDetailPage agodaHotelDetailPage = new AgodaHotelDetailPage(getDriver(),getTestContext());
        AgodaSearchPage agodaSearchPage = new AgodaSearchPage(getDriver(),getTestContext());

        //Start Testing
        agodaHomePage
                .openAgodaHomePage()
                .inputSearchLocationByName("Dalat")
                .selectStartDateFromTomorrowToNextNumberOfDays(3)
                .selectTravelTypeByName("Family travelers")
                .changeNumberOfRoomTo(1)
                .changeNumberOfAdultsTo(2)
                .clickSearchHotelButton();

        agodaSearchPage
                .checkSearchResultsNumberShouldContainsLocationValue(5,"Dalat")
                .filterHotelBenefitWithOptionName("Breakfast included")
                .clickOnHotelByIndex("1");

        agodaHotelDetailPage
                .verifyHotelDetailPageDisplayCorrectHotelName()
                .verifyHotelDetailPageDisplayCorrectHotelLocation()
                .verifyHotelDetailPageDisplaySelectedBenefitByName("Breakfast included");
    }
    @SneakyThrows
    @Test(alwaysRun = true)
    public void TC_2_Add_Hotel_Into_Favourite_Successfully(){
        AgodaHomePage agodaHomePage = new AgodaHomePage(getDriver(),getTestContext());
        AgodaHotelDetailPage agodaHotelDetailPage = new AgodaHotelDetailPage(getDriver(),getTestContext());
        AgodaSearchPage agodaSearchPage = new AgodaSearchPage(getDriver(),getTestContext());

        //Start Testing
        agodaHomePage
                .openAgodaHomePage()
                .inputSearchLocationByName("Dalat")
                .selectStartDateFromNextFridayToNextNumberOfDays(3)
                .selectTravelTypeByName("Family travelers")
                .changeNumberOfRoomTo(2)
                .changeNumberOfAdultsTo(4)
                .clickSearchHotelButton();

        agodaSearchPage
                .checkSearchResultsNumberShouldContainsLocationValue(5,"Dalat")
                .filterHotelBenefitWithOptionName("Swimming pool")
                .clickOnHotelByIndex("1");
    }
}
