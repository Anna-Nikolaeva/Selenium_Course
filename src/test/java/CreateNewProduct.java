import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

/**
 * Created by anna8 on 13.06.2017.
 */
public class CreateNewProduct extends TestBase {

    @Test
    public void verifyProductCreation(){
        Login();
        driver.findElement(By.cssSelector("a[href*=catalog]")).click();
        driver.findElement(By.cssSelector("a[href*=edit_product]")).click();
        driver.findElement(By.cssSelector("input[value='1'][name=status]")).click();
        String name = "Black Bear";
        driver.findElement(By.name("name[en]")).sendKeys(name);
        driver.findElement(By.name("code")).sendKeys("code");
        driver.findElement(By.xpath("//input[@value='1' and @type='checkbox']")).click();
        selectItemInDropdown(driver.findElement(By.name("default_category_id")),"1");
        driver.findElement(By.xpath("//input[@value='1-3']"));
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("50");
        String currentDir = System.getProperty("user.dir");
        driver.findElement(By.cssSelector("input[type=file]")).sendKeys(currentDir + "\\src\\test\\java\\bear.png");
        driver.findElement(By.name("date_valid_from")).sendKeys("12122016");
        driver.findElement(By.name("date_valid_to")).sendKeys("11112017");
        driver.findElement(By.cssSelector("a[href*=tab-information]")).click();

        //Information tab
        Select manufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturer.selectByValue("1");
        driver.findElement(By.name("keywords")).sendKeys("keywords");
        driver.findElement(By.name("short_description[en]")).sendKeys("short_description");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Description, long Description");
        driver.findElement(By.name("head_title[en]")).sendKeys("head_title[en]");
        driver.findElement(By.name("meta_description[en]")).sendKeys("meta_description[en]");
        driver.findElement(By.cssSelector("a[href*=tab-prices]")).click();
        
        //Prices tab
        WebElement purchase_price = driver.findElement(By.name("purchase_price"));
        purchase_price.clear();
        purchase_price.sendKeys("27");
        selectItemInDropdown(driver.findElement(By.name("purchase_price_currency_code")),"EUR");
        driver.findElement(By.name("prices[USD]")).sendKeys("26");
        driver.findElement(By.name("prices[EUR]")).sendKeys("27");
        driver.findElement(By.name("save")).click();
        //Verification that product was added
        driver.findElement(By.cssSelector("a[href*='catalog&category_id=1']")).click();
        List<WebElement> listOfProducts = driver.findElements(By.cssSelector("td:nth-of-type(3)>a[href*=product_id]"));
        assertTrue(findProduct(name,listOfProducts));
    }

    public boolean findProduct(String name, List<WebElement> list){

        for(WebElement el:list){
            if (el.getAttribute("textContent").equals(name)){
                return true;
            }
        }
        return false;
    }
}
