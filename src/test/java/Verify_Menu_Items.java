/**
 * Created by anna8 on 03.06.2017.
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
public class Verify_Menu_Items {

    private WebDriver driver;
    private WebDriverWait wait;

    public void Login() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public boolean findH1(){
        try {
            driver.findElement(By.tagName("h1"));
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void Click_Menu_Items() {
        Login();

        List<WebElement> initialList = driver.findElements(By.cssSelector("ul#box-apps-menu > li#app-"));

        for (int i=0;i<initialList.size();i++){
            List<WebElement> listDyn= driver.findElements(By.cssSelector("ul#box-apps-menu > li#app-"));
            listDyn.get(i).click();

            List<WebElement> initialSubList = driver.findElements(By.cssSelector("ul.docs > li"));
            if(initialSubList.size()>0) {
                for (int j = 0; j < initialSubList.size(); j++) {
                    List<WebElement> subListDyn = driver.findElements(By.cssSelector("ul.docs > li"));
                    subListDyn.get(j).click();

                    assertTrue(findH1());
                }
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}


