package pageobjecs;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import base.AbstractClass;

public class LoginPage extends AbstractClass {
    Page page;
    private Locator email;
    private Locator password;
    private Locator login;
    public LoginPage(Page page){

        super(page);
        System.out.println("login page executin..........");
        System.out.println(page);
        this.page = page;
        email = this.page.getByPlaceholder("email@example.com");
        password = this.page.getByPlaceholder("enter your passsword");
        login = this.page.locator("#login");
    }

    public Page getPageObject() {
        return this.page;
    }

    public ProductCatalogue login(String userName, String password) {
        email.fill(userName);
        this.password.fill(password);
        login.click();
        return new ProductCatalogue();
    }

}
