package base;


import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    private static final int TIME_OUT = 15;


    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--window-size=1250,900");
        chromeOptions.addArguments("--remote-allow-origins=*");
//        chromeOptions.addArguments("--headless");
        //chromeOptions.addArguments("--user-data-dir=C:\\Users\\Deni\\AppData\\Local\\Google\\Chrome\\User Data");
        //chromeOptions.addArguments("--profile-directory=Profile 11");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(TIME_OUT, TimeUnit.SECONDS);
    }


    public static boolean checkCookiesContains(String key, String value) {
        return driver.manage().getCookieNamed(key).getValue().contains(value);
    }

    public static boolean checkCookieEquals(String key, String value) {
        return driver.manage().getCookieNamed(key).getValue().equals(value);
    }


}


