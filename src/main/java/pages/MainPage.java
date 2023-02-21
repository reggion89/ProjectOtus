package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbsPage {
    public MainPage(WebDriver driver) {
        super(driver, "/");
    }

    private String courseCategory = ".course-categories__nav-item[title='%s']";


    public void openCoursePage(String courseCategoryTitle) {
        click(driver.findElement(By.cssSelector(String.format(courseCategory, courseCategoryTitle))));
    }
}