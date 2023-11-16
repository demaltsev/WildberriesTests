package tests;

import base.BaseTest;
import base.TestListener;
import groovy.util.logging.Slf4j;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.logging.LogManager;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith(TestListener.class)
public class MainPageTests extends BaseTest {

    private WebElement webElement;

    private static Logger log = LoggerFactory.getLogger(MainPageTests.class);

    @Owner("Denis Maltsev")
    @DisplayName("Переход на домашнюю страницу")
    @Description("Проверка на работу кнопки Wildberries")
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

    @Owner("Denis Maltsev")
    @DisplayName("Добавление адреса")
    @Description("Проверка на добавление существующего адреса доставки")
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
        webElement = driver.findElement(By.xpath("//span[@data-wba-header-name='DLV_Adress']"));
        Assertions.assertEquals(webElement.getText(), "г. Ногинск (Ногинский р-н.), ул. Советская, 43");
        log.info("Assert проверка выполнена");
    }

    @Owner("Denis Maltsev")
    @DisplayName("Изменение валюты")
    @Description("Проверка на изменение валюты")
    @Test
    public void setAnotherCurrency() {

        MainPage mainPage = new MainPage(driver);
        log.info("Запуск домашней страницы Wildberries");

        //нажимаем на поле поиска и вводим адрес
        mainPage.openMainPage().clickCurrencyButton();
        log.info("Нажимаем на кнопку валюты");

        mainPage.clickToChooseCurrency();
        log.info("Выбираем валюту");


        webElement =driver.findElement(By.xpath("//span[@class='simple-menu__currency'][contains(text(), 'AMD')]"));
        System.out.println(webElement.getText());
        Assertions.assertEquals(webElement.getText(), "AMD");
        log.info("Assert проверка на AMD выполнена");

        mainPage.clickAtFirstProduct();
        log.info("Кликаем на первый товар");

        WebElement element = driver.findElement(By.xpath("//ins[@class='price-block__final-price']"));
        String text = ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;", element).toString().trim();
        Assertions.assertEquals(text.substring(text.length()-4,text.length()), "драм");
        log.info("Assert проверка выполнена");
    }




}
