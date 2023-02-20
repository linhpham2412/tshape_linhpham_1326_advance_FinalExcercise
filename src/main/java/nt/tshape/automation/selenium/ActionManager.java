package nt.tshape.automation.selenium;

import lombok.SneakyThrows;
import nt.tshape.automation.reportmanager.HTMLReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ActionManager {
    private final WebDriver driver;
    private final Wait<WebDriver> wait;
    private final TestContext testContext;
    private final int retryCounter = 3;

    public ActionManager(WebDriver driver, TestContext testContext) {
        this.driver = driver;
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Constant.SHORT_TIME, TimeUnit.SECONDS)
                .pollingEvery(Constant.SHORT_TIME, TimeUnit.SECONDS)
                .ignoring(AssertionError.class);
        this.testContext = testContext;
    }

    public TestContext getTestContext() {
        return testContext;
    }

    public void elementHighlightAuto(WebElement element) {
        for (int i = 0; i < 2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 5px solid red;");
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }
    }

    public void elementHighlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 5px solid red;");
    }

    public void elementRemoveHighLight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
    }

    public WebElement findElement(String elementByTypeAndPath) {
        WebElement workingElement = null;
        String[] extractedString = elementByTypeAndPath.split("=", 2);
        String byType = extractedString[0];
        String path = extractedString[1];
        try {
            switch (byType) {
                case "id" -> workingElement = driver.findElement(By.id(path));
                case "xpath" -> workingElement = driver.findElement(By.xpath(path));
                case "class" -> workingElement = driver.findElement(By.className(path));
                case "css" -> workingElement = driver.findElement(By.cssSelector(path));
                case "linkText" -> workingElement = driver.findElement(By.linkText(path));
                case "name" -> workingElement = driver.findElement(By.name(path));
                case "partialLinkText" -> workingElement = driver.findElement(By.partialLinkText(path));
                case "tag" -> workingElement = driver.findElement(By.tagName(path));
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("The element located by : [" + elementByTypeAndPath + "] cannot be found!");
            throw new NoSuchElementException("The element located by : [" + elementByTypeAndPath + "] cannot be found!");
        } catch (StaleElementReferenceException staleElementReferenceException) {
            return findElement(elementByTypeAndPath);
        } catch (Exception e) {
            System.out.println("There is an error when finding the element : [" + elementByTypeAndPath + "]");
            throw e;
        }
        return workingElement;
    }

    public List<WebElement> findElements(String elementByTypeAndPath) {
        List<WebElement> workingElements = null;
        String[] extractedString = elementByTypeAndPath.split("=", 2);
        String byType = extractedString[0];
        String path = extractedString[1];
        try {
            switch (byType) {
                case "id" -> workingElements = driver.findElements(By.id(path));
                case "xpath" -> workingElements = driver.findElements(By.xpath(path));
                case "class" -> workingElements = driver.findElements(By.className(path));
                case "css" -> workingElements = driver.findElements(By.cssSelector(path));
                case "linkText" -> workingElements = driver.findElements(By.linkText(path));
                case "name" -> workingElements = driver.findElements(By.name(path));
                case "partialLinkText" -> workingElements = driver.findElements(By.partialLinkText(path));
                case "tag" -> workingElements = driver.findElements(By.tagName(path));
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Elements located by : [" + elementByTypeAndPath + "] cannot be found!");
            throw new NoSuchElementException("Elements located by : [" + elementByTypeAndPath + "] cannot be found!");
        } catch (StaleElementReferenceException staleElementReferenceException) {
            return findElements(elementByTypeAndPath);
        } catch (Exception e) {
            System.out.println("There is an error when finding Elements : [" + elementByTypeAndPath + "]");
            throw e;
        }
        return workingElements;
    }

    @SneakyThrows
    public void sendKeys(String elementToSendKey, CharSequence keysToSend) {
        String passMessage = "Sent [" + keysToSend + "] into element [" + elementToSendKey + "]";
        String failMessage = "Cannot send [" + keysToSend + "] into element [" + elementToSendKey + "]";
        try {
            WebElement workingElement = findElement(elementToSendKey);
            elementHighlightAuto(workingElement);
            workingElement.sendKeys(keysToSend);
            System.out.println(passMessage);
            HTMLReporter.getCurrentReportNode().pass(passMessage);
        } catch (StaleElementReferenceException staleElementReferenceException) {
            sendKeys(elementToSendKey, keysToSend);
        } catch (Exception e) {
            System.out.println(failMessage);
            HTMLReporter.getCurrentReportNode()
                    .fail(HTMLReporter.getHtmlReporter().markupFailedText(failMessage))
                    .addScreenCaptureFromPath(HTMLReporter.getHtmlReporter().takesScreenshot(driver, failMessage));
            throw e;
        }
    }

    public void clearText(String elementToClearText) {
        String passMessage = "Cleared text in element [" + elementToClearText + "]";
        String failMessage = "Cannot clear text of element [" + elementToClearText + "]";
        try {
            WebElement workingElement = findElement(elementToClearText);
            elementHighlightAuto(workingElement);
            workingElement.clear();
            System.out.println(passMessage);
        } catch (StaleElementReferenceException staleElementReferenceException) {
            clearText(elementToClearText);
        } catch (Exception e) {
            System.out.println(failMessage);
            throw e;
        }
    }

    public String getText(String elementToGetText) {
        try {
            WebElement workingElement = findElement(elementToGetText);
            elementHighlightAuto(workingElement);
            String resultText = workingElement.getTagName().equalsIgnoreCase("input") ? workingElement.getAttribute("value") : workingElement.getText();
            System.out.println("Got text [" + resultText + "] from element [" + elementToGetText + "]");
            return resultText;
        } catch (StaleElementReferenceException staleElementReferenceException) {
            return getText(elementToGetText);
        } catch (Exception e) {
            System.out.println("Cannot get text from element [" + elementToGetText + "]");
            throw e;
        }
    }

    public String getTextFromElement(WebElement webElementToGetText){
        try {
            elementHighlightAuto(webElementToGetText);
            String resultText = webElementToGetText.getTagName().equalsIgnoreCase("input") ? webElementToGetText.getAttribute("value") : webElementToGetText.getText();
            System.out.println("Got text [" + resultText + "] from element [" + webElementToGetText + "]");
            return resultText;
        } catch (StaleElementReferenceException staleElementReferenceException) {
            return getTextFromElement(webElementToGetText);
        } catch (Exception e) {
            System.out.println("Cannot get text from element [" + webElementToGetText + "]");
            throw e;
        }
    }

    @SneakyThrows
    public void click(String elementToClick) {
        String passMessage = "Clicked on the element [" + elementToClick + "]";
        String failMessage = "Cannot click on element [" + elementToClick + "]";
        try {
            elementHighlightAuto(findElement(elementToClick));
            findElement(elementToClick).click();
            HTMLReporter.getCurrentReportNode().pass(passMessage);
            System.out.println(passMessage);
        } catch (StaleElementReferenceException staleElementReferenceException) {
            click(elementToClick);
        } catch (Exception e) {
            HTMLReporter.getCurrentReportNode()
                    .fail(HTMLReporter.getHtmlReporter().markupFailedText(failMessage))
                    .addScreenCaptureFromPath(HTMLReporter.getHtmlReporter().takesScreenshot(driver, failMessage));
            System.out.println(failMessage);
            throw e;
        }
    }

    @SneakyThrows
    public void openUrl(String urlToBeOpen) {
        String passMessage = "Browser opened page with url [" + urlToBeOpen + "] successfully!";
        String failMessage = "Browser cannot opened page with url [" + urlToBeOpen + "]";
        try {
            driver.get(urlToBeOpen);
            HTMLReporter.getCurrentReportNode().pass(passMessage);
            System.out.println(passMessage);
        } catch (Exception e) {
            HTMLReporter.getCurrentReportNode()
                    .fail(HTMLReporter.getHtmlReporter().markupFailedText(failMessage))
                    .addScreenCaptureFromPath(HTMLReporter.getHtmlReporter().takesScreenshot(driver, failMessage));
            System.out.println(failMessage);
            throw e;
        }
    }

    @SneakyThrows
    public void waitForElementVisible(String elementToBeWait) {
        try {
            wait.until(ExpectedConditions.visibilityOf(findElement(elementToBeWait)));
        } catch (StaleElementReferenceException staleElementReferenceException) {
            waitForShortTime();
            waitForElementVisible(elementToBeWait);
        } catch (NoSuchElementException noSuchElementException) {
            throw noSuchElementException;
        }
    }

    @SneakyThrows
    public void waitForElementClickable(String elementToBeWait) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(findElement(elementToBeWait)));
        } catch (StaleElementReferenceException staleElementReferenceException) {
            waitForShortTime();
            waitForElementClickable(elementToBeWait);
        } catch (NoSuchElementException noSuchElementException) {
            throw noSuchElementException;
        }
    }

    @SneakyThrows
    public void mouseMoveToElementAndClick(String elementMovingToAndClick) {
        String passMessage = "Moved and clicked on element [" + elementMovingToAndClick + "]";
        String failMessage = "Cannot move and click on element [" + elementMovingToAndClick + "]";
        try {
            elementHighlightAuto(findElement(elementMovingToAndClick));
            Actions actions = new Actions(driver);
            actions.moveToElement(findElement(elementMovingToAndClick));
            actions.click().build().perform();
            HTMLReporter.getCurrentReportNode().pass(passMessage);
            System.out.println(passMessage);
        } catch (StaleElementReferenceException staleElementReferenceException) {
            mouseMoveToElementAndClick(elementMovingToAndClick);
        } catch (Exception e) {
            HTMLReporter.getCurrentReportNode()
                    .fail(HTMLReporter.getHtmlReporter().markupFailedText(failMessage))
                    .addScreenCaptureFromPath(HTMLReporter.getHtmlReporter().takesScreenshot(driver, failMessage));
            System.out.println(failMessage);
            throw e;
        }
    }

    @SneakyThrows
    public void mouseHoverToElement(String elementMovingTo) {
        String passMessage = "Hovered on element [" + elementMovingTo + "]";
        String failMessage = "Cannot hover on element [" + elementMovingTo + "]";
        try {
            elementHighlightAuto(findElement(elementMovingTo));
            Actions actions = new Actions(driver);
            actions.moveToElement(findElement(elementMovingTo));
            actions.build().perform();
            HTMLReporter.getCurrentReportNode().pass(passMessage);
            System.out.println(passMessage);
        } catch (StaleElementReferenceException staleElementReferenceException) {
            mouseMoveToElementAndClick(elementMovingTo);
        } catch (Exception e) {
            HTMLReporter.getCurrentReportNode()
                    .fail(HTMLReporter.getHtmlReporter().markupFailedText(failMessage))
                    .addScreenCaptureFromPath(HTMLReporter.getHtmlReporter().takesScreenshot(driver, failMessage));
            System.out.println(failMessage);
            throw e;
        }
    }

    @SneakyThrows
    public void selectDropDownFieldWithValue(String elementDropDownField, String fieldValue) {
        String passMessage = "Opened drop down field [" + elementDropDownField + "] and select value [" + fieldValue + "]";
        String failMessage = "Can't open dropdown [" + elementDropDownField + "] and select [" + fieldValue + "]";
        try {
            elementHighlightAuto(findElement(elementDropDownField));
            Select workingDropDownField = new Select(findElement(elementDropDownField));
            workingDropDownField.selectByValue(fieldValue);
            HTMLReporter.getCurrentReportNode().pass(passMessage);
            System.out.println(passMessage);
        } catch (StaleElementReferenceException staleElementReferenceException) {
            selectDropDownFieldWithValue(elementDropDownField, fieldValue);
        } catch (Exception e) {
            HTMLReporter.getCurrentReportNode()
                    .fail(HTMLReporter.getHtmlReporter().markupFailedText(failMessage))
                    .addScreenCaptureFromPath(HTMLReporter.getHtmlReporter().takesScreenshot(driver, failMessage));
            System.out.println(failMessage);
            throw e;
        }
    }

    public Select getDropDownOptionsList(String elementDropDownField) {
        return new Select(findElement(elementDropDownField));
    }

    public void waitForShortTime() throws InterruptedException {
        synchronized (this) {
            while (true) {
                this.wait(1000);
                break;
            }
        }
    }

    public void waitForMediumTime() throws InterruptedException {
        synchronized (this) {
            while (true) {
                this.wait(3000);
                break;
            }
        }
    }

    public void waitForLongTime() throws InterruptedException {
        synchronized (this) {
            while (true) {
                this.wait(5000);
                break;
            }
        }
    }

    public void switchToNewTab(){
        String currentTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
            }
        }
    }

    @SneakyThrows
    public void assertEqual(String objectName, String expected, String actual) {
        String passMessage = "Assert object [" + objectName + "] passed because expected value: [" + expected + "] is equal with actual value: [" + actual + "]";
        String failMessage = "[" + objectName + "] expected: [" + expected + "] not equal actual: [" + actual + "]";
        System.out.println("Compare value [" + expected + "] is equal with: [" + actual + "]");
        try {
            assertEquals(expected, actual);
            HTMLReporter.getCurrentReportNode().pass(passMessage);
            System.out.println(passMessage);
        } catch (AssertionError e) {
            HTMLReporter.getCurrentReportNode()
                    .fail(HTMLReporter.getHtmlReporter().markupFailedText(failMessage))
                    .addScreenCaptureFromPath(HTMLReporter.getHtmlReporter().takesScreenshot(driver, failMessage));
            System.out.println(failMessage);
        }
    }

    public void assertElementNotExist(String elementLocator) {
        String passMessage = "Element [" + elementLocator + "] not exist anymore";
        String failMessage = "Element [" + elementLocator + "] still exist";
        System.out.println("Check is element [" + elementLocator + "] not exist?");
        try {
            findElement(elementLocator);
            HTMLReporter.getCurrentReportNode()
                    .fail(HTMLReporter.getHtmlReporter().markupFailedText(failMessage))
                    .addScreenCaptureFromPath(HTMLReporter.getHtmlReporter().takesScreenshot(driver, failMessage));
            System.out.println(failMessage);
        } catch (NoSuchElementException | IOException e) {
            Assert.assertTrue(true);
            HTMLReporter.getCurrentReportNode().pass(passMessage);
            System.out.println(passMessage);
        }
    }

    public void assertElementExist(String elementLocator) throws IOException {
        String passMessage = "Element [" + elementLocator + "] exist!";
        String failMessage = "Element [" + elementLocator + "] not exist";
        System.out.println("Check is element [" + elementLocator + "] exist?");
        try {
            findElement(elementLocator);
            HTMLReporter.getCurrentReportNode().pass(passMessage);
            System.out.println(passMessage);
        } catch (NoSuchElementException e) {
            HTMLReporter.getCurrentReportNode()
                    .fail(HTMLReporter.getHtmlReporter().markupFailedText(failMessage))
                    .addScreenCaptureFromPath(HTMLReporter.getHtmlReporter().takesScreenshot(driver, failMessage));
            System.out.println(failMessage);
        }
    }

    public void assertConditionTrue(String objectName, Boolean actualCondition) throws IOException {
        String passMessage = "Assert object [" + objectName + "] passed because it is True";
        String failMessage = "[" + objectName + "] is not True";
        System.out.println("Asserting object [" + objectName + "] is True?");
        try {
            assertTrue(actualCondition);
            HTMLReporter.getCurrentReportNode().pass(passMessage);
            System.out.println(passMessage);
        } catch (AssertionError e) {
            HTMLReporter.getCurrentReportNode()
                    .fail(HTMLReporter.getHtmlReporter().markupFailedText(failMessage))
                    .addScreenCaptureFromPath(HTMLReporter.getHtmlReporter().takesScreenshot(driver, failMessage));
            System.out.println(failMessage);
        }
    }
}
