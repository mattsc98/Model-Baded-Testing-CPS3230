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

    private boolean isLoggedIn, isLoggedOut, isSeaching,
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
        isSeaching = false;
        isAddingToCart = false;
        isRemovingFromCart = false;
        isCheckingOut = false;

        if(b) {
            sut = new ScanMaltaSystem(browser);
        }
    }

    public boolean loggingInGuard() {
        return getState().equals(ScanMaltaStates.LOGGED_OUT);
    }

    public @Action
    void loggingIn() {

        sut.loggingIn();

        isLoggedIn = true;
        isLoggedOut = false;
        isSeaching = false;
        isAddingToCart = false;
        isRemovingFromCart = false;
        isCheckingOut = false;

        modelState = ScanMaltaStates.LOGGED_IN;

        assertEquals("", isLoggedIn, sut.isLoggedIn());
    }
}
