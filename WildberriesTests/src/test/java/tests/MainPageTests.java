package tests;

import base.BaseTest;
import base.TestListener;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ExtendWith(TestListener.class)
public class MainPageTests extends BaseTest {

    private WebElement webElement;
    private String pageName;
    private static String URL="https://www.wildberries.ru/";

    private  static String productId = "111160257";

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
        pageName = driver.getCurrentUrl();
        Assertions.assertEquals(URL+"lk/basket", pageName);
        log.info("Проверка на url пройдена");

        //кликаем на баннер wildberries
        mainPage.clickMainButton();
        log.info("Нажимаем на кнопку 'Wildberries'");

        //проверяем url main страницы
        pageName = driver.getCurrentUrl();
        Assertions.assertEquals(URL, pageName);
        log.info("Assert проверка на url пройдена");

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
        log.info("Assert проверка на сравнение адреса пройдена");
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

        webElement = driver.findElement(By.xpath("//span[@class='simple-menu__currency'][contains(text(), 'AMD')]"));
        Assertions.assertEquals(webElement.getText(), "AMD");
        log.info("Assert проверка на AMD пройдена");

        mainPage.clickAtFirstProduct();
        log.info("Нажимаем на первый (любой) товар");

        Assertions.assertEquals(mainPage.takeCurrencyTextJS().substring(mainPage.takeCurrencyTextJS().length() - 4, mainPage.takeCurrencyTextJS().length()), "драм");
        log.info("Assert проверка 'драм'  пройдена");
    }

    @Owner("Denis Maltsev")
    @DisplayName("Поиск по артикулу")
    @Description("Проверка на поиск цифровому по артикулу")
    @Test
    public void findingProduct() {
        MainPage mainPage = new MainPage(driver);
        log.info("Запуск домашней страницы Wildberries");

        mainPage.openMainPage().clickToSearchField();
        log.info("Нажимаем на поле поиска");

        mainPage.sendArticulKeys(productId);
        log.info("Вводим номер артикула и нажимаем ENTER");

        webElement = driver.findElement(By.xpath("//div[@class='product-page__header']"));
        Assertions.assertEquals(webElement.getText(), "FRESHLAND Влажные детские салфетки ДПантенол Зайка 3х120 шт с клапаном");
        log.info("Assert проверка на название товара пройдена");

        pageName = driver.getCurrentUrl();
        String trueCurrentPage = URL + "catalog/"+ productId + "/detail.aspx?targetUrl=SP";
        Assertions.assertEquals(pageName,trueCurrentPage);
        log.info("Assert проверка на адрес страницы пройдена");




    }

    @Owner("Denis Maltsev")
    @DisplayName("Добавление в корзину")
    @Description("Добавление в корзину, проверяем все названия и факт добавления")
    @Test
    public void addToCurt() {
        MainPage mainPage = new MainPage(driver);
        log.info("Запуск домашней страницы Wildberries");

        mainPage.openMainPage().clickAtFirstProduct();
        log.info("Нажимаем на первый (любой) товар");

        mainPage.clickToAddInCurt();
        log.info("Нажимаем на кнопку 'Добавить в корзину'");

       mainPage.clickBucketButton();
       log.info("Нажимаем на кнопку 'Корзина'");

    }


}




