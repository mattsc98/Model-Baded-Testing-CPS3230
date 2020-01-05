package ScanMalta;

import ScanMalta.enums.ScanMaltaStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class ScanModel implements FsmModel {

    WebDriver browser;

    private ScanMaltaSystem sut;
    private ScanMaltaStates modelState;

    private boolean isLoggedIn, isLoggedOut, isSearching,
                    isAddingToCart, isRemovingFromCart,
                    isCheckingOut;

    ScanModel(WebDriver browser) {
        this.browser = browser;
        sut = new ScanMaltaSystem(browser);
    }

    public Object getState() {
        return modelState;
    }

    public void reset(boolean b) {

        sut.loggingOut();
        modelState = ScanMaltaStates.LOGGED_OUT;
        isLoggedIn = false;
        isLoggedOut = true;
        isSearching = false;
        isAddingToCart = false;
        isRemovingFromCart = false;
        isCheckingOut = false;

        if(b) {
            sut = new ScanMaltaSystem(browser);
        }
    }

    public boolean loggingInGuard() {
        return (getState().equals(ScanMaltaStates.LOGGED_OUT))||
                (getState().equals(ScanMaltaStates.SEARCHING)) ||
                (getState().equals(ScanMaltaStates.ADDING_TO_CART)) ||
                (getState().equals(ScanMaltaStates.REMOVING_FROM_CART)) ||
                ((getState().equals(ScanMaltaStates.CHECKING_OUT)) && isLoggedOut);
    }
    public @Action
    void loggingIn() {

        sut.loggingIn();

        isLoggedIn = true;
        isLoggedOut = false;
        isSearching = true;
        isAddingToCart = true;
        isRemovingFromCart = true;
        isCheckingOut = false;

        modelState = ScanMaltaStates.LOGGED_IN;

        assertEquals("", isLoggedIn, sut.isLoggedIn());
    }

    public boolean loggingOutGuard() {
        return (getState().equals(ScanMaltaStates.LOGGED_IN)) ||
                (getState().equals(ScanMaltaStates.SEARCHING)) ||
                (getState().equals(ScanMaltaStates.ADDING_TO_CART)) ||
                (getState().equals(ScanMaltaStates.REMOVING_FROM_CART));
    }
    public @Action
    void loggingOut() {

        sut.loggingOut();

        isLoggedIn = false;
        isLoggedOut = true;
        isSearching = true;
        isAddingToCart = true;
        isRemovingFromCart = true;
        isCheckingOut = false;

        modelState = ScanMaltaStates.LOGGED_OUT;

        assertEquals("", isLoggedOut, sut.isLoggedOut());
    }

    public boolean searchingGuard() {
        return (getState().equals(ScanMaltaStates.LOGGED_IN)) ||
                (getState().equals(ScanMaltaStates.LOGGED_OUT)) ||
                (getState().equals(ScanMaltaStates.SEARCHING)) ||
                (getState().equals(ScanMaltaStates.ADDING_TO_CART)) ||
                (getState().equals(ScanMaltaStates.REMOVING_FROM_CART));
    }
    public @Action
    void searching() {

        sut.searching();

        isSearching = true;
        isAddingToCart = true;
        isRemovingFromCart = true;
        isCheckingOut = false;

        modelState = ScanMaltaStates.SEARCHING;

        assertEquals("", isSearching, sut.isSearching());
    }

    public boolean addingToCartGuard() {
        return  (getState().equals(ScanMaltaStates.SEARCHING));
    }
    public @Action
    void addingToCart() {

        sut.addingToCart();

        isSearching = true;
        isAddingToCart = true;
        isRemovingFromCart = false;
        isCheckingOut = false;

        modelState = ScanMaltaStates.ADDING_TO_CART;

        assertEquals("", isAddingToCart, sut.isAddingToCart());
        //assertEquals("", isLoggedIn, sut.isLoggedIn());
    }

    public boolean removingFromCartGuard() {
        return  (getState().equals(ScanMaltaStates.REMOVING_FROM_CART)) ||
                (getState().equals(ScanMaltaStates.ADDING_TO_CART)) ;
    }
    public @Action
    void removingFromCart() {

        sut.removingFromCart();

        isSearching = true;
        isAddingToCart = true;
        isRemovingFromCart = true;
        isCheckingOut = false;

        modelState = ScanMaltaStates.REMOVING_FROM_CART;

        assertEquals("", isRemovingFromCart, sut.isRemovingFromCart());
    }

    public boolean checkingOutGuard() {
        return  (getState().equals(ScanMaltaStates.ADDING_TO_CART))  ||
                (getState().equals(ScanMaltaStates.REMOVING_FROM_CART)) &&
                isLoggedIn;
    }
    public @Action
    void checkingOut() {

        sut.checkingOut();

        isLoggedIn = true;
        isLoggedOut = false;
        isSearching = false;
        isAddingToCart = true;
        isRemovingFromCart = true;
        isCheckingOut = true;

        modelState = ScanMaltaStates.CHECKING_OUT;

        assertEquals("", isCheckingOut, sut.isCheckingOut());
    }
}
