package ru.stqa.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header extends BasePage {

    public Header(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//div[@id='cart']//a[@class='content']//span[@class='quantity']")
    public WebElement cartQuantity;
}
