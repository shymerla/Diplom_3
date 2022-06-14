import POM.LoginPage;
import POM.RegistrationPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static org.junit.Assert.assertEquals;

public class RegistrationTest extends Setup{

    UserClient userClient;
    User user;
    UserCredentials userCredentials;
    String expectedInvalidPassErrorMessage = "Некорректный пароль";
    String expectedEnterTitle = "Вход";

    @Before
    public void setUp() {
        userClient = new UserClient();
        userCredentials = new UserCredentials();
        Configuration.startMaximized = true;
    }

    @After
    public void tearDown() {
        webdriver().driver().close();
    }

    @DisplayName("Проверяем, что можно зарегистрироваться")
    @Test
    public void signUpPositiveTest() {
        user = User.getRandomCorrectUser();
        RegistrationPage registerPage = Selenide.open(Base.registerPageURL, RegistrationPage.class);
        registerPage.signUp(user.getName(), user.getEmail(), user.getPassword());

        LoginPage loginPage = Selenide.page(LoginPage.class);
        String actualEnterTitle = loginPage.EnterTitle();

        String accessToken = userCredentials.getUserAccessToken(user);
        userClient.delete(accessToken);

        assertEquals("Expected title is " + expectedEnterTitle + ". But actual is " + actualEnterTitle,
                expectedEnterTitle, actualEnterTitle);
    }

    @DisplayName("Проверяет, что нельзя зарегистрироваться с паролем из 5 символов")
    @Test
    public void signUpWithFiveCharacterPassNegativeTest() {
        user = User.getUserWith5lettersPassword();
        RegistrationPage registerPage = Selenide.open(Base.registerPageURL, RegistrationPage.class);
        registerPage.signUp(user.getName(), user.getEmail(), user.getPassword());

        String actualErrorMessage = registerPage.getTooShortPasswordErrorMessage();

        assertEquals("Expected error message is " + expectedInvalidPassErrorMessage + ". But actual is " + actualErrorMessage,
                expectedInvalidPassErrorMessage, actualErrorMessage);
    }

    @DisplayName("Проверяем, что можно зарегистрироваться с паролем из 9 символов")
    @Test
    public void signUpWithNineSymbolsPassPositiveTest() {
        user = User.getUserWith9lettersPassword();
        RegistrationPage registerPage = Selenide.open(Base.registerPageURL, RegistrationPage.class);
        registerPage.signUp(user.getName(), user.getEmail(), user.getPassword());

        LoginPage loginPage = Selenide.page(LoginPage.class);
        String actualEnterTitle = loginPage.EnterTitle();

        String accessToken = userCredentials.getUserAccessToken(user);
        userClient.delete(accessToken);

        assertEquals("Expected title is " + expectedEnterTitle + ". But actual is " + actualEnterTitle,
                expectedEnterTitle, actualEnterTitle);
    }
}