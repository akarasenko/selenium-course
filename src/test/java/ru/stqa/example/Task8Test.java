package ru.stqa.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class Task8Test {
    private WebDriver driver;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities)driver).getCapabilities());
    }

    @Test
    public void task7Test() {
        driver.navigate().to("http://localhost/litecart/en/");

        List<WebElement> items = driver.findElements(By.cssSelector(".product"));

        for (WebElement item : items) {
            List<WebElement> icons = item.findElements(By.cssSelector(".sticker"));
            Assert.assertEquals(icons.size(), 1);
        }
    }

    @After
    public void stop() {
        driver.close();
    }
}
