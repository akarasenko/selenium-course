import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class Task13Test {
    WebDriver driver;
    Wait wait;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities) driver).getCapabilities());

        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void task13Test() {
        driver.navigate().to("http://localhost/litecart/en/");

        var productName = "";
        var productInTableXpath = "";
        var productCount = 3; // how many product you want to add to cart

        for (int i = 0; i<productCount; i++) {
            driver.findElement(By.xpath("//div[@id='box-most-popular']//li[@class='product column shadow hover-light'][1]")).click();
            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
            wait.until(textToBe(By.xpath("//div[@id='cart']//a[@class='content']//span[@class='quantity']"), "1"));
            driver.findElement(By.xpath("//div[@id='logotype-wrapper']")).click();
        }
        
        driver.findElement(By.xpath("//div[@id='cart']//a[@class='link']")).click();

        var productAmount = driver.findElements(By.xpath("//div[@id='box-checkout-cart']//ul[@class='shortcuts']//li")).size();

        for (int i =0; i < productAmount; i++) {

            productName = driver.findElement(By.xpath("//form[@name='cart_form']//div//p//a//strong")).getText();
            productInTableXpath = "//div[@id='order_confirmation-wrapper']//tbody//tr//td[@class='item'][text()='" + productName + "']";
            var tableProduct = driver.findElement(By.xpath(productInTableXpath));
            driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            wait.until(stalenessOf(tableProduct));
        }
    }

    @After
    public void stop() {
        driver.close();
    }
}
