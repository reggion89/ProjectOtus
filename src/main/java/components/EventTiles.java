package components;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    private Logger logger = LogManager.getLogger(EventTiles.class);
    public EventTiles(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".dod_new-event__time")
    List<WebElement> eventsWebElementList;

    @FindBy(css = ".dod_new-event__type")
    List<WebElement> dodList;

    public void eventTypeShouldBeSameAs(String eventType) {
        for (WebElement element : dodList) {
            Assertions.assertTrue(element.getText().contains(eventType));
        }
    }

    public void eventsTilesShouldBeDisplayed() { // Этот метод можно удалить если селектор вынесен в FindBy?
        checkVisibilityOfElements(driver.findElement(By.cssSelector(".dod_new-event__time")));
    }

    public void checkEventsDate() {
        List<String> eventsList = new ArrayList<>();
        List<LocalDate> eventsDateList = new ArrayList<>();
        String monthOfEvent;
        String dateOfEvent;
        LocalDate currentDate = LocalDate.now();
        int i;


        for (WebElement element : eventsWebElementList) {
            eventsList.add(element.getText());
        }
        for (String string : eventsList) {
            monthOfEvent = string.split(" ")[1];
            dateOfEvent = string.split(" ")[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            String date = dateOfEvent+" " +monthOfEvent +" 2023";
            LocalDate localDate = LocalDate.parse(date, formatter);


            if (localDate.equals("Сейчас в эфире")) {
                eventsDateList.add(LocalDate.now());
            } else {
                eventsDateList.add(localDate);
            }
        }
        for (LocalDate localDate : eventsDateList) {
            Assertions.assertTrue(localDate.isAfter(currentDate) || localDate.isEqual(currentDate));
        }
        logger.info("Мероприятий предстоит ещё "+eventsList.size());
    }
}
