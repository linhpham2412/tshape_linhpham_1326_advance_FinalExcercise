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
                .selectStartDateFromTomorrowToNextNumberOfDays(3);
    }
}
