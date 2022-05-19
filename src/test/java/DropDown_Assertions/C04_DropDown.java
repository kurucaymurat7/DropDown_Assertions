package day07_assertions_DropDown;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class C04_DropDown {
    /*amazona git, dropdown menuden
    book seçeneğini seçip Java aratın.
    Arama sonuçlarının Java içerdiğini test edin.
     */

    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void test02() throws InterruptedException {
        driver.get("https://www.amazon.com");

        //1. dropdown seçmek için 3 adım vardır.

        //2. dropdown locate et
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));

        //bir select objesi oluşturup parametre olarak bir önceki adımda locate ettiğimiz
        //dropdown menuyu girelim.
        Select select = new Select(dropdownElement);

        //3. dropdown'da var olan option'lardan istediğimiz birini seçelim.
        select.selectByVisibleText("Books");
        //select.selectByIndex(5);
        //select.selectByValue("search-alias=stripbooks-intl-ship");

        //arama çubuğuna Java yazdır.
        WebElement aramaKutusu = driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("Java" + Keys.ENTER);

        String sonucYazisi = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]")).getText();
        String arananKelime = "Java";

        Assert.assertTrue(sonucYazisi.contains(arananKelime));

        Thread.sleep(3000);
    }
}
