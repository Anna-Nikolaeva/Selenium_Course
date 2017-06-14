import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by anna8 on 08.06.2017.
 */
public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    public void Login() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Before
    public void start(){
        //FirefoxOptions options = new FirefoxOptions().setLegacy(true);
         //driver = new FirefoxDriver(options);
        //driver = new InternetExplorerDriver();
        driver = new ChromeDriver();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

    public boolean verifySorting(List<String> list){

        String previous = "";

        for (String str: list) {
            if (str.compareTo(previous) < 0)
                return false;
            previous = str;
        }

        return true;
    }

    public void selectItemInDropdown(WebElement element, String option){
        Select select = new Select(element);
        select.selectByValue(option);
    }
}
