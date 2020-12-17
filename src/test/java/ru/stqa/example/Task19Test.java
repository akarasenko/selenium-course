package ru.stqa.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class Task19Test {
    WebDriver driver;
    Wait wait;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities) driver).getCapabilities());

        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void task13Test() {
        goTo("http://localhost/litecart/en/rubber-ducks-c-1/");

        var productCount = 3; // how many product you want to add to cart

        for (int i = 0; i<productCount; i++) {
            addRandomProductToCart();
            goTo("http://localhost/litecart/en/rubber-ducks-c-1/");
        }

        goToCart();

        var productAmount = getProductAmountInCart();

        for (int i =0; i < productAmount; i++) {
            deleteRandomProductFromCart();
        }
    }

    public void addRandomProductToCart () {
        var cartQuantityXpath = "//div[@id='cart']//a[@class='content']//span[@class='quantity']";
        var addToCatrXpath = "//button[@name='add_cart_product']";
        var productXpath = "//div[@id='box-most-popular']//li[@class='product column shadow hover-light'][1]";

        var initQuantity = Integer.parseInt(driver.findElement(By.xpath(cartQuantityXpath)).getText());
        driver.findElement(By.xpath(productXpath)).click();
        driver.findElement(By.xpath(addToCatrXpath)).click();

        wait.until(textToBe(By.xpath(cartQuantityXpath), String.valueOf(initQuantity + 1)));
    }

    public void goTo(String url) {
        driver.navigate().to(url);
    }

    public void goToCart() {
        var cartXpath = "//div[@id='cart']//a[@class='link']";
        driver.findElement(By.xpath(cartXpath)).click();
    }

    public int getProductAmountInCart () {
        var productTableXpath = "//div[@id='box-checkout-cart']//ul[@class='shortcuts']//li";
        return driver.findElements(By.xpath(productTableXpath)).size();
    }


    public void deleteRandomProductFromCart() {
        var nameXpath = "//form[@name='cart_form']//div//p//a//strong";
        var deleteXpath = "//button[@name='remove_cart_item']";

        var productName = driver.findElement(By.xpath(nameXpath)).getText();
        var productInTableXpath = "//div[@id='order_confirmation-wrapper']//tbody//tr//td[@class='item'][text()='" + productName + "']";
        var tableProduct = driver.findElement(By.xpath(productInTableXpath));

        driver.findElement(By.xpath(deleteXpath)).click();
        wait.until(stalenessOf(tableProduct));
    }

    @After
    public void stop() {
        driver.close();
    }
}
