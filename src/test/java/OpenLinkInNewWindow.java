import AddDeleteProduct.TestBase;
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

        app.loginToAdminPage();
        app.driver.findElement(By.cssSelector("a[href*=countries]")).click();
        app.driver.findElement(By.cssSelector("a[href*='code=AR']")).click();

        List<WebElement> listOfIcons = app.driver.findElements(By.cssSelector("#content a[href*=http]"));
        String initialWindow = app.driver.getWindowHandle();
        Set<String> initialSetOfWindows =  app.driver.getWindowHandles();
        for(WebElement element:listOfIcons){
            element.click();
            String newWindow = getNewWindowHandle(initialSetOfWindows);
            app.driver.switchTo().window(newWindow);
            app.wait.until(ExpectedConditions.urlContains("http"));
            app.driver.close();
            app.driver.switchTo().window(initialWindow);
        }
    }

    public String getNewWindowHandle(Set<String> initialSetOfWindows){
        Set<String> newSetOfWindows = app.driver.getWindowHandles();
        newSetOfWindows.removeAll(initialSetOfWindows);
        if(newSetOfWindows.size()>0){
            return newSetOfWindows.iterator().next();
        }
        return null;
    }
}


