package ru.stqa.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {
    public String mainPageUrl = "http://localhost/litecart/en/";

    public String productXpath = "//div[@id='box-most-popular']//li[@class='product column shadow hover-light'][1]";
    public String cartXpath = "//div[@id='cart']//a[@class='link']";

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement randomProduct() {
        return driver.findElement(By.xpath(productXpath));
    }

    public WebElement toCartButton() {
        return driver.findElement(By.xpath(cartXpath));
    }

    public void goTo() {
        driver.navigate().to(mainPageUrl);
    }
}
