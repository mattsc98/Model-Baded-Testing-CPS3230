package ScanMalta;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ScanMaltaPageObject {

    WebDriver browser = null;

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }

    public ScanMaltaPageObject(WebDriver browser) {
        this.browser = browser;
    }


    public void getLoginPage() {
        browser.get("https://www.scanmalta.com/newstore/customer/account/logout/"); //due to login on login error
        browser.get("https://www.scanmalta.com/newstore/customer/account/login/");
        sleep(2);
    }

    public void login(String username, String password) {
        browser.findElement(By.name("login[username]")).sendKeys(username);
        browser.findElement(By.name("login[password]")).sendKeys(password);
        browser.findElement(By.name("send")).submit();
        sleep(2);
    }

    public void logout() {
        browser.get("https://www.scanmalta.com/newstore/customer/account/logout/");
        sleep(2);
        browser.get("https://www.scanmalta.com/newstore/"); //since sometimes logging out takes you to the login page
        sleep(2);
    }

    public void search(String product) {
        browser.findElement(By.id("search")).sendKeys(product);
        browser.findElement(By.className("icon-search")).submit();
        sleep(2);
    }

    public void selectFirstProduct() {
        //create list to hold all the list item products displayed, then retrieve the 1st one
        List<WebElement> productsList = browser.findElements(By.className("item-images"));
        WebElement firstProduct = productsList.get(0);
        firstProduct.click();
        sleep(2);
    }

    public void addToCart() {
        browser.findElement(By.id("product-addtocart-button")).submit();
        sleep(7); //due to popup that follows
    }

    public int getCartAmount() {
        String amount = browser.findElement(By.xpath("//span[contains(text(), 'items')]")).getText();
        return Integer.parseInt(amount.split(" ")[0]);
    }

    public void removeFirstProduct() {
        if(getCartAmount() != 0) {

            WebElement shoppingCartTable = browser.findElement(By.id("shopping-cart-table"));
            WebElement shoppingCartTableBody = shoppingCartTable.findElement(By.xpath("//table/tbody"));
            WebElement firstProduct = shoppingCartTableBody.findElement(By.className("first"));

            firstProduct.findElement(By.className("btn-remove")).click();
        }
        sleep(2);
    }

    public void checkoutPage() {
        browser.get("https://www.scanmalta.com/newstore/checkout/onepage/");
    }
}
