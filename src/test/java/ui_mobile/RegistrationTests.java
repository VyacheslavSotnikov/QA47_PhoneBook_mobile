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
    public void registrationNegativeWrongPasswordTest() {
        authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeRegistrationForm(createUserWrongPassword("Password123"));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Must contain at least 1 uppercase letter"));
    }

    @Test
    public void registrationWithInvalidEmail() {
        authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeRegistrationForm(createUserWrongEmail("email.com"));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("must be a well-formed"));
    }

    @Test
    public void registrationNegativeDublicateEmail() {
        authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeRegistrationForm(createUserWrongEmail("sotiga2015@gmail.com"));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("User already exist"));
    }

    @Test
    public void registrationWithShortPassword() {
        User user = createUserWrongPassword("123");
        authenticationScreen.typeRegistrationForm(user);
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Must contain at least 1 uppercase letter"));
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
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("User already exists"));
    }
}
