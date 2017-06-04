/**
 * Created by anna8 on 04.06.2017.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Verify_Stickers {

    private WebDriver driver;
    private WebDriverWait wait;

    public boolean findSticker(WebElement element){

        List<WebElement> stickers = element.findElements(By.cssSelector("div.sticker"));

        if(stickers.size() == 1){
            return true;
        } else {
            return false;
        }
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void verifySticker() {

        driver.navigate().to("http://localhost/litecart/en/");

        List<WebElement> productList = driver.findElements(By.cssSelector("div.content li.product"));

        for (WebElement el: productList){
            assertTrue(findSticker(el));
        }

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
