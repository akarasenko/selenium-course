package ru.stqa.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    public String mainPageUrl = "http://localhost/litecart/en/";

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//div[@id='box-most-popular']//li[@class='product column shadow hover-light'][1]")
    public WebElement randomProduct;

    @FindBy(xpath = "//div[@id='cart']//a[@class='link']")
    public WebElement toCartButton;

    public void open() {
        driver.navigate().to(mainPageUrl);
    }
}
