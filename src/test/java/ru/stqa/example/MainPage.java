package ru.stqa.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class MainPage extends BasePage {
    private String mainPageurl = "http://localhost/litecart/en/";

    private String cartQuantityXpath = "//div[@id='cart']//a[@class='content']//span[@class='quantity']";
    private String addToCatrXpath = "//button[@name='add_cart_product']";
    private String productXpath = "//div[@id='box-most-popular']//li[@class='product column shadow hover-light'][1]";
    private String cartXpath = "//div[@id='cart']//a[@class='link']";

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement cartQuantity() {
        return driver.findElement(By.xpath(cartQuantityXpath));
    }

    public WebElement addToCartButton() {
        return driver.findElement(By.xpath(addToCatrXpath));
    }

    public WebElement product() {
        return driver.findElement(By.xpath(productXpath));
    }

    public WebElement toCartButton() {
        return driver.findElement(By.xpath(cartXpath));
    }

    void goTo() {
        driver.navigate().to(mainPageurl);
    }
}
