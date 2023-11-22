package base;

import com.codeborne.selenide.webdriver.DriverFactory;
import io.qameta.allure.Step;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    private static final int TIME_OUT = 15;



    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "\\Users\\Deni\\IdeaProjects\\WildberriesTests\\src\\main\\resources\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--window-size=1250,900");
//        chromeOptions.addArguments("--user-data-dir=C:\\Users\\Deni\\AppData\\Local\\Google\\Chrome\\User Data");
//        chromeOptions.addArguments("--profile-directory=Profile 11");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--headless");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(TIME_OUT, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }


    public static boolean checkCookiesContains(String key, String value) {
        return driver.manage().getCookieNamed(key).getValue().contains(value);
    }

    public static boolean checkCookieEquals(String key, String value) {
        return driver.manage().getCookieNamed(key).getValue().equals(value);
    }


}


