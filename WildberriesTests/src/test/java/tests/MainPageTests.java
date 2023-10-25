package tests;

import base.BaseTest;
import base.TestListener;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.LogManager;

@ExtendWith(TestListener.class)
public class MainPageTests extends BaseTest {
    //        private  static Logger log = LoggerFactory.getLogger(MainPageTests.class);
    private static Logger log = LoggerFactory.getLogger(MainPageTests.class);


    @Test
    public void clickMainPage() {
        MainPage mainPage = new MainPage(driver);
        log.info("Запуск домашней страницы wildberries");
        mainPage.openMainPage().clickMainButton();
        log.info("Нажимаем на кнопку wildberries");
        String pageName = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.wildberries.ru/", pageName);
        log.info("Проверка на url сайта");


    }

}
