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
        var rgbRegularPriceColorMain = regularPriceColorMain.replaceAll("[^\\d. ]","").split(" ");
        Assert.assertEquals(rgbRegularPriceColorMain[1], rgbRegularPriceColorMain[2]);

        var campaignPriceColorMain = campaignPriceElementMain.getCssValue("color");
        var rgbCampaignPriceColorMain = campaignPriceColorMain.replaceAll("[^\\d. ]","").split(" ");
        Assert.assertEquals(0, Integer.parseInt(rgbCampaignPriceColorMain[1]));
        Assert.assertEquals(0, Integer.parseInt(rgbCampaignPriceColorMain[2]));

        var textDecorationRegularPriceMain = regularPriceElementMain.getCssValue("text-decoration");
        Assert.assertTrue(textDecorationRegularPriceMain.contains("line-through"));

        var fontRegularPriceMain = Double.parseDouble(regularPriceElementMain.getCssValue("font-size").replaceAll("[^\\d.]",""));
        var fontCampaignPriceMain = Double.parseDouble(campaignPriceElementMain.getCssValue("font-size").replaceAll("[^\\d.]",""));
        Assert.assertTrue(fontCampaignPriceMain > fontRegularPriceMain);

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

        var campaignPriceColorProduct = campaignPriceElementProduct.getCssValue("color");
        var rgbCampaignPriceColorProduct = campaignPriceColorProduct.replaceAll("[^\\d. ]","").split(" ");
        Assert.assertEquals(0, Integer.parseInt(rgbCampaignPriceColorProduct[1]));
        Assert.assertEquals(0, Integer.parseInt(rgbCampaignPriceColorProduct[2]));


        var textDecorationRegularPriceProduct = regularPriceElementProduct.getCssValue("text-decoration");
        Assert.assertTrue(textDecorationRegularPriceProduct.contains("line-through"));

        var fontRegularPriceProduct = Double.parseDouble(regularPriceElementProduct.getCssValue("font-size").replaceAll("[^\\d.]",""));
        var fontCampaignPriceProduct = Double.parseDouble(campaignPriceElementProduct.getCssValue("font-size").replaceAll("[^\\d.]",""));
        Assert.assertTrue(fontCampaignPriceProduct > fontRegularPriceProduct);
    }

    @After
    public void stop() {
        driver.close();
    }

}
