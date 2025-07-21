package ui_mobile;

import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.SplashScreen;

public class SplashTest extends AppiumConfig {
    @Test
    public void splashTest(){
        Assert.assertTrue(new SplashScreen(driver)
                .validateVersionApp("Version 1.0.0"));
    }

    @Test
    public void AuthLoadTimeTest() {
        AuthenticationScreen authScreen = new AuthenticationScreen(driver);

        long start = System.currentTimeMillis();

        boolean loaded = authScreen.isAuthScreenLoaded();

        long duration = System.currentTimeMillis() - start;

        Assert.assertTrue(loaded, "Auth screen not loaded");
        System.out.println("Auth screen loaded in " + duration + " ms");
    }
}
