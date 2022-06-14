import POM.LoginPage;
import POM.MainPage;
import POM.ProfilePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class LoginOutTest extends Setup{

    String expectedEnterTitle = "Вход";
    UserClient userClient;
    User user;
    UserCredentials userCredentials;

    @Before
    public void setUp() {
        userClient = new UserClient();
        userCredentials = new UserCredentials();
        user = User.getRandomCorrectUser();
        Response response = userClient.userRegistration(user);
        response.then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
        Configuration.startMaximized = true;
    }

    @After
    public void tearDown() {
        webdriver().driver().close();

        String accessToken = userCredentials.getUserAccessToken(user);
        userClient.delete(accessToken);
    }

    @DisplayName("Проверяет, что пользователь может разлогиниться")
    @Test
    public void logOutPositiveTest() {

        LoginPage loginPage = Selenide.open(Base.loginURL, LoginPage.class);
        loginPage.logIn(user.getEmail(), user.getPassword());
        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.clickPersonalAccountButton();
        ProfilePage personalAccountPage = Selenide.page(ProfilePage.class);
        personalAccountPage.clickExitButton();
        String actualEnterTitle = loginPage.EnterTitle();

        assertEquals("Пользователя не перешел на страницу авторизации", expectedEnterTitle, actualEnterTitle);
    }
}