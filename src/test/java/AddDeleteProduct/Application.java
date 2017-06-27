package AddDeleteProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * Created by anna8 on 27.06.2017.
 */
public class Application extends TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    public MainPage mainPage;
    public CommonMethods commonMethods;
    public ProductPage productPage;
    public CartPage cartPage;

    public Application(){
        //FirefoxOptions options = new FirefoxOptions().setLegacy(true);
        //driver = new FirefoxDriver(options);
        //driver = new InternetExplorerDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        commonMethods = new CommonMethods(driver);
    }

    public void loginToAdminPage() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("loginToAdminPage")).click();
    }

    public void quit(){
        driver.quit();
        driver = null;
    }

    public void goToMainPage(){
        driver.navigate().to("http://localhost/litecart/en/");
    }


    public void addItemstoCart(){
        while (mainPage.findNumberOfProductsInCart()<3){
            int prevNumber=mainPage.findNumberOfProductsInCart();
            mainPage.goToProductPage();
            productPage.addQuantityforProduct();
            productPage.clickAddButton();
            wait.until(ExpectedConditions.attributeToBe(By.cssSelector("span.quantity"),"textContent",Integer.toString(prevNumber+1)));
            goToMainPage();
        }
        mainPage.clickCheckout();
    }

    public void removeItemsFromCart(){
        List<WebElement> productList = cartPage.findAllItemsInCart();
        for(int i=0; i<productList.size()-1;i++){
            List<WebElement> productListDyn = cartPage.findAllItemsInCart();
            productListDyn.get(0).click();
            String name = cartPage.getProductNAme();
            cartPage.clickRemoveButton();
            List<WebElement> tableList = cartPage.getListFromTable();
            assertFalse(cartPage.isProductInTable(tableList,name));
        }
        cartPage.clickRemoveButton();
        cartPage.verifyThatCartIsEmpty();
    }
}
