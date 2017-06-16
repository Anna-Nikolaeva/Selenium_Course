import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

/**
 * Created by anna8 on 16.06.2017.
 */
public class OpenLinkInNewWindow extends TestBase {

    @Test
    public void verifyLinkIsOpenedInNewWindow(){

        Login();
        driver.findElement(By.cssSelector("a[href*=countries]")).click();
        driver.findElement(By.cssSelector("a[href*='code=AR']")).click();

        List<WebElement> listOfIcons = driver.findElements(By.cssSelector("#content a[href*=http]"));
        String initialWindow = driver.getWindowHandle();
        Set<String> initialSetOfWindows =  driver.getWindowHandles();
        for(WebElement element:listOfIcons){
            element.click();
            String newWindow = getNewWindowHandle(initialSetOfWindows);
            driver.switchTo().window(newWindow);
            wait.until(ExpectedConditions.urlContains("http"));
            driver.close();
            driver.switchTo().window(initialWindow);
        }
    }

    public String getNewWindowHandle(Set<String> initialSetOfWindows){
        Set<String> newSetOfWindows = driver.getWindowHandles();
        newSetOfWindows.removeAll(initialSetOfWindows);
        if(newSetOfWindows.size()>0){
            return newSetOfWindows.iterator().next();
        }
        return null;
    }
}


