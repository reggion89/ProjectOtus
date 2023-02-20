import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class TestSet extends BaseTest {
        private Logger logger = LogManager.getLogger(TestSet.class);
    private String cookieButton = "#__next > div.sc-1l5foaq-0.gCyyUk.sc-1gmm0i0-0.bHUXbO > div.sc-1hmcglv-0.iiFycX > div > div > div > button > div";





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


    }





}



