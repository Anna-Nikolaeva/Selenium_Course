package AddDeleteProduct;

import org.junit.After;
import org.junit.Before;

import java.util.List;

/**
 * Created by anna8 on 08.06.2017.
 */
public class TestBase {

    public Application app;


    @Before
    public void start(){
        app = new Application();
    }

    @After
    public void stop(){
        app.quit();
    }

    public boolean verifySorting(List<String> list){

        String previous = "";

        for (String str: list) {
            if (str.compareTo(previous) < 0)
                return false;
            previous = str;
        }

        return true;
    }
}
