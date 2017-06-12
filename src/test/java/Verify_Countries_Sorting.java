/**
 * Created by anna8 on 04.06.2017.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class Verify_Countries_Sorting extends TestBase {

    @Test
    public void sortingCountries() {
        Login();
        driver.findElement(By.cssSelector("a[href*=countries]")).click();

        List<String>countriesNamesList = new ArrayList<String>();
        List<String> indexesOfCountriesWithZones = new ArrayList<String>();
        List<WebElement> countriesList = driver.findElements(By.cssSelector("tr.row"));
        int i=2;
        for (WebElement el: countriesList) {
            countriesNamesList.add(el.findElement(By.cssSelector("td:nth-of-type(5) a")).getAttribute("textContent"));

            if(!el.findElement(By.cssSelector("td:nth-of-type(6)")).getAttribute("textContent").equals("0")) {
                indexesOfCountriesWithZones.add(String.valueOf(i));
            }
            i++;
        }

        assertTrue(verifySorting(countriesNamesList));


        for(String str: indexesOfCountriesWithZones) {
            driver.findElement(By.cssSelector("tr.row:nth-of-type(" + str + ") td:nth-of-type(5) a")).click();
            List<String> stringOfZones = new ArrayList<String>();
            List<WebElement> listOfZones = driver.findElements(By.cssSelector("table#table-zones td:nth-of-type(3) input[type=hidden]"));

            for(WebElement el: listOfZones){
                stringOfZones.add(el.getAttribute("value"));
            }

            assertTrue(verifySorting(stringOfZones));
            driver.navigate().back();
        }
    }
}
