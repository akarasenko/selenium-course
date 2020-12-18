package ru.stqa.example.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.stream.Collectors;

public class Task17Test {
    WebDriver driver;
    String initPage = "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";
    String productXpath = "//td[@id='content']//table[@class='dataTable']//img/..//a";

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities) driver).getCapabilities());
    }

    @Test
    public void task14Test() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("login")).click();

        driver.navigate().to(initPage);

        var productNameList = driver.findElements(By.xpath("//td[@id='content']//table[@class='dataTable']//img/..//a"))
                .stream().map(i -> i.getText())
                .collect(Collectors.toList());


        for (var productName : productNameList) {
            var xpath = productXpath + "[text()='" + productName + "']";
            driver.findElement(By.xpath(xpath)).click();

            var consoleErrors = driver.manage().logs().get("browser").getAll();
            Assert.assertEquals(consoleErrors.size(), 0);

            driver.navigate().to(initPage);
        }
    }

    @After
    public void stop() {
        driver.quit();
    }
}
