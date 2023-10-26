package tests;

import base.BaseTest;
import base.TestListener;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.logging.LogManager;

@ExtendWith(TestListener.class)
public class MainPageTests extends BaseTest {
    //        private  static Logger log = LoggerFactory.getLogger(MainPageTests.class);
    private static Logger log = LoggerFactory.getLogger(MainPageTests.class);


    @Test
    public void clickMainPage() {
        //открываем домашнюю страницу wildberries
        MainPage mainPage = new MainPage(driver);
        log.info("Запуск домашней страницы Wildberries");

        //кликаем на корзину
        mainPage.openMainPage().clickBucketButton();
        log.info("Нажимаем на кнопку 'Корзина'");

        //проверяем url bucket страницы
        String pageName = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.wildberries.ru/lk/basket", pageName);
        log.info("Проверка на url пройдена");

        //кликаем на баннер wildberries
        mainPage.clickMainButton();
        log.info("Нажимаем на кнопку 'Wildberries'");

        //проверяем url main страницы
        pageName = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.wildberries.ru/", pageName);
        log.info("Проверка на url пройдена");


//        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.id("")));
//        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='app-store']")));
    }

    @Test
    public void addAddressPage() {
        //открываем домашнюю страницу wildberries
        MainPage mainPage = new MainPage(driver);
        log.info("Запуск домашней страницы Wildberries");

        //нажимаем на поле поиска и вводим адрес
        mainPage.openMainPage().clickAddressButton();
        mainPage.setAddressInput("г. Ногинск (Ногинский р-н.), ул. Советская, 43");
        log.info("Нажимаем на поле поиска и вводим адрес");

        //нажимаем найти
        mainPage.clickToFindAddress();
        log.info("Нажимаем на кнопку найти");

        //выбираем наш адрес из списка
        mainPage.clickToChooseAddress();
        log.info("Выбираем адрес из списка");

        //подтверждаем выбор
        mainPage.clickToConfirmAddress();
        log.info("Подтверждаем выбор адреса");

        //проверка отображения адреса в верхней части сайта
        WebElement webElement = driver.findElement(By.xpath("//span[@data-wba-header-name='DLV_Adress']"));
        Assertions.assertEquals(webElement.getText(), "г. Ногинск (Ногинский р-н.), ул. Советская, 43");
        log.info("Assert проверка выполнена");

    }


}
