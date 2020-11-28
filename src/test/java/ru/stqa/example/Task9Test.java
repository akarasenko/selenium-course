package ru.stqa.example;

import com.google.common.collect.Ordering;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Task9Test {
    private WebDriver driver;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities)driver).getCapabilities());
    }

    @Test
    public void task9Test() {

        var baseUrl = "http://localhost/litecart/admin/?app=countries&doc=countries";

        driver.navigate().to(baseUrl);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("123qwe");
        driver.findElement(By.name("login")).click();

        var contryList = driver.findElements(By.xpath("//form[@name='countries_form']//*[@class='row']//td[5]"))
                .stream().map(element -> element.getText()).collect(Collectors.toList());

        Assert.assertTrue(Ordering.natural().isOrdered(contryList));

        var zoneCountList = driver.findElements(By.xpath("//form[@name='countries_form']//*[@class='row']//td[6]"));
        var countryToCheckZones = new ArrayList<String>();

        for (var zoneCount : zoneCountList) {
            var count = Integer.parseInt(zoneCount.getText());

            if (count > 0) {
                countryToCheckZones.add( zoneCount.findElement(By.xpath("..//td[5]//a")).getAttribute("href") );
            }
        }

        for (var country : countryToCheckZones) {
            driver.navigate().to(country);

            var zoneList = driver.findElements(By.xpath("//table[@id='table-zones']//td[3]//input"))
                    .stream().map(element -> element.getAttribute("value")).filter(text -> text != "").collect(Collectors.toList());
            Assert.assertTrue(Ordering.natural().isOrdered(zoneList));

            driver.navigate().to(baseUrl);
        }
    }

    @After
    public void stop() {
        driver.close();
    }
}
