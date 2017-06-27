package AddDeleteProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by anna8 on 27.06.2017.
 */
public class CartPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public List<WebElement> findAllItemsInCart(){return driver.findElements(By.cssSelector("a[href='#']"));}
    public String getProductNAme(){return driver.findElement(By.cssSelector("a>strong")).getAttribute("textContent");}
    public void clickRemoveButton(){driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();}

    public List<WebElement> getListFromTable(){
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.className("dataTable"))));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("dataTable")));
        return driver.findElements(By.cssSelector("td.item"));
    }

    public boolean isProductInTable(List<WebElement> list, String name){
        for(WebElement el:list){
            if(el.getAttribute("textContent").equals(name)){
                return true;
            }
        }
        return false;
    }

    public void verifyThatCartIsEmpty(){wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("em")));}


}
