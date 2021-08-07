package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.util.List;

public class Webdriverlaunch {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void setupTest() throws InterruptedException {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--headless");
        driver = new ChromeDriver(co);
        driver.get("http://emicalculator.net/");
        Thread.sleep(5000);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() throws InterruptedException {
       String verticalxpath= "//*[local-name()='svg']//*[name()='g' and @class='highcharts-series-group']//*[name()='rect']";
       String textxpath="//*[local-name()='svg']//*[name()='g' and @class='highcharts-label highcharts-tooltip highcharts-color-undefined']//*[name()='text']";
       List<WebElement> barslist=driver.findElements(By.xpath(verticalxpath));
        System.out.println(barslist.size());
        Actions act=new Actions(driver);
        for(WebElement e: barslist){
            act.moveToElement(e).perform();
           // Thread.sleep(500);
            String text=driver.findElement(By.xpath(textxpath)).getText();
            System.out.println(text);


        }
    }

}


