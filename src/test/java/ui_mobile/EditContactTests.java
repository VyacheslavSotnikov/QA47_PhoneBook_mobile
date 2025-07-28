package ui_mobile;

import config.AppiumConfig;
import dto.Contact;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static utils.ContactFactory.*;

public class EditContactTests extends AppiumConfig {
    ContactsScreen contactsScreen;

    @BeforeMethod
    public void login() {
        new SplashScreen(driver);
        new AuthenticationScreen(driver)
                .typeLoginForm(User.builder()
                        .username("sotiga2015@gmail.com")
                        .password("Sh12345!@")
                        .build());
        contactsScreen = new ContactsScreen(driver);
    }

    @Test
    public void editContactPositiveTest(){
        contactsScreen.swipeLeftToRight()
                .editContact(createPositiveContact());
    }

    @Test
    public void editContactNegativeWithEmptyName(){
        contactsScreen.swipeLeftToRight()
                .editContact(createNegativeContact_wrongName(""));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("name=must not be blank"));
    }

    @Test
    public void editContactNegativeWithEmptyLastName(){
        contactsScreen.swipeLeftToRight()
                .editContact(createNegativeContact_wrongLastName(""));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("lastName=must not be blank"));
    }

    @Test
    public void editContactNegativeWithEmptyPhone(){
        contactsScreen.swipeLeftToRight()
                .editContact(createNegativeContact_wrongPhone(""));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("phone=Phone number must contain only digits! And length min 10, max 15!"));
    }

    @Test
    public void editContactNegativeWithEmptyAddress(){
        contactsScreen.swipeLeftToRight()
                .editContact(createNegativeContact_wrongAddress(""));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("address=must not be blank"));
    }

    @Test
    public void editContactNegativeWithWrongEmail(){
        contactsScreen.swipeLeftToRight()
                .editContact(createNegativeContact_wrongEmail("1"));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("email=must be a well-formed email address"));
    }

    @Test
    public void editContactNegativeWithWrongPhone(){
        contactsScreen.swipeLeftToRight()
                .editContact(createNegativeContact_wrongPhone("1"));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("phone=Phone number must contain only digits! And length min 10, max 15!"));
    }
}

