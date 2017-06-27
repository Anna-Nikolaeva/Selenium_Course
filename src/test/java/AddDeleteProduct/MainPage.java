package AddDeleteProduct; /**
 * Created by anna8 on 27.06.2017.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

        protected WebDriver driver;
        protected WebDriverWait wait;

        public MainPage(WebDriver driver) {
            this.driver = driver;
            wait = new WebDriverWait(driver, 10);
        }

    public int findNumberOfProductsInCart(){
        WebElement numberOfProducts = driver.findElement(By.cssSelector("span.quantity"));
        return Integer.parseInt(numberOfProducts.getAttribute("textContent"));
    }

    public void goToProductPage(){driver.findElement(By.cssSelector("li.product>a[href*=http]")).click();}
    public void clickCheckout(){driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();}


}
