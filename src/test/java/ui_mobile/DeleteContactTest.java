package ui_mobile;

import config.AppiumConfig;
import dto.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

public class DeleteContactTest  extends AppiumConfig {
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
    public void deleteContactTest(){
        contactsScreen.swipeRightToLeft();
        contactsScreen.clickBtnYes();
    }
}
