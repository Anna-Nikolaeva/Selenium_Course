import AddDeleteProduct.TestBase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by anna8 on 13.06.2017.
 */
public class CreateNewProduct extends TestBase {

    @Test
    public void verifyProductCreation(){
        app.loginToAdminPage();
        app.driver.findElement(By.cssSelector("a[href*=catalog]")).click();
        app.driver.findElement(By.cssSelector("a[href*=edit_product]")).click();
        app.driver.findElement(By.cssSelector("input[value='1'][name=status]")).click();
        String name = "Black Bear";
        app.driver.findElement(By.name("name[en]")).sendKeys(name);
        app.driver.findElement(By.name("code")).sendKeys("code");
        app.driver.findElement(By.xpath("//input[@value='1' and @type='checkbox']")).click();
        //app.selectItemInDropdown(driver.findElement(By.name("default_category_id")),"1");
        app.driver.findElement(By.xpath("//input[@value='1-3']"));
        app.driver.findElement(By.name("quantity")).clear();
        app.driver.findElement(By.name("quantity")).sendKeys("50");
        String currentDir = System.getProperty("user.dir");
        app.driver.findElement(By.cssSelector("input[type=file]")).sendKeys(currentDir + "\\src\\test\\java\\bear.png");
        app.driver.findElement(By.name("date_valid_from")).sendKeys("12122016");
        app.driver.findElement(By.name("date_valid_to")).sendKeys("11112017");
        app.driver.findElement(By.cssSelector("a[href*=tab-information]")).click();

        //Information tab
        Select manufacturer = new Select(app.driver.findElement(By.name("manufacturer_id")));
        manufacturer.selectByValue("1");
        app.driver.findElement(By.name("keywords")).sendKeys("keywords");
        app.driver.findElement(By.name("short_description[en]")).sendKeys("short_description");
        app.driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Description, long Description");
        app.driver.findElement(By.name("head_title[en]")).sendKeys("head_title[en]");
        app.driver.findElement(By.name("meta_description[en]")).sendKeys("meta_description[en]");
        app.driver.findElement(By.cssSelector("a[href*=tab-prices]")).click();

        //Prices tab
        WebElement purchase_price = app.driver.findElement(By.name("purchase_price"));
        purchase_price.clear();
        purchase_price.sendKeys("27");
        //selectItemInDropdown(driver.findElement(By.name("purchase_price_currency_code")),"EUR");
        app.driver.findElement(By.name("prices[USD]")).sendKeys("26");
        app.driver.findElement(By.name("prices[EUR]")).sendKeys("27");
        app.driver.findElement(By.name("save")).click();
        //Verification that product was added
        app.driver.findElement(By.cssSelector("a[href*='catalog&category_id=1']")).click();
        List<WebElement> listOfProducts = app.driver.findElements(By.cssSelector("td:nth-of-type(3)>a[href*=product_id]"));
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
