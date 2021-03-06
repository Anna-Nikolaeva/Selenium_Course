import AddDeleteProduct.TestBase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by anna8 on 26.06.2017.
 */
public class MessagesinBrowserLog extends TestBase {

    @Test
    public void verifyMessageAbsenceInBrowserLog(){
        app.loginToAdminPage();
        app.driver.findElement(By.cssSelector("a[href*=catalog]")).click();
        app.driver.findElement(By.cssSelector("a[href*='category_id=1']")).click();
        List<WebElement> listOfProducts = app.driver.findElements(By.cssSelector("td:nth-of-type(3)>a[href*='product_id']"));
        for(int i=0;i<listOfProducts.size();i++){
            List<WebElement> listOfProductsDyn = app.driver.findElements(By.cssSelector("td:nth-of-type(3)>a[href*='product_id']"));
            listOfProductsDyn.get(i).click();
            System.out.println(app.driver.manage().logs().get("browser").getAll());
            app.driver.navigate().back();
        }
    }
}
