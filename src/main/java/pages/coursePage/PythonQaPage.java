package pages.coursePage;

import org.openqa.selenium.WebDriver;

public class PythonQaPage extends AbsCourse{
    public PythonQaPage(WebDriver driver) {
        super(driver, "/lessons/avtomatizaciya-web-testirovaniya/");
    }
    private String courseTitleText = "Python QA Engineer";
    private String courseDescriptionText = "Автоматизация тестирования на Python";
    private String courseDurationText = "5 месяцев";
    private String courseFormatText = "Online";

    public String getCourseTitleText() {
        return courseTitleText;
    }

    public String getCourseDescriptionText() {
        return courseDescriptionText;
    }

    public String getCourseDurationText() {
        return courseDurationText;
    }

    public String getCourseFormatText() {
        return courseFormatText;
    }

}
