import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

/**
 * Created by anna8 on 12.06.2017.
 */
public class CreateNewUser extends TestBase {

    @Test
    public void createNewUser(){
        driver.navigate().to("http://localhost/litecart/en/");
        driver.findElements(By.cssSelector("a[href*=create_account]")).get(0).click();
        driver.findElement(By.name("tax_id")).sendKeys("777");
        driver.findElement(By.name("company")).sendKeys("Company");
        driver.findElement(By.name("firstname")).sendKeys("FirstName");
        driver.findElement(By.name("lastname")).sendKeys("LastName");
        driver.findElement(By.name("address1")).sendKeys("address1");
        driver.findElement(By.name("address2")).sendKeys("address2");
        driver.findElement(By.name("postcode")).sendKeys("22222");
        driver.findElement(By.name("city")).sendKeys("city");
        Select countryDropdown = new Select(driver.findElement(By.name("country_code")));
        countryDropdown.selectByValue("US");

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Select stateDropdown = new Select (driver.findElement(By.cssSelector("select[name=zone_code]")));
        System.out.println(stateDropdown.getOptions());
        stateDropdown.selectByValue("AK");

        String email = "email"+Math.random()+"@d.c";
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+380274963481");
        String pass = "password";
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.name("confirmed_password")).sendKeys(pass);


        driver.findElement(By.name("create_account")).click();

        driver.findElement(By.cssSelector("a[href*=logout]")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.cssSelector("button[name=login]")).click();
        driver.findElements(By.cssSelector("a[href*=logout]")).get(1).click();


    }
}
