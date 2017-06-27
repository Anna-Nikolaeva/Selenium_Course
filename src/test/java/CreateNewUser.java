import AddDeleteProduct.TestBase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

/**
 * Created by anna8 on 12.06.2017.
 */
public class CreateNewUser extends TestBase {

    @Test
    public void createNewUser(){
        app.driver.navigate().to("http://localhost/litecart/en/");
        app.driver.findElements(By.cssSelector("a[href*=create_account]")).get(0).click();
        app.driver.findElement(By.name("tax_id")).sendKeys("777");
        app.driver.findElement(By.name("company")).sendKeys("Company");
        app.driver.findElement(By.name("firstname")).sendKeys("FirstName");
        app.driver.findElement(By.name("lastname")).sendKeys("LastName");
        app.driver.findElement(By.name("address1")).sendKeys("address1");
        app.driver.findElement(By.name("address2")).sendKeys("address2");
        app.driver.findElement(By.name("postcode")).sendKeys("22222");
        app.driver.findElement(By.name("city")).sendKeys("city");
        Select countryDropdown = new Select(app.driver.findElement(By.name("country_code")));
        countryDropdown.selectByValue("US");

        app.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Select stateDropdown = new Select (app.driver.findElement(By.cssSelector("select[name=zone_code]")));
        System.out.println(stateDropdown.getOptions());
        stateDropdown.selectByValue("AK");

        String email = "email"+Math.random()+"@d.c";
        app.driver.findElement(By.name("email")).sendKeys(email);
        app.driver.findElement(By.name("phone")).sendKeys("+380274963481");
        String pass = "password";
        app.driver.findElement(By.name("password")).sendKeys(pass);
        app.driver.findElement(By.name("confirmed_password")).sendKeys(pass);


        app.driver.findElement(By.name("create_account")).click();

        app.driver.findElement(By.cssSelector("a[href*=logout]")).click();
        app.driver.findElement(By.name("email")).sendKeys(email);
        app.driver.findElement(By.name("password")).sendKeys(pass);
        app.driver.findElement(By.cssSelector("button[name=loginToAdminPage]")).click();
        app.driver.findElements(By.cssSelector("a[href*=logout]")).get(1).click();


    }
}
