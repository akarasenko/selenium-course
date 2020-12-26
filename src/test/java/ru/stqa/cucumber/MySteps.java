package ru.stqa.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import ru.stqa.example.application.Application;


public class MySteps {
    public static Application app;

    @When("go to main page")
    public void goToMainPage() {
        app.gotoMainPage();
    }

    @And("add random product to cart")
    public void addRandomProductToCart() {
        app.addRandomProductToCart();
    }

    @When("go to cart")
    public void goToCart() {
        app.goToCart();
    }

    @And("delete random product from cart")
    public void deleteRandomProductFromCart() {
        app.deleteRandomProductFromCart();
    }
}
