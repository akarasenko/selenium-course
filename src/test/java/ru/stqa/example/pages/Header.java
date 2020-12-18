package ru.stqa.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header extends BasePage {
    public String cartQuantityXpath = "//div[@id='cart']//a[@class='content']//span[@class='quantity']";

    public Header(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement cartQuantity() {
        return driver.findElement(By.xpath(cartQuantityXpath));
    }
}
