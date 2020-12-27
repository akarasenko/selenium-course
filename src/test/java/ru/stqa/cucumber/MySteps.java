package ru.stqa.cucumber;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.stqa.example.application.Application;


public class MySteps {
    public static Application app = new Application();

    @Given("go to main page")
    public void goToMainPage() {
        app.gotoMainPage();
    }

    @And("add {string} random products to cart")
    public void addRandomProductsToCart(int productCount) {
        for (int i = 0; i < productCount; i++) {
            app.addRandomProductToCart();
            app.gotoMainPage();
        }
    }

    @Then("go to cart")
    public void goToCart() {
        app.goToCart();
    }

    @And("delete all products from cart")
    public void deleteAllProductsFromCart() {
        var productAmount = app.getProductAmountInCart();

        for (int i = 0; i < productAmount; i++) {
            app.deleteRandomProductFromCart();
        }
    }
}
