import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TestSet extends BaseTest {
        private Logger logger = LogManager.getLogger(TestSet.class);
    private String cookieButton = "body > div.body-wrapper > div > div.container.cookies__container > div > div > button";
    private String cookieButtonMain = "body > div.body-wrapper > div > div.container.cookies__container > div > div > button";
    private static final String HIDE_COOKIE = "$('body > div.body-wrapper > div > div.container.cookies__container > div > div').hide();";





    @Test
    public void test1() {
        Logger log = LogManager.getLogger(LogTestClass.class);
        setUp();
        driver.get("https://otus.ru/catalog/courses?categories=testing");
        WebDriverWait firstClick = new WebDriverWait(driver, Duration.ofSeconds(10));
        firstClick.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#__next > div.sc-1l5foaq-0.gCyyUk.sc-13r6hla-1.jSZuSH > div.sc-1hmcglv-0.iiFycX > div > div > div > button > div")));
        driver.findElement(By.cssSelector("#__next > div.sc-1l5foaq-0.gCyyUk.sc-13r6hla-1.jSZuSH > div.sc-1hmcglv-0.iiFycX > div > div > div > button > div")).click();
        driver.findElement(By.cssSelector("#__next > div.sc-1l5foaq-0.gCyyUk.sc-13r6hla-1.jSZuSH > div.sc-1l5foaq-1.knewyH > div > section > div.sc-18q05a6-0.HLCRD > button")).click();
        WebElement coursesElement = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[2]/div/section/div[2]/div"));
        int courseCount = coursesElement.findElements(By.tagName("a")).size();
        log.info("Количество курсов на странице: "+ courseCount);
        Assertions.assertEquals(courseCount, 12);
        if (driver != null)
            driver.close();
    }

    @Test
    public void test2(){
        setUp();
        driver.get("https://otus.ru/lessons/qa-engineer/");
        WebDriverWait click = new WebDriverWait(driver, Duration.ofSeconds(10));
        click.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cookieButton)));
        driver.findElement(By.cssSelector(cookieButton)).click();
//                Просмотр карточки курса:
//                1 Пользователь переходит на карточку курса
        //                2 В карточке указана информация о курсе:
        //•	Название
        Assertions.assertTrue(driver.findElement(By.cssSelector("#__next > div.sc-1l5foaq-0.gCyyUk.sc-1gmm0i0-0.bHUXbO > div.sc-1l5foaq-1.knewyH > div > section > div.sc-1ddwpfq-0.zmSZx.sc-182qdc9-6.gAbKXT > div > div.x072mc-0.sc-182qdc9-5.fntjjk.fXJUGI > h1")).isDisplayed());
        //•	Описание
        WebElement courseDiscription = driver.findElement(By.cssSelector(("div[class='x072mc-0 sc-12wbpca-0 fntjjk bzlDEV']")));
        Assertions.assertTrue(courseDiscription.isDisplayed());
        //•	Длительность обучения
        Assertions.assertTrue(driver.findElement(By.cssSelector("#__next > div.sc-1l5foaq-0.gCyyUk.sc-1gmm0i0-0.bHUXbO > div.sc-1l5foaq-1.knewyH > div > section > div.x072mc-0.sc-10wn8wt-1.fntjjk.iKpZHT > div > div:nth-child(3) > p")).isDisplayed());
        //•	Формат // Минимально достаточное - проверить одну карточку. В идеале все в разделе тестирования.
        Assertions.assertTrue(driver.findElement(By.cssSelector("#__next > div.sc-1l5foaq-0.gCyyUk.sc-1gmm0i0-0.bHUXbO > div.sc-1l5foaq-1.knewyH > div > section > div.x072mc-0.sc-10wn8wt-1.fntjjk.iKpZHT > div > div:nth-child(5) > p")).isDisplayed());
        if (driver != null)
            driver.close();
    }

    @Test
    public void test3() {
        Logger log = LogManager.getLogger(LogTestClass.class);
        setUp();
//        Валидация дат предстоящих мероприятий:
//        1 Пользователь переходит в раздел События -> Календарь мероприятий
        driver.get("https://otus.ru/");

        WebDriverWait clickMain = new WebDriverWait(driver, Duration.ofSeconds(10));
        clickMain.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cookieButtonMain)));
//        driver.findElement(By.cssSelector(cookieButtonMain)).click();
        ((JavascriptExecutor) driver).executeScript(HIDE_COOKIE);
        // Нахождение кнопки принятия cookie-файлов и клик на нее
        // Нахождение элемента меню "Обучение" и наведение на него курсора
        WebElement educationMenu = driver.findElement(By.xpath("//span[text()='Обучение']"));
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(educationMenu));
        actions.moveToElement(educationMenu).perform();

        // Нахождение элемента "Календарь мероприятий" и переход на страницу
        WebElement calendarLink = driver.findElement(By.cssSelector("body > div.body-wrapper > div > header > div > nav > div:nth-child(2) > div > div > div:nth-child(2) > div > a:nth-child(3)"));
        calendarLink.click();
        ((JavascriptExecutor) driver).executeScript(HIDE_COOKIE);

//        2 На странице отображаются карточки предстоящих мероприятий.
        WebElement eventElements = driver.findElement(By.className("dod_new-event"));

        Assertions.assertTrue(eventElements.isDisplayed());
        log.info("Найдены мероприятия");
//        3 Даты проведения мероприятий больше или равны текущей дате
        String eventRawDate = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/section/div[1]/a[1]/div/div[5]/div[1]/span[1]/span[2]")).getText()+" 2023";
        log.info("Дата ближайшего мероприятия: "+ eventRawDate);
        // Преобразуем текст даты в объект LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDate eventDate = LocalDate.parse(eventRawDate, formatter);

        // Получаем текущую дату
        LocalDate currentDate = LocalDate.now();
        if (eventDate.isBefore(currentDate)) {
            log.error("Мероприятие прошло");
        } else if (eventDate.isEqual(currentDate)) {
            log.info("Мероприятие сегодня");
        } else {
            log.info("Мероприятие начнется"+ eventDate);
        }
        if (driver != null)
            driver.close();
}




    @Test
    public void test4(){
        Logger log = LogManager.getLogger(LogTestClass.class);
        setUp();
        driver.get("https://otus.ru/");
        WebDriverWait clickMain = new WebDriverWait(driver, Duration.ofSeconds(10));
        clickMain.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cookieButtonMain)));
        ((JavascriptExecutor) driver).executeScript(HIDE_COOKIE);
        // Нахождение элемента меню "Обучение" и наведение на него курсора
        WebElement educationMenu = driver.findElement(By.xpath("//span[text()='Обучение']"));
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(educationMenu));
        actions.moveToElement(educationMenu).perform();

        // Нахождение элемента "Календарь мероприятий" и переход на страницу
        WebElement calendarLink = driver.findElement(By.cssSelector("body > div.body-wrapper > div > header > div > nav > div:nth-child(2) > div > div > div:nth-child(2) > div > a:nth-child(3)"));
        calendarLink.click();
        ((JavascriptExecutor) driver).executeScript(HIDE_COOKIE);
        WebElement eventElements = driver.findElement(By.className("dod_new-event"));
        Assertions.assertTrue(eventElements.isDisplayed());
        driver.findElement(By.cssSelector("body > div.body-wrapper > div > div.js-dod-new-events-root > div > section > header > div.dod_new-events__header-left > div > div.dod_new-events-dropdown__input > span")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Открытый вебинар')]")).click();
        String textFromEvent = driver.findElement(By.cssSelector("body > div.body-wrapper > div > div.js-dod-new-events-root > div > section > div.dod_new-events__list.js-dod_new_events > a:nth-child(1) > div > div.dod_new-event__type > div > div.dod_new-type__text")).getText();
        Assertions.assertEquals(textFromEvent, "Открытый вебинар");
        if (driver != null)
            driver.close();
    }





}



