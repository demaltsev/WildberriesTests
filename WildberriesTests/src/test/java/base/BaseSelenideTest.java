package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;

public class BaseSelenideTest {


    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 40;
        Configuration.headless = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Before
    public void init(){
        setUp();
    }

    @After
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
