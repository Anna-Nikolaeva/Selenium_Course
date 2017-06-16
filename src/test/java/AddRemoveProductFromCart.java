import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by anna8 on 15.06.2017.
 */
public class AddRemoveProductFromCart extends TestBase{

    public int findNumberOfProductsInCart(){
        WebElement numberOfPruducts = driver.findElement(By.cssSelector("span.quantity"));
        return Integer.parseInt(numberOfPruducts.getAttribute("textContent"));
    }

    public boolean isElementPresent(By locator){
        try {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        }finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
    }

    @Test
    public void verifyThatPruductIsAddedAndDeletedFromCart(){
        driver.navigate().to("http://localhost/litecart/en/");

        //adding items
        while (findNumberOfProductsInCart()<3){
            int prevNumber=findNumberOfProductsInCart();
            driver.findElement(By.cssSelector("li.product>a[href*=http]")).click();
            if(isElementPresent(By.cssSelector("select[name='options[Size]']"))){
                selectItemInDropdown(driver.findElement(By.cssSelector("select[name='options[Size]']")),"Medium");
            }
            driver.findElement(By.name("add_cart_product")).click();
            wait.until(ExpectedConditions.attributeToBe(By.cssSelector("span.quantity"),"textContent",Integer.toString(prevNumber+1)));
            driver.navigate().back();
        }
        driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();

        //removing items
        List<WebElement> productList = driver.findElements(By.cssSelector("a[href='#']"));
        for(int i=0; i<productList.size()-1;i++){
            List<WebElement> productListDyn = driver.findElements(By.cssSelector("a[href='#']"));
            productListDyn.get(0).click();
            String name = driver.findElement(By.cssSelector("a>strong")).getAttribute("textContent");
            driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.className("dataTable"))));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("dataTable")));
            List<WebElement> tableList = driver.findElements(By.cssSelector("td.item"));
            assertFalse(isProductInTable(tableList,name));
        }

        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("em")));
    }

    public boolean isProductInTable(List<WebElement> list, String name){
        for(WebElement el:list){
            if(el.getAttribute("textContent").equals(name)){
                return true;
            }
        }
        return false;
    }
}
