package ru.stqa.example.application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.example.pages.CartPage;
import ru.stqa.example.pages.Header;
import ru.stqa.example.pages.MainPage;
import ru.stqa.example.pages.ProductPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class Application {

    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    CartPage cartPage;
    ProductPage productPage;
    Header header;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        mainPage = new MainPage(driver, wait);
        cartPage = new CartPage(driver, wait);
        productPage = new ProductPage(driver, wait);
        header = new Header(driver, wait);
    }

    public void addRandomProductToCart () {
        var initQuantity = Integer.parseInt(header.cartQuantity.getText());
        mainPage.randomProduct.click();
        productPage.addToCartButton.click();
        wait.until(textToBePresentInElement(header.cartQuantity, String.valueOf(initQuantity + 1)));
    }

    public void gotoMainPage() {
       mainPage.open();
    }

    public void quit() {
        driver.quit();
    }

    public void goToCart() {
        mainPage.toCartButton.click();
    }

    public int getProductAmountInCart () {
        return cartPage.productRows.size();
    }

    public void deleteRandomProductFromCart() {
        var productName = cartPage.selectedProductName.getText();
        var productInTable = cartPage.productInTable(productName);
        cartPage.deleteButton.click();
        wait.until(stalenessOf(productInTable));
    }
}
