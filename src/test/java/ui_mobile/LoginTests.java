package ui_mobile;

import config.AppiumConfig;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactsScreen;
import screens.ErrorScreen;
import screens.SplashScreen;

import static utils.UserFabric.createUser;

public class LoginTests extends AppiumConfig {

    AuthenticationScreen authenticationScreen;

    @BeforeMethod
    public void goToAuthScreen() {
        new SplashScreen(driver);
        authenticationScreen = new AuthenticationScreen(driver);
    }

    @Test
    public void loginPositiveTest() {
        authenticationScreen.typeLoginForm(User.builder()
                        .username("sotiga2015@gmail.com")
                .password("Sh12345!@")
                .build());
        Assert.assertTrue(new ContactsScreen(driver)
                .validateContactsScreenOpen("Contact list"));
    }

    @Test
    public void loginNegative_WrongEmailTest() {
        authenticationScreen.typeLoginForm(User.builder()
                .username("sotiga2015gmail.com")
                .password("Sh12345!@")
                .build());
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Login or Password incorrect"));;
    }

    @Test
    public void loginNegative_WrongPasswordTest() {
        authenticationScreen.typeLoginForm(User.builder()
                .username("sotiga2015@gmail.com")
                .password("Sh12345!")
                .build());
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Login or Password incorrect"));;
    }

    @Test
    public void loginNegative_UnregisteredTest() {
        authenticationScreen.typeLoginForm(User.builder()
                .username("sotiga2000@gmail.com")
                .password("Sh12345!@")
                .build());
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Login or Password incorrect"));;
    }
}
