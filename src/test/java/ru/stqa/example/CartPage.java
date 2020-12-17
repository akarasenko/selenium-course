package ru.stqa.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends BasePage {
    private String productTableXpath = "//div[@id='order_confirmation-wrapper']//tbody//tr//td[@class='item']";
    private String nameXpath = "//form[@name='cart_form']//div//p//a//strong";
    private String deleteXpath = "//button[@name='remove_cart_item']";

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public List<WebElement> productRows () {
        return driver.findElements(By.xpath(productTableXpath));
    }

    public WebElement productName () {
        return driver.findElement(By.xpath(nameXpath));
    }

    public WebElement productInTable(String productName) {
        var productInTableXpath = "//div[@id='order_confirmation-wrapper']//tbody//tr//td[@class='item'][text()='" + productName + "']";
        return driver.findElement(By.xpath(productInTableXpath));
    }

    public WebElement deleteButton () {
        return driver.findElement(By.xpath(deleteXpath));
    }
}
