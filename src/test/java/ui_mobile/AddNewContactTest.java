package ui_mobile;

import config.AppiumConfig;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static utils.ContactFactory.*;

public class AddNewContactTest  extends AppiumConfig {

    ContactsScreen contactsScreen;
    AddNewContactScreen addNewContactScreen;
    ErrorScreen errorScreen;

    @BeforeMethod
    public void login() {
        new SplashScreen(driver);
        new AuthenticationScreen(driver)
        .typeLoginForm(User.builder()
                .username("sotiga2015@gmail.com")
                .password("Sh12345!@")
                .build());
        contactsScreen = new ContactsScreen(driver);
        contactsScreen.clickBtnPlus();
        addNewContactScreen = new AddNewContactScreen(driver);
    }

    @Test
    public void AddNewContactPositiveTest(){
        addNewContactScreen.typeContactForm(createPositiveContact());
        Assert.assertTrue(addNewContactScreen.validateMessageSuccess("Contact was added!"));
    }

    @Test
    public void AddNewContactNegativeTest_wrongEmail(){
        addNewContactScreen.typeContactForm(createNegativeContact_wrongEmail("sotiga201gmail.com"));
    }

    @Test
    public void AddNewContactNegativeTest_wrongPhone(){
        addNewContactScreen.typeContactForm(createNegativeContact_wrongPhone(""));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Phone number must contain only"));
    }

    @Test
    public void AddNewContactNegativeTest_withoutName(){
        addNewContactScreen.typeContactForm(createNegativeContact_wrongName(""));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("name=must not be blank"));
    }
}


