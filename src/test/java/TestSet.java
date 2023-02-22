import components.EventTiles;
import components.LessonTiles;
import components.Menu;
import data.Courses;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.EventPage;
import pages.MainPage;
import pages.courseCategory.TestPage;
import pages.coursePage.CoursePage;


public class TestSet {
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(TestSet.class);
    @BeforeAll
    public static void start() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        new MainPage(driver).open();
        logger.info("Браузер открыт в максимальном разрешении.");
    }
    @AfterEach
    public void close(){
        if(driver != null){
            driver.close();
        driver.quit();}
        logger.info("Браузер закрыт после успешного теста");
    }



    @Test
    public void countLessonTiles(){
        MainPage mainPage = new MainPage(driver);
        TestPage testPage = new TestPage(driver);
        LessonTiles lessonTiles = new LessonTiles(driver);

        mainPage.openCoursePage("Тестирование");
        testPage.showMoreItems();
        lessonTiles.lessonTilesShouldBeDisplayed();
        lessonTiles.numberOfLessonTilesShouldBeSameAs(12);

    }

    @Test
    public void checkCoursePage(){
        MainPage mainPage = new MainPage(driver);
        TestPage testingPage = new TestPage(driver);
        CoursePage coursePage = new CoursePage(driver, Courses.WEBQA);
        mainPage.openCoursePage("Тестирование");
        testingPage.clickCourseLink(testingPage.getCoursePageName());
        coursePage.courseTitleShouldBeSameAs(Courses.WEBQA);
        coursePage.courseDescriptionShouldBeSameAs(Courses.WEBQA);
        coursePage.courseDurationShouldBeSameAs(Courses.WEBQA);
        coursePage.courseFormatShouldBeSameAs(Courses.WEBQA);
    }

    @Test
    public void validateEventsDate(){

        Menu menu = new Menu(driver);
        EventTiles eventsTiles = new EventTiles(driver);
        menu.openMenuName("learning");
        menu.clickLearningSubMenuName("/events/near/");
        eventsTiles.eventsTilesShouldBeDisplayed();
        eventsTiles.checkEventsDate();
    }

    @Test
    public void viewEventsByTitles(){
        EventPage eventsPage = new EventPage(driver);
        EventTiles eventsTiles = new EventTiles(driver);
        Menu menu = new Menu(driver);

        menu.openMenuName("learning");
        menu.clickLearningSubMenuName("/events/near/");
        eventsPage.openEventsDropDown();
        eventsPage.selectEvent("ДОД");
        eventsTiles.eventTypeShouldBeSameAs("День открытых дверей");

    }
}



