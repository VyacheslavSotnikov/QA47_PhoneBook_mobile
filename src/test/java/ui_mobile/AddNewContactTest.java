package ui_mobile;

import config.AppiumConfig;
import dto.Contact;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static org.openqa.selenium.devtools.v108.debugger.Debugger.pause;
import static utils.ContactFactory.*;

public class AddNewContactTest  extends AppiumConfig {

    ContactsScreen contactsScreen;
    AddNewContactScreen addNewContactScreen;
    ContactScreen contactScreen;

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
    public void AddNewContactPositiveTest_validateDataContact(){
        Contact contact = createPositiveContact();
        addNewContactScreen.typeContactForm(contact);
       contactsScreen.scrollLastContact();
       contactsScreen.clickToLastContact();
       contactScreen = new ContactScreen(driver);
       contactScreen.getContact();
       Assert.assertEquals(contactScreen.getContact(), contact);
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

    @Test
    public void AddNewContactNegativeTest_withoutAddress(){
        addNewContactScreen.typeContactForm(createNegativeContact_wrongAddress(""));
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("address=must not be blank"));
    }
}


