/**
 * Created by anna8 on 08.06.2017.
 */

import org.junit.Test;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class Verify_Sorting_GeoZones extends TestBase {

    @Test
    public void verifyGeoZonesSorting(){
       Login();
       driver.findElement(By.cssSelector("a[href*=geo_zones]")).click();

       List<WebElement> numberOfCountries = driver.findElements(By.cssSelector("td:nth-of-type(3) >a"));
       for(int i=2;i<=numberOfCountries.size()+1;i++){

           List<WebElement> listOfCountries = driver.findElements(By.cssSelector("td:nth-of-type(3) >a"));
           listOfCountries.get(i).click();

           List<WebElement> listOfZones = driver.findElements(By.cssSelector("td:nth-of-type(3)>select"));
           List<String> listOfZoneNames = new ArrayList<String>();
           for(WebElement el:listOfZones){
               listOfZoneNames.add(el.findElement(By.cssSelector("[selected=selected]")).getAttribute("innerText"));
           }
           assertTrue(verifySorting(listOfZoneNames));
           driver.navigate().back();

       }

   }

}
