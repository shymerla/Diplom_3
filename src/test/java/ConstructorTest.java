import POM.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ConstructorTest extends Setup{

    @Before
    public void setUp() {
        Configuration.startMaximized = true;
    }

    @Test
    @DisplayName("Переход к блоку начинки")
    public void checkSwitchToToppingsSectionTest() {
        MainPage mainPage = Selenide.open(Base.mainPageURL, MainPage.class);
        mainPage.clickToppingsTab();
        boolean isFillingsTabActive = mainPage.isFillingsTabActive();
        assertTrue("Нет перехода на раздел Начинки", isFillingsTabActive);
    }

    @Test
    @DisplayName("Переход к блоку начинки")
    public void checkSwitchToBunsSectionTest() {
        MainPage mainPage = Selenide.open(Base.mainPageURL, MainPage.class);
        mainPage.clickBunsTab();
        boolean isBunsTabActive = mainPage.isBunsTabActive();
        assertTrue("Нет перехода на раздел Булки", isBunsTabActive);
    }

    @Test
    @DisplayName("Переход к блоку соусы")
    public void checkSwitchToSaucesSectionTest() {
        MainPage mainPage = Selenide.open(Base.mainPageURL, MainPage.class);
        mainPage.clickSauceTab();
        boolean isSaucesTabActive = mainPage.isSaucesTabActive();
        assertTrue("Нет перехода на раздел Соусы", isSaucesTabActive);
    }
}