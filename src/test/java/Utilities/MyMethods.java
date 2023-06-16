package Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MyMethods {

    public WebDriverWait wait = new WebDriverWait(BasicDriver.getDriver(), Duration.ofSeconds(30));

    public void sendKeysMethod(WebElement element, String keys) {
        waitUntilVisible(element);
        waitUntilClickable(element);
        element.clear();
        element.sendKeys(keys);
    }

    public void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) BasicDriver.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickMethod(WebElement element) {
        waitUntilClickable(element);
        waitUntilVisible(element);
        scrollToElement(element);
        element.click();
    }

    public void wait(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyContainsText(WebElement element, String value) {
        waitUntilVisible(element);
        if (isDisplayed(element)) {
            Assert.assertTrue(element.getText().contains(value));
        } else {
            throw new AssertionError("Element is not present or visible");
        }
    }

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isPresent(WebElement element) {
        scrollToElement(element);
        return element.isDisplayed();
    }

    public boolean isSelectedMethod(WebElement element) {
        return element.isSelected();
    }

    public void hoverOver(WebElement element) {
        Actions actions = new Actions(BasicDriver.getDriver());
        Action hoverOverElement = actions.moveToElement(element).build();
        hoverOverElement.perform();
    }

    public void selectMethod(WebElement element) {
        Select select = new Select(element);
        element.click();
    }


}
