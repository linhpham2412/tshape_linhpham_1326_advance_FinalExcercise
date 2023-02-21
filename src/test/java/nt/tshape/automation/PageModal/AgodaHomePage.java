package nt.tshape.automation.PageModal;

import lombok.SneakyThrows;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class AgodaHomePage extends ActionManager {
    public AgodaHomePage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String agodaHomePage_DestinationTextBox = "id=textInput";
    private final String agodaHomePage_DestinationSuggestionLocation = "xpath=//div[@class='Popup__content']//li[@data-text='%s']";
    private final String agodaHomePage_FromDateToday = "xpath=//div[contains(@class,'PriceSurgePicker-Day__circle--today')]//span";
    private final String agodaHomePage_FromDateTomorrow = "xpath=//div[contains(@class,'PriceSurgePicker-Day__circle--today')]//following::div[1]//span";
    private final String agodaHomePage_FromDateNextFridayOfThisMonth = "xpath=//div[contains(@class,'PriceSurgePicker-Day__circle--today')]//parent::div//parent::div//parent::div//following::div[contains(@class,'DayPicker-Week-Wide')][1]//div[contains(@class,'PriceSurgePicker-Day') and contains(@aria-label,'Fri')]//span";
    private final String agodaHomePage_FromDateFirstFridayOfNextMonth = "xpath=(//div[contains(@class,'DayPicker-Month')][2]//div[contains(@class,'PriceSurgePicker-Day') and contains(@aria-label,'Fri')])[1]//span";
    private final String agodaHomePage_PreviousMonthButton = "xpath=//span[contains(@data-selenium,'calendar-previous-month-button')]";
    private final String agodaHomePage_DateSelectorByDayNumberOfThisMonth = "xpath=(//div[contains(@class,'DayPicker-Month') and contains(@role,'grid')])[1]//div[contains(@class,'PriceSurgePicker-Day') and contains(@aria-disabled,'false')]//span[(text()='%s')]";
    private final String agodaHomePage_DateSelectorByDayNumberOfNextMonth = "xpath=(//div[contains(@class,'DayPicker-Month') and contains(@role,'grid')])[2]//div[contains(@class,'PriceSurgePicker-Day') and contains(@aria-disabled,'false')]//span[(text()='%s')]";
    private final String agodaHomePage_DateSelectorLastDayOfThisMonth = "xpath=(//div[contains(@class,'DayPicker-Month') and contains(@role,'grid')])[1]//div[contains(@class,'DayPicker-Week-Wide')][last()]//div[contains(@class,'PriceSurgePicker-Day') and contains(@aria-disabled,'false')][last()]//span";
    private final String agodaHomePage_DateSelectorPostFixToGetFullDate = "//parent::div//parent::div//parent::div[contains(@class,'PriceSurgePicker-Day__Wide')]";
    private final String agodaHomePage_RoomValueLocator = "xpath=//*[contains(@data-selenium,'desktop-occ-room-value')]";
    private final String agodaHomePage_RoomButtonLocatorByName = "xpath=//*[contains(@data-selenium,'%s') and contains(@data-element-name,'occupancy-selector-panel-rooms')]";
    private final String agodaHomePage_TravalTypeLocatorByName = "xpath=//div[contains(@class,'TravellerSegment__title') and contains(text(),'%s')]";
    private final String agodaHomePage_AdultValueLocator = "xpath=//*[contains(@data-selenium,'desktop-occ-adult-value')]";
    private final String agodaHomePage_AdultButtonLocatorByName = "xpath=//*[contains(@data-selenium,'%s') and contains(@data-element-name,'occupancy-selector-panel-adult')]";
    private final String agodaHomePage_OccupancyBoxLocator = "id=occupancy-box";
    private final String agodaHomePage_SearchButtonLocator = "xpath=//button[contains(@data-selenium,'searchButton')]";
    private final String agodaHomePage_InAppPopupMessage = "xpath=//div[contains(@class,'ab-in-app-message')]";
    private final String agodaHomePage_CloseButtonInAppPopupMessage = "xpath=//button[contains(@class,'ab-close-button')]";


    //Function
    @SneakyThrows
    public AgodaHomePage openAgodaHomePage() {
        openUrl("https://www.agoda.com/");
        return this;
    }

    @SneakyThrows
    public AgodaHomePage inputSearchLocationByName(String locationName) {
        waitForElementVisible(agodaHomePage_DestinationTextBox);
        sendKeys(agodaHomePage_DestinationTextBox, locationName);
        waitForShortTime();
//        waitForLongTime();
//        waitForElementVisible(agodaHomePage_InAppPopupMessage);
//        click(agodaHomePage_CloseButtonInAppPopupMessage);
        waitForElementVisible(agodaHomePage_DestinationSuggestionLocation.formatted(locationName));
        click(agodaHomePage_DestinationSuggestionLocation.formatted(locationName));
        return this;
    }

    @SneakyThrows
    public AgodaHomePage selectStartDateFromTomorrowToNextNumberOfDays(int numberOfDays) {
        waitForShortTime();
        try {
            waitForElementVisible(agodaHomePage_FromDateToday);
        } catch (Exception e) {
            click(agodaHomePage_PreviousMonthButton);
        }
        int todayDateValue = Integer.parseInt(getText(agodaHomePage_FromDateToday));
        int lastDayOfThisMonthDateValue = Integer.parseInt(getText(agodaHomePage_DateSelectorLastDayOfThisMonth));
        int selectedFromDate = 0;
        if (todayDateValue == lastDayOfThisMonthDateValue) {
            click(agodaHomePage_DateSelectorByDayNumberOfNextMonth.formatted("1"));
            selectedFromDate = 1;
        } else {
            click(agodaHomePage_FromDateTomorrow);
            selectedFromDate = Integer.parseInt(getText(agodaHomePage_FromDateTomorrow));
        }
        if (selectedFromDate == lastDayOfThisMonthDateValue) {
            click(agodaHomePage_DateSelectorByDayNumberOfNextMonth.formatted(String.valueOf(numberOfDays)));
        } else if (selectedFromDate == 1) {
            numberOfDays += selectedFromDate;
            click(agodaHomePage_DateSelectorByDayNumberOfNextMonth.formatted(String.valueOf(numberOfDays)));
        } else {
            numberOfDays += selectedFromDate;
            click(agodaHomePage_DateSelectorByDayNumberOfThisMonth.formatted(String.valueOf(numberOfDays)));
        }
        return this;
    }

    @SneakyThrows
    public AgodaHomePage selectStartDateFromNextFridayToNextNumberOfDays(int numberOfDays) {
        try {
            waitForElementVisible(agodaHomePage_FromDateToday);
        } catch (Exception e) {
            waitForShortTime();
            click(agodaHomePage_PreviousMonthButton);
        }
        int selectedFromDate = 0;
        int lastDayOfThisMonthDateValue = Integer.parseInt(getText(agodaHomePage_DateSelectorLastDayOfThisMonth));
        try {
            waitForElementVisible(agodaHomePage_FromDateNextFridayOfThisMonth);
            click(agodaHomePage_FromDateNextFridayOfThisMonth);
            getTestContext().setAttribute("checkInText", getTextByAttribute(agodaHomePage_FromDateNextFridayOfThisMonth + agodaHomePage_DateSelectorPostFixToGetFullDate, "aria-label"));
            selectedFromDate = Integer.parseInt(getText(agodaHomePage_FromDateNextFridayOfThisMonth));
            if (selectedFromDate == lastDayOfThisMonthDateValue) {
                click(agodaHomePage_DateSelectorByDayNumberOfNextMonth.formatted(String.valueOf(numberOfDays)));
                getTestContext().setAttribute("checkOutText", getTextByAttribute(agodaHomePage_DateSelectorByDayNumberOfNextMonth.formatted(String.valueOf(numberOfDays)) + agodaHomePage_DateSelectorPostFixToGetFullDate, "aria-label"));
            } else if (selectedFromDate < lastDayOfThisMonthDateValue) {
                int numberOfDateToEOM = lastDayOfThisMonthDateValue - selectedFromDate;
                if (numberOfDays > numberOfDateToEOM) {
                    numberOfDays -= numberOfDateToEOM;
                }
                selectedFromDate += numberOfDays;
                click(agodaHomePage_DateSelectorLastDayOfThisMonth.formatted(numberOfDays));
                getTestContext().setAttribute("checkOutText", getTextByAttribute(agodaHomePage_DateSelectorLastDayOfThisMonth.formatted(numberOfDays) + agodaHomePage_DateSelectorPostFixToGetFullDate, "aria-label"));
            }
        } catch (NoSuchElementException e) {
            click(agodaHomePage_FromDateFirstFridayOfNextMonth);
            getTestContext().setAttribute("checkInText", getTextByAttribute(agodaHomePage_FromDateFirstFridayOfNextMonth + agodaHomePage_DateSelectorPostFixToGetFullDate, "aria-label"));
            selectedFromDate = Integer.parseInt(getText(agodaHomePage_FromDateFirstFridayOfNextMonth));
            numberOfDays += selectedFromDate;
            getTestContext().setAttribute("checkOutText", getTextByAttribute(agodaHomePage_DateSelectorByDayNumberOfNextMonth.formatted(String.valueOf(numberOfDays)) + agodaHomePage_DateSelectorPostFixToGetFullDate, "aria-label"));
            click(agodaHomePage_DateSelectorByDayNumberOfNextMonth.formatted(String.valueOf(numberOfDays)));
        }
        return this;
    }

    @SneakyThrows
    public AgodaHomePage selectTravelTypeByName(String travelTypeName) {
        waitForShortTime();
        try {
            waitForElementVisible(agodaHomePage_TravalTypeLocatorByName.formatted(travelTypeName));
            click(agodaHomePage_TravalTypeLocatorByName.formatted(travelTypeName));
        } catch (Exception e) {

        }
        waitForMediumTime();
        waitForElementVisible(agodaHomePage_InAppPopupMessage);
        click(agodaHomePage_CloseButtonInAppPopupMessage);
        return this;
    }

    @SneakyThrows
    public AgodaHomePage changeNumberOfRoomTo(int numberOfRoom) {
        waitForShortTime();
        waitForElementVisible(agodaHomePage_RoomValueLocator);
        int currentRoomValue = Integer.parseInt(getText(agodaHomePage_RoomValueLocator));
        int numberOfClickToChange = 0;
        if (currentRoomValue < numberOfRoom) {
            numberOfClickToChange = numberOfRoom - currentRoomValue;
            for (int i = 0; i < numberOfClickToChange; i++) {
                mouseMoveToElementAndClick(agodaHomePage_RoomButtonLocatorByName.formatted("plus"));
            }
        } else {
            numberOfClickToChange = currentRoomValue - numberOfRoom;
            for (int i = 0; i < numberOfClickToChange; i++) {
                mouseMoveToElementAndClick(agodaHomePage_RoomButtonLocatorByName.formatted("minus"));
            }
        }
        getTestContext().setAttribute("roomValue", getText(agodaHomePage_RoomValueLocator));
        return this;
    }

    public AgodaHomePage changeNumberOfAdultsTo(int numberOfAdults) {
        waitForElementVisible(agodaHomePage_AdultValueLocator);
        int currentRoomValue = Integer.parseInt(getText(agodaHomePage_AdultValueLocator));
        int numberOfClickToChange = 0;
        if (currentRoomValue < numberOfAdults) {
            numberOfClickToChange = numberOfAdults - currentRoomValue;
            for (int i = 0; i < numberOfClickToChange; i++) {
                mouseMoveToElementAndClick(agodaHomePage_AdultButtonLocatorByName.formatted("plus"));
            }
        } else {
            numberOfClickToChange = currentRoomValue - numberOfAdults;
            for (int i = 0; i < numberOfClickToChange; i++) {
                mouseMoveToElementAndClick(agodaHomePage_AdultButtonLocatorByName.formatted("minus"));
            }
        }
        getTestContext().setAttribute("adultValue", getText(agodaHomePage_AdultValueLocator));
        return this;
    }

    public AgodaHomePage clickSearchHotelButton() {
        click(agodaHomePage_OccupancyBoxLocator);
        click(agodaHomePage_SearchButtonLocator);
        return this;
    }
    //todo: get value of FromDateToday and value of DateSelectorLastDayOfThisMonth
    //if today equal to last day of this month > pick DateSelectorByDayNumberOfNextMonth with value 1
    //else pick FromDateTomorrow
    //get value of picked day
    //if picked day equal to last day of this month select DateSelectorByDayNumberOfNextMonth with value 3
    //if picked day equal to first day of next month select DateSelectorByDayNumberOfNextMonth with value 4
    //else add 3 to picked date and select that result form DateSelectorByDayNumberOfThisMonth

    //Check 1st when no such element found
}
