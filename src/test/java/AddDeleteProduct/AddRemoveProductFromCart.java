package AddDeleteProduct;

import org.junit.Test;

/**
 * Created by anna8 on 15.06.2017.
 */
public class AddRemoveProductFromCart extends TestBase {

    @Test
    public void verifyThatProductIsAddedAndDeletedFromCart(){
        app.goToMainPage();
        app.addItemstoCart();
        app.removeItemsFromCart();
    }
}
