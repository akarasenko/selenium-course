package ru.stqa.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Task10Test {
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

        var product = driver.findElement(By.xpath("//div[@id='box-campaigns']//li"));
        var nameMain = product.findElement(By.className("name")).getText();

        var regularPriceElementMain = product.findElement(By.className("regular-price"));
        var regularPriceMain = regularPriceElementMain.getText();

        var campaignPriceElementMain = product.findElement(By.className("campaign-price"));
        var campaignPriceMain = campaignPriceElementMain.getText();

        var regularPriceColorMain = regularPriceElementMain.getCssValue("color");
        var gColorRegularPriceMain = regularPriceColorMain.substring(10, 13);
        var bColorRegularPriceMain = regularPriceColorMain.substring(15, 18);
        Assert.assertEquals(gColorRegularPriceMain, bColorRegularPriceMain);

        var textDecorationRegularPriceMain = regularPriceElementMain.getCssValue("text-decoration");
        Assert.assertTrue(textDecorationRegularPriceMain.contains("line-through"));

        var sizeRegularPriceMain = regularPriceElementMain.getSize();
        var sizeCampaignPriceMain = campaignPriceElementMain.getSize();
        Assert.assertTrue(sizeCampaignPriceMain.getHeight() > sizeRegularPriceMain.getHeight());

        product.click();

        var productBox = driver.findElement(By.id("box-product"));

        var nameProduct = productBox.findElement(By.className("title")).getText();
        Assert.assertEquals(nameMain, nameProduct);

        var regularPriceElementProduct = productBox.findElement(By.className("regular-price"));
        Assert.assertEquals(regularPriceMain, regularPriceElementProduct.getText());

        var campaignPriceElementProduct = productBox.findElement(By.className("campaign-price"));
        Assert.assertEquals(campaignPriceMain, campaignPriceElementProduct.getText());

        var regularPriceColorProduct = regularPriceElementProduct.getCssValue("color");
        var gColorRegularPriceProduct = regularPriceColorProduct.substring(10, 13);
        var bColorRegularPriceProduct = regularPriceColorProduct.substring(15, 18);
        Assert.assertEquals(gColorRegularPriceProduct, bColorRegularPriceProduct);

        var textDecorationRegularPriceProduct = regularPriceElementProduct.getCssValue("text-decoration");
        Assert.assertTrue(textDecorationRegularPriceProduct.contains("line-through"));

        var sizeRegularPriceProduct = regularPriceElementProduct.getSize();
        var sizeCampaignPriceProduct = campaignPriceElementProduct.getSize();
        Assert.assertTrue(sizeCampaignPriceProduct.getHeight() > sizeRegularPriceProduct.getHeight());
    }

    @After
    public void stop() {
        driver.close();
    }

}
