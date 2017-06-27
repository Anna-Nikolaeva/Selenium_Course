package AddDeleteProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by anna8 on 27.06.2017.
 */
public class ProductPage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected CommonMethods commonMethods;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        commonMethods = new CommonMethods(driver);
    }

    public void addQuantityforProduct(){
        if(commonMethods.isElementPresent(By.cssSelector("select[name='options[Size]']"))){
            commonMethods.selectItemInDropdown(driver.findElement(By.cssSelector("select[name='options[Size]']")),"Medium");
        }
    }

    public void clickAddButton(){driver.findElement(By.name("add_cart_product")).click();}
}
