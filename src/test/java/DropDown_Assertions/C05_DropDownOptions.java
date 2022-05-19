package day07_assertions_DropDown;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class C05_DropDownOptions {
    /*
    1. amazon anasayfaya gidip,
    dropdown menuden books'u seçelim
    seçtiğimiz option'ı yazdıralım

    2.dropdown'daki option'ların toplam sayısının
    28 olduğunu test edin.
     */

    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test03() throws InterruptedException {

        driver.get("https://www.amazon.com");
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText("Books");

        /*bir dropdown ile çalışırken,
        select.getFirstSelectedOption().getText()
        web element üzerindeki yazıyı döndürür.
         */

        System.out.println(select.getFirstSelectedOption().getText());

        /*
        dropdown'daki option sayısının 28 olduğunu göster
         */

        List<WebElement> optionList = select.getOptions();
        int optionSayisi = optionList.size();
        int expectedOptionSayisi = 28;

        Assert.assertEquals(expectedOptionSayisi, optionSayisi);
    }
}
