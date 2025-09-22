package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import utils.DataReader;

import java.util.HashMap;

public class LoginTest extends BaseTest {
    @Test(dataProvider = "getDummyData",dataProviderClass = DataReader.class)
    public void loginTest(HashMap<String,String> values) throws Exception{
        loginPage.login(values.get("userName"),values.get("password"));
        if (values.get("valid").equals("false")) {
            String toast_message = loginPage.getPageObject().locator("//div[@aria-label='Incorrect email or password.']").textContent();
            String message = "Incorrect email or password.";
            Assert.assertNotEquals(toast_message.trim(), message);
        }
    }
}

