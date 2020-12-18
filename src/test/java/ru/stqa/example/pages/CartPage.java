package ru.stqa.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//div[@id='order_confirmation-wrapper']//tbody//tr//td[@class='item']")
    public List<WebElement> productRows;

    @FindBy(xpath = "//form[@name='cart_form']//div//p//a//strong")
    public WebElement selectedProductName;

    //  не удалось заменить на поле
    public String productTableXpath = "//div[@id='order_confirmation-wrapper']//tbody//tr//td[@class='item']";
    public WebElement productInTable(String productName) {
        var productInTableXpath = "//div[@id='order_confirmation-wrapper']//tbody//tr//td[@class='item'][text()='" + productName + "']";
        return driver.findElement(By.xpath(productInTableXpath));
    }

    @FindBy(xpath = "//button[@name='remove_cart_item']")
    public WebElement deleteButton;
}
