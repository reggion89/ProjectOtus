package pages.courseCategory;

import org.openqa.selenium.WebDriver;

public  class TestPage extends AbsCategory {
    public TestPage(WebDriver driver) {
        super(driver, "/catalog/courses?categories=testing");
    }
    private String coursePageName = "avtomatizaciya-web-testirovaniya";
    public String getCoursePageName() {
        return coursePageName;
    }
}
