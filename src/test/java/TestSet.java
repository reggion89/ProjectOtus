import components.EventTiles;
import components.LessonTiles;
import components.Menu;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EventPage;
import pages.MainPage;
import pages.courseCategory.TestPage;
import pages.coursePage.PythonQaPage;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TestSet {
    private WebDriver driver;
        private Logger logger = LogManager.getLogger(TestSet.class);
    private String cookieButton = "body > div.body-wrapper > div > div.container.cookies__container > div > div > button";
    private String cookieButtonMain = "body > div.body-wrapper > div > div.container.cookies__container > div > div > button";
    private static final String HIDE_COOKIE = "$('body > div.body-wrapper > div > div.container.cookies__container > div > div').hide();";
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
    public void test1(){
        MainPage mainPage = new MainPage(driver);
        TestPage testPage = new TestPage(driver);
        LessonTiles lessonTiles = new LessonTiles(driver);

        mainPage.openCoursePage("Тестирование");
        testPage.showMoreItems();
        lessonTiles.lessonTilesShouldBeDisplayed();
        lessonTiles.numberOfLessonTilesShouldBeSameAs(12);

    }

    @Test
    public void test2(){
        MainPage mainPage = new MainPage(driver);
        TestPage testingPage = new TestPage(driver);
        PythonQaPage pythonQaPage = new PythonQaPage(driver);
        mainPage.openCoursePage("Тестирование");
        testingPage.clickCourseLink(testingPage.getCoursePageName());
        pythonQaPage.courseTitleShouldBeSameAs(pythonQaPage.getCourseTitleText());
        pythonQaPage.courseDescriptionShouldBeSameAs(pythonQaPage.getCourseDescriptionText());
        pythonQaPage.courseDurationShouldBeSameAs(pythonQaPage.getCourseDurationText());
        pythonQaPage.courseFormatShouldBeSameAs(pythonQaPage.getCourseFormatText());
    }

    @Test
    public void test3(){

        Menu menu = new Menu(driver);
        EventTiles eventsTiles = new EventTiles(driver);
        menu.openMenuName("learning");
        menu.clickLearningSubMenuName("/events/near/");
        eventsTiles.eventsTilesShouldBeDisplayed();
        eventsTiles.checkEventsDate();
    }

    @Test
    public void test4(){
        EventPage eventsPage = new EventPage(driver);
        EventTiles eventsTiles = new EventTiles(driver);
        Menu menu = new Menu(driver);

        menu.openMenuName("learning");
        menu.clickLearningSubMenuName("/events/near/");
        eventsPage.openEventsDropDown();
        eventsPage.selectEvent("ДОД");
        eventsTiles.eventTypeShouldBeSameAs("День открытых дверей");

    }
    @Test
    public void test40(){
    }





}



