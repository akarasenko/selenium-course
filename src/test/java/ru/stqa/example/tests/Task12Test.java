package ru.stqa.example.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class Task12Test {
    WebDriver driver;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities)driver).getCapabilities());
    }

    @Test
    public void task12Test() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("123qwe");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//span[text()='Catalog']")).click();

        driver.findElement(By.xpath("//a[@class='button'][2]")).click();

        var randomPart = Long.toString(Math.round(Math.random() * 100000));
        var productName = "New product" + randomPart;

        driver.findElement(By.xpath("//input[@name='status'][@value='1']")).click();
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(productName);
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='product_groups[]'][@value='1-2']")).click();

        var quantity = driver.findElement(By.xpath("//input[@name='quantity']"));
        quantity.clear();
        quantity.sendKeys("100");

        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys(Keys.HOME + "01.01.2021");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys(Keys.HOME + "01.04.2021");

        File file = new File("duck.jpg");
        String absolutePath = file.getAbsolutePath();
        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(absolutePath);

        driver.findElement(By.xpath("//div[@class='tabs']//li//a[text()='Information']")).click();
        Thread.sleep(30);

        var manufacturer = new Select(driver.findElement(By.xpath("//select[@name='manufacturer_id']")));
        manufacturer.selectByValue("1");

        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("duck");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("short description");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("description");
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("title");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("meta description");

        driver.findElement(By.xpath("//div[@class='tabs']//li//a[text()='Prices']")).click();
        Thread.sleep(30);

        var price = driver.findElement(By.xpath("//input[@name='purchase_price']"));
        price.clear();
        price.sendKeys("100");

        var currencyCode = new Select(driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']")));
        currencyCode.selectByValue("USD");

        var priceUSD = driver.findElement(By.xpath("//input[@name='prices[USD]']"));
        priceUSD.clear();
        priceUSD.sendKeys("100");

        var grossUSD = driver.findElement(By.xpath("//input[@name='gross_prices[USD]']"));
        grossUSD.clear();
        grossUSD.sendKeys("120");

        var priceEUR = driver.findElement(By.xpath("//input[@name='prices[EUR]']"));
        priceEUR.clear();
        priceEUR.sendKeys("80");

        var grossEUR = driver.findElement(By.xpath("//input[@name='gross_prices[EUR]']"));
        grossEUR.clear();
        grossEUR.sendKeys("90");

        driver.findElement(By.xpath("//button[@name='save']")).click();
        Thread.sleep(30);

        driver.findElement(By.xpath("//a[text()='"+productName+"']")).click();
        Thread.sleep(30);
        var productNameActual = driver.findElement(By.xpath("//input[@name='name[en]']")).getAttribute("value");

        Assert.assertEquals(productName, productNameActual);
    }

    @After
    public void stop() {
        driver.close();
    }
}
