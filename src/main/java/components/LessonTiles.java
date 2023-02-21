package components;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class LessonTiles extends AbsBaseComponent{

    public LessonTiles(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = ".sc-18q05a6-1 > a")
    private List<WebElement> lessonsList;

    public void lessonTilesShouldBeDisplayed() {
        checkVisibilityOfElements(driver.findElement(By.cssSelector(".sc-18q05a6-1 > a")));
    }

    public void numberOfLessonTilesShouldBeSameAs(int numberOfLessonsTiles) {
        Assertions.assertEquals(numberOfLessonsTiles, lessonsList.size());
    }
}
