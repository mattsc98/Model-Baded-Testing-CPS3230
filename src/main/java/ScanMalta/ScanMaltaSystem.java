package ScanMalta;
import org.openqa.selenium.WebDriver;

public class ScanMaltaSystem {

    WebDriver browser;
    ScanMaltaPageObject sut;

    String email = "farseersc28@gmail.com";
    String password = "Test@123";
    String product = "ssd";

    public ScanMaltaSystem(WebDriver browser) {
        this.browser = browser;
        browser.get("https://www.scanmalta.com/newstore/");
        sut = new ScanMaltaPageObject(browser);
    }

    boolean isLoggedIn = false,
            isLoggedOut = true,
            isSearching = false,
            isAddingToCart = false,
            isRemovingFromCart = false,
            isCheckingOut = false;

    boolean isLoggedIn(){ return isLoggedIn; }
    boolean isLoggedOut(){ return isLoggedOut; }
    boolean isSearching(){ return isSearching; }
    boolean isAddingToCart(){ return isAddingToCart; }
    boolean isRemovingFromCart(){ return isRemovingFromCart; }
    boolean isCheckingOut(){ return isCheckingOut; }

    public void loggingIn() {
        isLoggedIn = true;
        isLoggedOut = false;
        isSearching = true;
        isAddingToCart = true;
        isRemovingFromCart = true;
        isCheckingOut = false;

        sut.getLoginPage();

        sut.login(email, password);
    }

    public void loggingOut() {
        isLoggedIn = false;
        isLoggedOut = true;
        isSearching = true;
        isAddingToCart = true;
        isRemovingFromCart = true;
        isCheckingOut = false;

        sut.logout();
    }

    public void searching() {
        isSearching = true;
        isAddingToCart = true;
        isRemovingFromCart = true;
        isCheckingOut = false;

        sut.search(product);
    }

    public void addingToCart() {
        isSearching = true;
        isAddingToCart = true;
        isRemovingFromCart = false;
        isCheckingOut = false;

        sut.selectFirstProduct();
        sut.addToCart();
    }

    public void removingFromCart() {
        isSearching = true;
        isAddingToCart = true;
        isRemovingFromCart = true;
        isCheckingOut = false;

        sut.removeFirstProduct();
    }

    public void checkingOut() {
        isLoggedIn = true;
        isLoggedOut = false;
        isSearching = false;
        isAddingToCart = true;
        isRemovingFromCart = true;
        isCheckingOut = true;

        //since a user must be logged in to checkout:
        sut.getLoginPage();
        sut.login(email, password);

        sut.checkoutPage();
    }
}
