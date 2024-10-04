package page.objects.desktopweb.lotteryUI;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
//import jdk.internal.org.jline.utils.Log;
import utilities.dateTime;

import java.util.List;

public class LotteryUIDrawClose {

    Page page;

    public static String monitoring_and_operations_path = "//span[text()='Monitoring and Operations']";

    public static String draw_operations_path = "//span[text()='Draw Operations']";
    public static String modify_draw_status_path = "//a[@id='drawOperations-tab-mdd']";


    public static String game_path(String game,String time_or_status){return "(//select[@name='game']/option[@value='"+game+"'])["+time_or_status+"]";}

    public static String choose_game = "(//div[@class='form-group']/select[@name='game'])[2]";

    public static String draw_option_path = "//select[@name='draw']//option[2]";

    public static String draw_time_path = "//span[@class='input-group']//input[@type='text']";

    public static String button_modify_path = "//button[text()='Modify Draw Time']";

    public static String somewhere_path = "//a[@id='drawOperations-tab-mdt']";

    public static String payment_suspended_existence = "//tr/td[text()='Payments Suspended']/following-sibling::td/a/span[@title='View']";

    public static String suspend_path = "(//tr/td[text()='Payments Suspended']/following-sibling::td/a/span[@title='View'])[1]";

    public static String release_payments_path = "//button[text()='Release Payments']";

    public static String modify_draw_status_btn_path = "//button[text()='Modify Draw Status']";

    public static String num_of_entries_path = "//select[@name='limit']/option[@value='100']";



    public String pin;

    public LotteryUIDrawClose(Page page) {
        this.page = page;
    }



    dateTime dt = new dateTime();



    public LotteryUIDrawClose selectMonitoringAndOperationsTab() {

        // Wait for the element to be visible and click
        page.waitForSelector("xpath=" + monitoring_and_operations_path).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        monitoring_and_operations_path);
        //button.click();
        return this;
    }
    public LotteryUIDrawClose selectDrawOperationsTab() {
        page.waitForSelector("xpath=" + draw_operations_path).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        draw_operations_path);
        //button.click();
        return this;
    }
    public LotteryUIDrawClose selectModifyDrawStatusTab() {
        page.waitForSelector("xpath=" + modify_draw_status_path).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        modify_draw_status_path);
        //button.click();
        return this;
    }
    public LotteryUIDrawClose selectGameFromDropDown(String game, String draw_time_or_status) {
        page.waitForSelector("xpath=" + choose_game).click();

        //Locator buttonLocator = page.locator(game_path(game,draw_time_or_status));
        //buttonLocator.evaluate("button => button.click()");

        page.waitForSelector(game_path(game,draw_time_or_status)).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        game_path(game,draw_time_or_status));
        //button.click();
        return this;
    }

    public LotteryUIDrawClose changeToPaymentSuspended() throws InterruptedException {
        List elements = page.locator(payment_suspended_existence).elementHandles();

        //Generic.delay(2);
        //List<WebElement> elements = webDriver.findElements(payment_suspended_existence);
        int size = elements.size();
        //Log.info("Number of Draws for winning column: "+size);
        while (size > 0) {
            //try{
            selectLatestDrawToSuspend();
            //}
            //catch(Exception e){
            //JavascriptExecutor js = (JavascriptExecutor) webDriver;
            //js.executeScript("window.scrollBy(0,350)", "");
            //}
            //Generic.delay(2);
            List exist = page.locator(payment_suspended_existence).elementHandles();
            //List<WebElement> exist = webDriver.findElements(payment_suspended_existence);
            size = exist.size();
        }
        return this;
    }
    public LotteryUIDrawClose selectLatestDrawToSuspend() throws InterruptedException {
        try {
            page.waitForSelector("xpath=" + suspend_path);
            page.evaluate("() => {\n  window.scrollBy(0, -100)\n}");
            page.waitForSelector("xpath=" + suspend_path).click();
            page.waitForSelector("xpath=" + release_payments_path).click();
            page.waitForSelector("xpath=" + modify_draw_status_btn_path).click();
            page.evaluate("() => {\n  scroll(0, -100)\n}");

            //WebElement element = webDriver.findElement(suspend_path);
            //JavascriptExecutor js = (JavascriptExecutor) webDriver;
            //Generic.delay(2);
            //((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            //Generic.delay(2);
            //js.executeScript("window.scrollBy(0, -100)", "");
            //Generic.delay(2);
            //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
             //       suspend_path);
            //button.click();
            //WebElement button2 = SeleniumWaits.visibilityOfElementLocated(webDriver,
                    //release_payments_path);
            //button2.click();
            //WebElement button3 = SeleniumWaits.visibilityOfElementLocated(webDriver,
                    //modify_draw_status_btn_path);
            //button3.click();
            //js.executeScript("scroll(0, -100)", "");
        }
        catch (Exception e){
            //Log.info("No more draws with payment Suspended");
            System.out.println("No more draws with payment Suspended");
        }
        return this;
    }



    public LotteryUIDrawClose selectDraw() {
        page.waitForSelector("xpath=" + draw_option_path).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        draw_option_path);
        //button.click();
        return this;
    }

    public LotteryUIDrawClose newDateTime() {
        page.waitForSelector("xpath=" + draw_time_path).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        draw_time_path);
        //button.click();
        for(int i=0;i<20;i++){
            page.keyboard().down("Shift");
            page.keyboard().down("a");
            page.keyboard().press("Delete");
            page.keyboard().up("Shift");
            page.keyboard().up("a");

            //button.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        }
        page.keyboard().type(dt.dateTime());

        //button.sendKeys(dt.dateTime());
        return this;
    }

    public LotteryUIDrawClose modifyDrawTimeButton() throws InterruptedException {
        page.waitForSelector("xpath=" + somewhere_path).click();

        //WebElement button2 = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        somewhere_path);
        //button2.click();
        page.waitForSelector("xpath=" + button_modify_path).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        button_modify_path);
        //button.click();
        //Generic.delay(120);
        page.waitForTimeout(120000);
        return this;
    }

    public LotteryUIDrawClose select100Entries() {
        page.waitForSelector("xpath=" + num_of_entries_path).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        num_of_entries_path);
        //button.click();
        return this;
    }

}
