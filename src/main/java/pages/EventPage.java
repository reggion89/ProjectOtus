package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EventPage extends AbsPage{
    public EventPage(WebDriver driver) {
        super(driver, "/events/near/");
    }
    @FindBy(css = ".dod_new-events-dropdown")
    private WebElement eventsDropDown;

    @FindBy (css = ".footer2__container")
    private WebElement footer;

    private String eventSelector = ".dod_new-events-dropdown__list-item[title='%s']";



    public void selectEvent(String eventTitle) {
        waiter.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".dod_new-events-dropdown_opened")));
        click(driver.findElement(By.cssSelector(String.format(eventSelector, eventTitle))));
    }
    public void openEventsDropDown() {
        clickAndPerform(eventsDropDown);
    }
}
