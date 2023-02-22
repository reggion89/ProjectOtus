package pages.coursePage;

import data.Courses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbsPage;

public class CoursePage extends AbsPage {
    public CoursePage(WebDriver driver, Courses courses) {
        super(driver, courses.getPath());
    }
    @FindBy(css = ".course-header2__title")
    protected WebElement courseTitle;

    @FindBy (css = ".course-header2__subtitle")
    protected WebElement courseDescription;

    @FindBy (css = ".container__col_md-4")
    protected WebElement courseDuration;

    @FindBy (css = ".container__col_md-2")
    protected WebElement courseFormat;

    public void courseTitleShouldBeSameAs(Courses courses) {
        assertByText(courseTitle, courses.getTitle());
    }

    public void courseDescriptionShouldBeSameAs(Courses courses) {
        assertByText(courseDescription, courses.getDescription() );
    }

    public void courseDurationShouldBeSameAs(Courses courses) {
        assertByText(courseDuration, courses.getDuration());
    }

    public void courseFormatShouldBeSameAs(Courses courses) {
        assertByText(courseFormat, courses.getFormat());
    }
}
