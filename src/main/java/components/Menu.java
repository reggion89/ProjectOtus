package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Menu extends AbsBaseComponent{

    public Menu(WebDriver driver) {
        super(driver);
    }

    private String learningMenuIsHidden = ".js-header3-popup[data-name='learning'][style='display: none;']";
    private String learningSubMenuNameSelector = ".header3__nav-column-item[href='%s']";
    private String learningSubMenuItemSelector = ".header3__nav-column-item[href='%s']";

    public void openMenuName(String name) {
        String menuNameSelector = ".header3__nav-item[data-name='%s']";
        perform(driver.findElement(By.cssSelector(String.format(menuNameSelector, name))));
    }

    public void clickLearningSubMenuName(String name) {
        waiter.waitForCondition(ExpectedConditions.not(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector(".js-header3-popup[data-name='learning'][style='display: none;']"))));
        waiter.waitForCondition(ExpectedConditions.visibilityOf((driver.findElement(By.cssSelector(String.format(learningSubMenuNameSelector, name))))));
        click(driver.findElement(By.cssSelector(String.format(learningSubMenuNameSelector, name))));
    }
}
