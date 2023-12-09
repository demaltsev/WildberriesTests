package tests;

import base.BaseTest;
import base.TestListener;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
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
    private String PAGE_NAME;
    private String TEXT;
    private static String URL = "https://www.wildberries.ru/";

    private static String productId = "111160257";

    private static Logger log = LoggerFactory.getLogger(MainPageTests.class);


    @Owner("Denis Maltsev")
    @DisplayName("Переход на домашнюю страницу")
    @Description("Проверка на работу кнопки Wildberries")
    @Test
    public void clickMainPage() {

        //открыть домашнюю страницу wildberries
        MainPage mainPage = new MainPage(driver);
        log.info("Запуск домашней страницы Wildberries");

        //нажать  на корзину
        mainPage.openMainPage().clickBucketButton();
        log.info("нажать на кнопку 'Корзина'");

        //проверить url bucket страницы
        PAGE_NAME = driver.getCurrentUrl();
        Assertions.assertEquals(URL + "lk/basket", PAGE_NAME);
        log.info("Проверка на url пройдена");

        //нажать  на баннер wildberries
        mainPage.clickMainButton();
        log.info("нажать на кнопку 'Wildberries'");

        //проверить url main страницы
        PAGE_NAME = driver.getCurrentUrl();
        Assertions.assertEquals(URL, PAGE_NAME);
        log.info("Assert проверка на url пройдена");

    }

    @Owner("Denis Maltsev")
    @DisplayName("Добавление адреса")
    @Description("Проверка на добавление существующего адреса доставки")
    @Test
    public void addAddressPage() {
        //открыть домашнюю страницу wildberries
        MainPage mainPage = new MainPage(driver);
        log.info("Запуск домашней страницы Wildberries");

        //нажать на поле поиска и ввести адрес
        mainPage.openMainPage().clickAddressButton();
        mainPage.setAddressInput("г. Ногинск (Ногинский р-н.), ул. Советская, 43");
        log.info("нажать на поле поиска и ввести адрес");

        //нажать найти
        mainPage.clickToFindAddress();
        log.info("нажать на кнопку найти");

        //выбрать наш адрес из списка
        mainPage.clickToChooseAddress();
        log.info("выбрать адрес из списка");

        //подтвердить выбор
        mainPage.clickToConfirmAddress();
        log.info("подтвердить выбор адреса");

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

        mainPage.openMainPage().clickAtFirstProduct();
        log.info("нажать на первый (любой) товар");

        Assertions.assertEquals(mainPage.takeCurrencyTextJS().substring(mainPage.takeCurrencyTextJS().length() - 1, mainPage.takeCurrencyTextJS().length()), "₽");
        log.info("Assert проверка '₽'  пройдена");
    }

    @Owner("Denis Maltsev")
    @DisplayName("Поиск по артикулу")
    @Description("Проверка на поиск цифровому по артикулу")
    @Test
    public void findingProduct() {
        MainPage mainPage = new MainPage(driver);
        log.info("Запуск домашней страницы Wildberries");

        mainPage.openMainPage().clickToSearchField();
        log.info("нажать на поле поиска");

        mainPage.sendArticulKeys(productId);
        log.info("ввести номер артикула и нажать ENTER");

        webElement = driver.findElement(By.xpath("//div[@class='product-page__header']"));
        Assertions.assertEquals(webElement.getText(), "FRESHLAND Влажные детские салфетки ДПантенол Зайка 3х120 шт с клапаном");
        log.info("Assert проверка на название товара пройдена");

        PAGE_NAME = driver.getCurrentUrl();
        String trueCurrentPage = URL + "catalog/" + productId + "/detail.aspx?targetUrl=SP";
        Assertions.assertEquals(PAGE_NAME, trueCurrentPage);
        log.info("Assert проверка на адрес страницы пройдена");


    }


    @Owner("Denis Maltsev")
    @DisplayName("Добавление в корзину, удаление из корзины")
    @Description("Добавление в корзину, проверить все названия и факт добавления, удаляем из корзины")
    @Test
    public void addToCurt() {
        MainPage mainPage = new MainPage(driver);
        log.info("Запуск домашней страницы Wildberries");

        mainPage.openMainPage().clickToSearchField();
        log.info("нажать на поле поиска");

        mainPage.sendArticulKeys(productId);
        log.info("ввести номер артикула и нажать ENTER");

        mainPage.clickToAddInCurt();
        log.info("нажать на кнопку 'Добавить в корзину'");

        mainPage.clickBucketButton();
        log.info("нажать на кнопку 'Корзина'");

        Assertions.assertEquals("1", mainPage.takingNumberInCurt());
        log.info("проверить кол-во товаров в корзине");

        mainPage.clickToDeleteCurt();
        log.info("Удаляем из корзины");

        TEXT = driver.findElement(By.xpath("//div[@class='basket-empty__wrap']//h1[@class='section-header basket-empty__title']")).getText();
        Assertions.assertEquals(TEXT, "В корзине пока пусто");
        log.info("проверить кол-во товаров в корзине");
    }


}




