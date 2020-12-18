package ru.stqa.example.tests;

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

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("123qwe");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void task9Part1() {

        var baseUrl = "http://localhost/litecart/admin/?app=countries&doc=countries";

        driver.navigate().to(baseUrl);

        var countryList = driver.findElements(By.xpath("//form[@name='countries_form']//*[@class='row']//td[5]"))
                .stream().map(element -> element.getText()).collect(Collectors.toList());

        Assert.assertTrue(Ordering.natural().isOrdered(countryList));

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

    @Test
    public void task9Part2() {
        var baseUrl = "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";

        driver.navigate().to(baseUrl);
        var zonesToCheck = driver.findElements(By.xpath("//form[@name='geo_zones_form']//tr[@class='row']//td[3]//a"))
                .stream().map(element -> element.getAttribute("href")).collect(Collectors.toList());

        for (var zone :zonesToCheck) {
            driver.navigate().to(zone);
            var zones = driver.findElements(By.xpath("//table[@id='table-zones']//tr//td[3]//option[@selected]"))
                    .stream().map(element -> element.getText()).collect(Collectors.toList());

            Assert.assertTrue(Ordering.natural().isOrdered(zones));

            driver.navigate().to(baseUrl);
        }
    }

    @After
    public void stop() {
        driver.close();
    }
}
