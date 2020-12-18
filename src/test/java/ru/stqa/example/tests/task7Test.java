package ru.stqa.example.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class task7Test {
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
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("123qwe");
        driver.findElement(By.name("login")).click();

        List<WebElement> menu = driver.findElements(By.xpath("//li[@id='app-']"));
        int length = menu.size();

        for (int i=1; i<length+1; i++)
        {
            String xpath = "//li[@id='app-']" + "[" + Integer.toString(i) + "]";
            driver.findElement(By.xpath(xpath)).click();
            driver.findElement(By.xpath(xpath + "/a")).click();

            List<WebElement> submenu = driver.findElements(By.xpath(xpath + "//ul/li/a"));
            int subLength = submenu.size();

            for (int j=1; j<subLength+1; j++)
            {
                String itemXpath = xpath + "//ul/li" + "[" + Integer.toString(j) + "]";
                driver.findElement(By.xpath(itemXpath)).click();
                driver.findElement(By.cssSelector("#content>h1"));
            }
        }
    }

    @After
    public void stop() {
        driver.close();
    }
}
