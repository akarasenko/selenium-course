package ru.stqa.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Task11Test {
    WebDriver driver;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities)driver).getCapabilities());
    }

    @Test
    public void task7Test() {

        var randomPart = Long.toString(Math.round(Math.random() * 100000));
        var firstName = "TestFirstName" + randomPart;
        var email = "test" + randomPart + "@test.com";

        driver.navigate().to("http://localhost/litecart/en/");
        driver.findElement(By.xpath("//form[@name='login_form']//a[text()='New customers click here']")).click();

        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='tax_id']")).sendKeys("TestTaxId");
        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='company']")).sendKeys("TestCompany");
        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='lastname']")).sendKeys("TestLastName");
        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='address1']")).sendKeys("TestAddress1");
        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='address2']")).sendKeys("TestAddress2");
        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='postcode']")).sendKeys("12345");
        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='city']")).sendKeys("TestCity");

        var countryCodeSelect = new Select(driver.findElement(By.xpath("//select[@name='country_code']")));
        countryCodeSelect.selectByVisibleText("United States");

        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='phone']")).sendKeys("+71112224455");

        selectCheckbox(driver,"//form[@name='customer_form']//input[@type='checkbox']");

        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='password']")).sendKeys("password");
        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='confirmed_password']")).sendKeys("password");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.xpath("//div[@id='box-account']//a[text()='Logout']")).click();

        driver.findElement(By.xpath("//div[@id='box-account-login']//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//div[@id='box-account-login']//input[@name='password']")).sendKeys("123qwe");
        driver.findElement(By.xpath("//div[@id='box-account-login']//button[@name='login']")).click();

        driver.findElement(By.xpath("//div[@id='box-account']//a[text()='Logout']")).click();
    }

    public void selectCheckbox(WebDriver driver, String checkBoxXpath) {
        var checkbox = driver.findElement(By.xpath(checkBoxXpath));
        var value = checkbox.getAttribute("checked");
        if (!value.equals("true")) {
            checkbox.click();
        }
    }

    @After
    public void stop() {
        driver.close();
    }
}
