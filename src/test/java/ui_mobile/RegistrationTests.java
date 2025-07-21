package ui_mobile;

import config.AppiumConfig;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactsScreen;
import screens.SplashScreen;

import static utils.UserFabric.*;

public class RegistrationTests extends AppiumConfig {

    AuthenticationScreen authenticationScreen;

    @BeforeMethod
    public void goToAuthScreen() {
        new SplashScreen(driver);
        authenticationScreen = new AuthenticationScreen(driver);
    }

    @Test
    public void registrationPositiveTest() {
        authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeRegistrationForm(createUser());
        Assert.assertTrue(new ContactsScreen(driver)
                .validateContactsScreenOpen("Contact list"));
    }

    @Test
    public void registrationWithInvalidEmail() {
        User user = createUserWrongEmail("sotiga2015gmail.com");
        authenticationScreen.typeRegistrationForm(user);
        Assert.assertFalse(new ContactsScreen(driver)
                .validateContactsScreenOpen("Contact list"));
    }

    @Test
    public void registrationWithShortPassword() {
        User user = createUserWrongPassword("123");
        authenticationScreen.typeRegistrationForm(user);
        Assert.assertFalse(new ContactsScreen(driver)
                .validateContactsScreenOpen("Contact list"));
    }

    @Test
    public void registrationWithEmptyFields() {
        User user = User.builder()
                .username("")
                .password("")
                .build();

        authenticationScreen.typeRegistrationForm(user);
        Assert.assertFalse(new ContactsScreen(driver)
                .validateContactsScreenOpen("Contact list"));
    }

    @Test
    public void registrationWithExistingUser() {
        User user = User.builder()
                .username("sotiga2015@gmail.com")
                .password("Sh12345!@")
                .build();
        authenticationScreen.typeRegistrationForm(user);
        Assert.assertFalse(new ContactsScreen(driver)
                .validateContactsScreenOpen("Contact list"));
    }
}
