package components;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EventTiles extends AbsBaseComponent{
    public EventTiles(WebDriver driver) {
        super(driver);
    }
    private static final String EVENTTIMECLASS = ".dod_new-event__time";
    private static final String EVENTTYPE = ".dod_new-event__type";

    @FindBy(css = EVENTTIMECLASS)
    List<WebElement> eventsWebElementList;

    @FindBy(css = EVENTTYPE)
    List<WebElement> dodList;

    public void eventTypeShouldBeSameAs(String eventType) {
        for (WebElement element : dodList) {
            Assertions.assertTrue(element.getText().contains(eventType));
        }
    }

    public void eventsTilesShouldBeDisplayed() {
        checkVisibilityOfElements(driver.findElement(By.cssSelector(EVENTTIMECLASS)));
    }

    public void checkEventsDate() {
        List<String> eventsList = new ArrayList<>();
        List<LocalDateTime> eventsDateList = new ArrayList<>();
        String monthOfEvent;
        String dateOfEvent;


        for (WebElement element : eventsWebElementList) {
            eventsList.add(element.getText()+" 2023");
        }
        for (String string : eventsList) {
            monthOfEvent = string.split(" ")[1];
            dateOfEvent = string.split(" ")[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            LocalDateTime localDateTime = LocalDateTime.parse(dateOfEvent, formatter);

            if (localDateTime.equals("Мероприятие сегодня")) {
                eventsDateList.add(LocalDateTime.now());
            } else {
                eventsDateList.add(localDateTime);
            }
        }
        for (LocalDateTime localDateTime : eventsDateList) {
            Assertions.assertTrue(localDateTime
                    .isAfter(localDateTime.now()) || localDateTime.isEqual(localDateTime.now()));
        }
    }
}
