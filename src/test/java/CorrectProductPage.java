import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static org.junit.Assert.*;

/**
 * Created by anna8 on 12.06.2017.
 */
public class CorrectProductPage extends TestBase {


    public  boolean isColorRed(String color){

       // if(driver instanceof ChromeDriver) {
            color = color.replace("rgba(", "").replace(")", "");
            String[] rgba = color.split(", ");
            if ((!rgba[0].equals("0")) && rgba[1].equals("0") && rgba[2].equals("0")) {
                return true;
            } else {
                return false;
            }
        /*}else if (driver instanceof FirefoxDriver){
            color = color.replace("rgb(", "").replace(")", "");
            String[] rgba = color.split(", ");
            if ((!rgba[0].equals("0")) && rgba[1].equals("0") && rgba[2].equals("0")) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }*/
    }

    public  boolean isColorGray(String color){

       // if(driver instanceof ChromeDriver) {
            color = color.replace("rgba(", "").replace(")", "");
            String[] rgba = color.split(", ");
            if (rgba[0].equals(rgba[1]) && rgba[1].equals(rgba[2])) {
                return true;
            } else {
                return false;
            }
        /*}else if(driver instanceof FirefoxDriver){
            color = color.replace("rgb(", "").replace(")", "");
            String[] rgba = color.split(", ");
            if (rgba[0].equals(rgba[1]) && rgba[1].equals(rgba[2])) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }*/

    }

    @Test
    public void verifyCorrectProductPage(){

        driver.navigate().to("http://localhost/litecart");

        //Elements on main page
        WebElement product = driver.findElement(By.cssSelector("#box-campaigns li:first-of-type"));
        WebElement regularPriceEl = product.findElement(By.cssSelector(".regular-price"));
        WebElement campaignPriceEl = product.findElement(By.cssSelector(".campaign-price"));

        String productName = product.findElement(By.cssSelector("div.name")).getAttribute("textContent");
        String productPrice = regularPriceEl.getAttribute("textContent");
        String campaignPrice = campaignPriceEl.getAttribute("textContent");

        //Verify that regular price is crossed out and campaign is bold on main page
        if(driver instanceof InternetExplorerDriver){
            assertEquals("line-through", regularPriceEl.getCssValue("textDecoration"));
        }else {
            assertEquals("line-through", regularPriceEl.getCssValue("text-decoration-line"));
        }

        if(driver instanceof FirefoxDriver || driver instanceof InternetExplorerDriver) {
            assertTrue(Integer.parseInt(campaignPriceEl.getCssValue("font-weight"))>=700);

        }else{
            assertEquals("bold", campaignPriceEl.getCssValue("font-weight"));
        }

        //verify that campaign price size bigger than regular size on main page
        double regularPriceSize = Double.parseDouble(regularPriceEl.getCssValue("font-size").replace("px",""));
        double campaignPriceSize = Double.parseDouble(campaignPriceEl.getCssValue("font-size").replace("px",""));
        assertTrue(campaignPriceSize>regularPriceSize);


        //verify colors
        String colorRegular = regularPriceEl.getCssValue("color");
        assertTrue(isColorGray(colorRegular));
        String colorCampaign = campaignPriceEl.getCssValue("color");
        assertTrue(isColorRed(colorCampaign));


        //Elements on detailed page
        driver.findElement(By.cssSelector("#box-campaigns li:first-of-type>a.link")).click();
        WebElement regularPriceDetailedElement = driver.findElement(By.cssSelector(".regular-price"));
        WebElement campaignPriceDetailedElement = driver.findElement(By.cssSelector(".campaign-price"));

        String productNameDetailed = driver.findElement(By.cssSelector("h1.title")).getAttribute("textContent");
        String productPriceDetailed = regularPriceDetailedElement.getAttribute("textContent");
        String campaignPriceDetailed = campaignPriceDetailedElement.getAttribute("textContent");

        //verify that campaign price size bigger than regular size on detailed page
        double regularPriceSizeDetailed = Double.parseDouble(regularPriceDetailedElement.getCssValue("font-size").replace("px",""));
        double campaignPriceSizeDetailed = Double.parseDouble(campaignPriceDetailedElement.getCssValue("font-size").replace("px",""));
        assertTrue(campaignPriceSizeDetailed>regularPriceSizeDetailed);

        //Verify that regular price is crossed out and campaign is bold on detailed page
        if(driver instanceof InternetExplorerDriver){
            assertEquals("line-through", regularPriceDetailedElement.getCssValue("textDecoration"));
        }else {
            assertEquals("line-through", regularPriceDetailedElement.getCssValue("text-decoration-line"));

        }if(driver instanceof ChromeDriver) {
            assertEquals("bold", campaignPriceDetailedElement.getCssValue("font-weight"));
        }else if(driver instanceof FirefoxDriver || driver instanceof InternetExplorerDriver){
            assertTrue(Integer.parseInt(campaignPriceDetailedElement.getCssValue("font-weight"))>=700);
        }

        //Verify colors
        String colorRegularDetailed = regularPriceDetailedElement.getCssValue("color");
        assertTrue(isColorGray(colorRegularDetailed));
        String colorCampaignPriceDetailed = campaignPriceDetailedElement.getCssValue("color");
        assertTrue(isColorRed(colorCampaignPriceDetailed));

        //Verify that product has same name and price on main and detailed pages
        assertEquals(productName,productNameDetailed);
        assertEquals(productPrice,productPriceDetailed);
        assertEquals(campaignPrice, campaignPriceDetailed);
    }
}
