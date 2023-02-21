package pages.coursePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbsPage;

public class AbsCourse extends AbsPage {
    public AbsCourse(WebDriver driver, String path) {
        super(driver, path);
    }
    @FindBy(css = ".course-header2__title")
    protected WebElement courseTitle;

    @FindBy (css = ".course-header2__subtitle")
    protected WebElement courseDescription;

    @FindBy (css = ".container__col_md-4")
    protected WebElement courseDuration;

    @FindBy (css = ".container__col_md-2")
    protected WebElement courseFormat;

    public void courseTitleShouldBeSameAs(String courseTitleText) {
        assertByText(courseTitle, courseTitleText);
    }

    public void courseDescriptionShouldBeSameAs(String courseDescriptionText) {
        assertByText(courseDescription, courseDescriptionText);
    }

    public void courseDurationShouldBeSameAs(String courseDurationText) {
        assertByText(courseDuration, courseDurationText);
    }

    public void courseFormatShouldBeSameAs(String courseFormatText) {
        assertByText(courseFormat, courseFormatText);
    }
}
