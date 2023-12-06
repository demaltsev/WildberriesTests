package tests;

import base.BaseSelenideTest;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class WildberriesSelenideTests extends BaseSelenideTest {
    private final static String URL = "https://www.wildberries.ru/";
    @Test
    public void clickMainPage() {
       open(URL);
    }
}
