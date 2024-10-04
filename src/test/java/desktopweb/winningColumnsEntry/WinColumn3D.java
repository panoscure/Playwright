package desktopweb.winningColumnsEntry;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.objects.desktopweb.lotteryUI.LotteryUIAuthorizationRequest;
import page.objects.desktopweb.lotteryUI.LotteryUIDrawClose;
import page.objects.desktopweb.lotteryUI.LotteryUIEnterWinningNumbers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

public class WinColumn3D {
    public Page page;
    Properties properties = new Properties();
    @BeforeMethod(alwaysRun = true)
    public void initializeWebDriver() throws IOException {
        properties.load(Files.newInputStream(Paths.get("src/test/resources/common.properties")));
        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }


    @Test(priority=1, description = "Validate_3_Digit")
    public void QAAU23_1568() throws InterruptedException {


        LotteryUIDrawClose lotteryUI = new LotteryUIDrawClose(page);
        LotteryUIAuthorizationRequest lUI = new LotteryUIAuthorizationRequest(page);
        LotteryUIEnterWinningNumbers win_numbers = new LotteryUIEnterWinningNumbers(page);



        /*******************************Enter Winning Numbers from Lottery UI**********************************************/
        page.navigate(properties.getProperty("taiwan.lottery.ui.url"));
        lUI.lotteryUILogin(properties.getProperty("back.office.user"),properties.getProperty("back.office.password"));
        page.navigate(properties.getProperty("taiwan.lottery.ui.url"));

        win_numbers.selectWinningNumbersTab().selectWinningColumnsTab();
        win_numbers.selectNumbersXD("2108",3, Arrays.asList(1,2,3));
        lotteryUI.selectMonitoringAndOperationsTab().selectDrawOperationsTab().selectModifyDrawStatusTab().selectGameFromDropDown("2108","2");
        lotteryUI.select100Entries();
        lotteryUI.changeToPaymentSuspended().selectLatestDrawToSuspend();
        /******************************Winning Numbers Entered******************************/

    }



}
