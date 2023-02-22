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
    private List<WebElement> eventsWebElementList;

    @FindBy(css = ".dod_new-event__type")
    private List<WebElement> dodList;

    public void eventTypeShouldBeSameAs(String eventType) {
        for (WebElement element : dodList) {
            Assertions.assertTrue(element.getText().contains(eventType));
        }
    }

    public void eventsTilesShouldBeDisplayed() {
        eventsWebElementList.forEach(this::checkVisibilityOfElements);
    }

    public void checkEventsDate() {
        LocalDate currentDate = LocalDate.now();


        for (WebElement element : eventsWebElementList) {
            String data = element.getText()+" "+LocalDate.now().getYear();
            LocalDate localData = null;
            if (data.contains("Сейчас в эфире")) {
                localData = LocalDate.now();
            } else {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM HH:mm yyyy", new Locale("ru"));
            localData = LocalDate.parse(data, formatter);
            }
            Assertions.assertTrue(localData.isAfter(currentDate) || localData.isEqual(currentDate));

        }
        logger.info("Мероприятий предстоит ещё "+eventsWebElementList.size());
        }


}
