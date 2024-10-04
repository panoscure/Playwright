package page.objects.desktopweb.lotteryUI;


import com.microsoft.playwright.Page;

public class LotteryUIAuthorizationRequest {

    Page page;
    public static String user_name = "//input[@id='username']";
    public static String password_element = "//input[@id='password']";

    public static String btn_login = "//button[@id='btnLogin']";

    public String pin;

    public LotteryUIAuthorizationRequest(Page page) {
        this.page = page;
    }


    public LotteryUIAuthorizationRequest lotteryUILogin(String username, String password) {
        enterLotteryUIUsername(username);
        enterLotteryUIPassword(password);
        clickLoginButton();
        return this;
    }

    public LotteryUIAuthorizationRequest enterLotteryUIUsername(String username) {
        page.waitForSelector("xpath=" + user_name).click();
        page.keyboard().type(username);
        //WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        user_name);
        //games.sendKeys(username);
        return this;
    }



    public LotteryUIAuthorizationRequest clickLoginButton() {
        page.waitForSelector("xpath=" + btn_login).click();

        //WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        By.xpath("//button[@id='btnLogin']"));
        //games.click();
        return this;
    }

    public LotteryUIAuthorizationRequest enterLotteryUIPassword(String password) {
        page.waitForSelector("xpath=" + password_element).click();
        page.keyboard().type(password);

        //WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        password_element);
        //games.sendKeys(password);
        return this;
    }

}
