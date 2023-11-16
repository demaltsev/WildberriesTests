package tests;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


    @Step("Вводим в поле ввода адреса существующий адрес")
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


}
