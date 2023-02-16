package nt.tshape.automation.PageModal;

import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

public class AgodaHomePage extends ActionManager {

    public AgodaHomePage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String agodaHomePage_DestinationTextBox = "id=textInput";
    private final String agodaHomePage_DestinationSuggestionLocation = "xpath=//div[@class='Popup__content']//li[@aria-label='%s']";
    private final String agodaHomePage_FromDateToday = "xpath=//div[contains(@aria-current,'date') and contains(@class,'today')]//span";
    private final String agodaHomePage_FromDateTomorrow = "xpath=(//div[contains(@aria-current,'date') and contains(@class,'today')]//following-sibling::div)[1]//span";
    private final String agodaHomePage_DateSelectorByDayNumberOfThisMonth = "xpath=(//div[contains(@class,'DayPicker-Month') and contains(@role,'grid')])[1]//div[contains(@class,'PriceSurgePicker-Day') and contains(@aria-disabled,'false')]//span[(text()='%s')]";
    private final String agodaHomePage_DateSelectorByDayNumberOfNextMonth = "xpath=(//div[contains(@class,'DayPicker-Month') and contains(@role,'grid')])[2]//div[contains(@class,'PriceSurgePicker-Day') and contains(@aria-disabled,'false')]//span[(text()='%s')]";
    private final String agodaHomePage_DateSelectorLastDayOfThisMonth = "xpath=(//div[contains(@class,'DayPicker-Month') and contains(@role,'grid')])[1]//div[contains(@class,'DayPicker-Week-Wide')][last()]//div[contains(@class,'PriceSurgePicker-Day') and contains(@aria-disabled,'false')][last()]//span";
    private final String agodaHomePage_RoomValueLocator = "xpath=//div[contains(@data-selenium,'desktop-occ-room-value')]//*";
    private final String agodaHomePage_RoomButtonLocatorByName = "xpath=//div[contains(@data-selenium,'%s') and contains(@data-element-name,'occupancy-selector-panel-rooms')]";
    private final String agodaHomePage_AdultValueLocator = "xpath=//div[contains(@data-selenium,'desktop-occ-adult-value')]//*";
    private final String agodaHomePage_AdultButtonLocatorByName = "xpath=//div[contains(@data-selenium,'%s') and contains(@data-element-name,'occupancy-selector-panel-adult')]";
    private final String agodaHomePage_ChildrenValueLocator = "xpath=//div[contains(@data-selenium,'desktop-occ-children-value')]//*";
    private final String agodaHomePage_ChildrenButtonLocatorByName = "xpath=//div[contains(@data-selenium,'%s') and contains(@data-element-name,'occupancy-selector-panel-children')]";
    private final String agodaHomePage_OccupancyBoxLocator = "id=occupancy-box";
    private final String agodaHomePage_SearchButtonLocator = "xpath=//button[contains(@data-selenium,'searchButton')]";


    //Function
    //todo: get value of FromDateToday and value of DateSelectorLastDayOfThisMonth
    //if today equal to last day of this month > pick DateSelectorByDayNumberOfNextMonth with value 1
    //else pick FromDateTomorrow
    //get value of picked day
    //if picked day equal to last day of this month select DateSelectorByDayNumberOfNextMonth with value 3
    //if picked day equal to first day of next month select DateSelectorByDayNumberOfNextMonth with value 4
    //else add 3 to picked date and select that result form DateSelectorByDayNumberOfThisMonth
}
