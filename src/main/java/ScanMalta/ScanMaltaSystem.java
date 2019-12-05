package ScanMalta;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ScanMaltaSystem {

    WebDriver browser;
    String email = "farseersc28@gmail.com";
    String password = "Test@123";

    public ScanMaltaSystem(WebDriver browser) {
        this.browser = browser;
        browser.get("https://www.scanmalta.com/newstore/customer/account/login/");
    }

    boolean isLoggedIn = false,
            isLoggedOut = true,
            isSeaching = false,
            isAddingToCart = false,
            isRemovingFromCart = false,
            isCheckingOut = false;

    boolean isLoggedIn(){ return isLoggedIn; }
    boolean isLoggedOut(){ return isLoggedOut; }
    boolean isSeaching(){ return isSeaching; }
    boolean isAddingToCart(){ return isAddingToCart; }
    boolean isRemovingFromCart(){ return isRemovingFromCart; }
    boolean isCheckingOut(){ return isCheckingOut; }

    public void loggingIn() {
        isLoggedIn = true;
        isLoggedOut = false;
        isSeaching = false;
        isAddingToCart = false;
        isRemovingFromCart = false;
        isCheckingOut = false;

        browser.get("https://www.scanmalta.com/newstore/customer/account/login/");
        browser.findElement(By.name("login[username]")).sendKeys(email);
        browser.findElement(By.name("login[password]")).sendKeys(password);
        browser.findElement(By.name("send")).click();
    }

    public void loggingOut() {
        browser.get("https://www.scanmalta.com/newstore/customer/account/logout/");
    }
}
