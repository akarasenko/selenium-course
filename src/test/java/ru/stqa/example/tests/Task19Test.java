package ru.stqa.example.tests;
import org.junit.Test;

public class Task19Test extends TestBase {

    @Test
    public void task19Test() {
        app.gotoMainPage();

        var productCount = 1; // how many product you want to add to cart

        for (int i = 0; i < productCount; i++) {
            app.addRandomProductToCart();
            app.gotoMainPage();
        }

        app.goToCart();

        var productAmount = app.getProductAmountInCart();

        for (int i = 0; i < productAmount; i++) {
            app.deleteRandomProductFromCart();
        }
    }
}
