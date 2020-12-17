package ru.stqa.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class Application {

    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    CartPage cartPage;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        mainPage = new MainPage(driver, wait);
        cartPage = new CartPage(driver, wait);
    }

    public void addRandomProductToCart () {
        var initQuantity = Integer.parseInt(mainPage.cartQuantity().getText());
        mainPage.product().click();
        mainPage.addToCartButton().click();
        wait.until(textToBePresentInElement(mainPage.cartQuantity(), String.valueOf(initQuantity + 1)));
    }

    public void gotoMainPage() {
       mainPage.goTo();
    }

    public void quit() {
        driver.quit();
    }

    public void goToCart() {
        mainPage.toCartButton().click();
    }

    public int getProductAmountInCart () {
        return cartPage.productRows().size();
    }

    public void deleteRandomProductFromCart() {
        var productName = cartPage.productName().getText();
        var productInTable = cartPage.productInTable(productName);
        cartPage.deleteButton().click();
        wait.until(stalenessOf(productInTable));
    }
}
