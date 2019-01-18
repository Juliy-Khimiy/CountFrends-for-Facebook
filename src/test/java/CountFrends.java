import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.fail;

/**
 * Created by Admin on 16.01.2019.
 */
public class CountFrends {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass()
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "c:\\Program Files (x86)\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void testCountFrends() throws Exception {
         String login;
         String pass;
        JavascriptExecutor je = (JavascriptExecutor) driver;
        pass = " ";
        login=" ";
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(pass);
        driver.findElement(By.xpath("//label[@id='loginbutton']")).click();
        driver.navigate().to("https://www.facebook.com/profile.php?id=100028733544093");
        driver.findElement(By.xpath("//li[3]/a[contains(@class, '_6-6')]")).click();
        int i=0,n=0;
        WebDriverWait waitForOne = new WebDriverWait(driver, 30);
        List <WebElement> k;
        while (true)
        {
            try {
                k = waitForOne.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"pagelet_timeline_app_collection_100028733544093:2356318349:2\"]/ul//li//div[@class='fsl fwb fcb']")));
                n = k.size();
                if (n==i) break;
                i=n;
                je.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(500);
            }catch (Error e) {
                    verificationErrors.append(e.toString());
            }
        }
        System.out.println(n+" - Count frends");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}

