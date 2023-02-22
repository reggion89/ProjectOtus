package pageObject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waiter.Waiter;

import java.time.Duration;

public abstract class AbsPageObject {
    protected WebDriver driver;
    protected Actions actions;
    protected Waiter waiter;

    public AbsPageObject (WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiter = new Waiter(driver);

        PageFactory.initElements(driver, this);
    }
    protected void click (WebElement element) {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    protected void perform (WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(element).perform();
    }

    public void checkVisibilityOfElements(WebElement element) {
        Assertions.assertTrue(element.isDisplayed());

    }
    protected void assertByText(WebElement element, String text) {
        Assertions.assertEquals(element.getText(), text);

    }
    protected void clickAndPerform(WebElement element) {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).click().perform();
    }


}
