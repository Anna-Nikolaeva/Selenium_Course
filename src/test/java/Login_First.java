/**
 * Created by anna8 on 29.05.2017.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
public class Login_First {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new InternetExplorerDriver();
    }

    @Test
    public void Login() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("loginToAdminPage")).click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
