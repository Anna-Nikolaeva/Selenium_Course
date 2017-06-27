package AddDeleteProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by anna8 on 27.06.2017.
 */
public class CommonMethods {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void selectItemInDropdown(WebElement element, String option){
        Select select = new Select(element);
        select.selectByValue(option);
    }

    public boolean isElementPresent(By locator){
        try {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        }finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
    }
}
