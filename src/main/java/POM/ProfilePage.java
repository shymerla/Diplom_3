package POM;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {

    // кнопка Конструктор
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    // Лого Stellar burger header
    @FindBy(how = How.CSS, using = ".AppHeader_header__logo__2D0X2")
    private SelenideElement headerLogo;

    // кнопка Выход
    @FindBy(how = How.CSS, using = ".Account_button__14Yp3")
    private SelenideElement exitButton;

    // кнопка Профиль
    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    private SelenideElement profileButton;

    @Step("Нажатие кноки Конструктор")
    public void clickConstructorButton() {
        constructorButton.click();
    }

    @Step("Нажатие лого Stellar Burger")
    public void clickStellarBurgerHeaderLogo() {
        headerLogo.click();
    }

    @Step("Видимость кнопки Профиль")
    public boolean isProfileButtonDisplayed() {
        return profileButton.shouldBe(Condition.exist).isDisplayed();
    }

    @Step("Видимость кнопки Выход")
    public boolean isExitButtonDisplayed() {
        return exitButton.shouldBe(Condition.exist).isDisplayed();
    }

    @Step("Нажатие кнопки Выход")
    public void clickExitButton() {
        exitButton.click();
    }
}