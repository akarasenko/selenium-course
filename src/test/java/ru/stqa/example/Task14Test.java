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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Set;

public class Task14Test {
    WebDriver driver;
    Wait wait;
    String initWindow ="";

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities) driver).getCapabilities());

        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void task14Test() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//table[@class='dataTable']//a[@title='Edit']")).click();

        initWindow = driver.getWindowHandle();
        clickToOpenLinkAndCheckUrl("//input[@name='iso_code_2']/..//a[@target='_blank']", "https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2");
        clickToOpenLinkAndCheckUrl("//input[@name='iso_code_3']/..//a[@target='_blank']", "https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3");
        clickToOpenLinkAndCheckUrl("//input[@name='tax_id_format']/..//a[@target='_blank']", "https://en.wikipedia.org/wiki/Regular_expression");
        clickToOpenLinkAndCheckUrl("//textarea[@name='address_format']/..//a[@target='_blank']", "https://www.informatica.com/products/data-quality/data-as-a-service/address-verification/address-formats.html");
        clickToOpenLinkAndCheckUrl("//input[@name='postcode_format']/..//a[@target='_blank']", "https://en.wikipedia.org/wiki/Regular_expression");
        clickToOpenLinkAndCheckUrl("//input[@name='currency_code']/..//a[@target='_blank']", "https://en.wikipedia.org/wiki/List_of_countries_and_capitals_with_currency_and_language");
        clickToOpenLinkAndCheckUrl("//input[@name='phone_code']/..//a[@target='_blank']", "https://en.wikipedia.org/wiki/List_of_country_calling_codes");
    }

    public void clickToOpenLinkAndCheckUrl(String xPathLink, String linkUrl) {
        var oldWindows = driver.getWindowHandles();
        driver.findElement(By.xpath(xPathLink)).click();
        String newWindow = (String) wait.until(thereIsWindowOtherThan(oldWindows));
        driver.switchTo().window(newWindow);
        Assert.assertEquals( driver.getCurrentUrl(), linkUrl);
        driver.close();
        driver.switchTo().window(initWindow);
    }

    public ExpectedCondition<String> thereIsWindowOtherThan (Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver input) {
                var currentWindows = driver.getWindowHandles();
                currentWindows.removeAll(oldWindows);
                return currentWindows.size() > 0 ? currentWindows.iterator().next() : null;
            }
        };
    }
    @After
    public void stop() {
        driver.quit();
    }
}
