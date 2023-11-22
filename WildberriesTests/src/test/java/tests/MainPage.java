package tests;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
;import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MainPage extends BasePage {

    public WebDriverWait wait;

    @FindBy(xpath = "//a[@data-wba-header-name='Main']")
    private WebElement mainButton;

    @FindBy(xpath = "//a[@data-wba-header-name='Cart']")
    private WebElement bucketButton;

    @FindBy(xpath = "//span[@data-wba-header-name='DLV_Adress']")
    private WebElement addressButton;

    @FindBy(xpath = "//input[@placeholder='Введите адрес']")
    private WebElement addressInput;

    @FindBy(xpath = "//span[@class='address-item__name-text']//span[contains(text(),'г. Ногинск (Ногинский р-н.), ул. Советская, 43')]")
    private WebElement addressChoosing;
    @FindBy(xpath = "//ymaps[text()='Найти']")
    private WebElement clickToFind;

    @FindBy(xpath = "//*[text()='Выбрать']")
    private WebElement confirmClick;

    @FindBy(xpath = "//span[@class='simple-menu__currency']")
    private WebElement currencyButton;

    @FindBy(xpath = "//*[text()='Армянский драм']")
    private WebElement currencyChoosing;

    @FindBy(xpath = "//a[@class='product-card__link j-card-link j-open-full-product-card'][1]")
    private WebElement chooseFirstProduct;

    @FindBy(xpath = "//ins[@class='price-block__final-price']")
    private WebElement nameOfCurrency;

    @FindBy(id = "searchInput")
    private WebElement searchField;

    @FindBy(xpath = "//div[@class='product-page__header']")
    private WebElement productName;

    @FindBy(xpath = "//button[@class='btn-main']")
    private WebElement addToCart;







    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Открываем главную страницу Wildberries")
    public MainPage openMainPage() {
        driver.get("https://www.wildberries.ru/");
        return this;
    }

    @Step("Нажимаем на кнопку 'Корзина'")
    public void clickBucketButton() {
        bucketButton.click();
    }

    @Step("Нажимаем на кнопку Wildberries")
    public void clickMainButton() {
        mainButton.click();
    }

    @Step("Нажимаем на кнопку 'Адрес'")
    public void clickAddressButton() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addressButton.click();
    }


    @Step("Вводим в поле ввода адреса существующий адрес: {0}")
    public void setAddressInput(String str) {
        addressInput.sendKeys(str);
    }

    @Step("Нажимаем на кнопку 'Поиск'")
    public void clickToFindAddress() {
        clickToFind.click();
    }

    @Step("Выбраем нужный адрес")
    public void clickToChooseAddress() {
        addressChoosing.click();

    }

    @Step("Нажимаем на кнопку 'Подтвердить'")
    public void clickToConfirmAddress() {
        confirmClick.click();
    }

    @Step("Нажимаем на кнопку валюты")
    public void clickCurrencyButton() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        currencyButton.click();
    }

    @Step("Выбираем валюту")
    public void clickToChooseCurrency() {
        currencyChoosing.click();
    }

    @Step("Выбираем любой товар")
    public void clickAtFirstProduct() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chooseFirstProduct.click();
    }


    @Step("Используем скрипт для проверки изменения валюты")
    public String takeCurrencyTextJS() {
        String text = ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;", nameOfCurrency).toString().trim();
        return text;
    }

    @Step("Нажимаем на поле поиска")
    public void clickToSearchField () {
        searchField.click();
    }

    @Step("Вводим номер артикула: {0}")
    public void sendArticulKeys (String str) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchField.sendKeys(str);
        searchField.sendKeys(Keys.ENTER);
    }

    @Step("Добавляем в корзину")
    public void clickToAddInCurt() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addToCart.click();
    }

    @Step("Используем скрипт для проверки изменения валюты")
    public void clickToCurtJS() {
       ((JavascriptExecutor) driver).executeScript("arguments[0].click();",addToCart);
    }





}
