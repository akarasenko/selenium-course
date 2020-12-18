package ru.stqa.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {
    public String addToCartXpath = "//button[@name='add_cart_product']";

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement addToCartButton() {
        return driver.findElement(By.xpath(addToCartXpath));
    }
}

