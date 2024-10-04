package page.objects.desktopweb.lotteryUI;


import com.microsoft.playwright.Page;
//import jdk.internal.org.jline.utils.Log;
import utilities.dateTime;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LotteryUIEnterWinningNumbers {


    Page page;

    public static String winning_numbers_path = "//span[text()='Winning Numbers']";

    public static String winning_column_path = "//span[text()='Winning Columns']";


    public static String input_field = "//div[@class=' css-1ab7ooq']";

    public static String input_field2 = "//input[@id='react-select-2-input']";


    public static String numbers_path(int number,int line) {return "(//div[@class='col-xs-1']//a[text()='"+number+"'])["+line+"]";}

    public static String second_board_numbers_path(int number,int line) {return "(//div[@class='col-xs-1']//a[text()='"+number+"'])["+line+"]";}

    public static String save_btn_path = "//button[text()='Save']";

    public static String available_draw_existence = "//select[@name='draw']/option[@value!='']";

    public static String second_user_requested = "//div[@class='rrt-title' and text()='Error']";
    public static String user_menu_path = "(//li[@class='dropdown'])[1]";

    public static String logout_path = "//a[@name='logout']";




    public String pin;

    public LotteryUIEnterWinningNumbers(Page page) {
        this.page = page;
    }



    dateTime dt = new dateTime();

    public LotteryUIEnterWinningNumbers selectWinningNumbersTab() {
        page.waitForSelector("xpath=" + winning_numbers_path).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        winning_numbers_path);
        //button.click();
        return this;
    }
    public LotteryUIEnterWinningNumbers selectWinningColumnsTab() {
        page.waitForSelector("xpath=" + winning_column_path).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        winning_column_path);
        //button.click();
        return this;
    }
    public LotteryUIEnterWinningNumbers selectInsertGameInput(String game) {
        page.waitForSelector("xpath=" + input_field).click();
        page.waitForSelector("xpath=" + input_field2).click();
        page.keyboard().type(game);
        page.keyboard().press("Enter");

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        input_field);
        //button.click();
        //WebElement field = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        input_field2);
        //field.sendKeys(game);
        //field.sendKeys(Keys.ENTER);
        return this;
    }


    public LotteryUIEnterWinningNumbers selectNumbersXD(String game_id, int num_of_numbers, List<Integer> numbers) throws InterruptedException {
        //int[] numbers = new int[num_of_numbers];
        for(int i =0;i<num_of_numbers;i++){
            numbers.set(i, i);
        }
        selectInsertGameInput(game_id);
        page.waitForTimeout(2000);

        //Generic.delay(2);
//        await().atLeast(10000, TimeUnit.MILLISECONDS).and().atMost(130, TimeUnit.SECONDS).untilAsserted(() -> {
//            Truth.assertThat(webDriver.findElements(available_draw_existence).size()).isGreaterThan(0);
//        });
        List elements = page.locator(available_draw_existence).elementHandles();


        //List<WebElement> elements = webDriver.findElements(available_draw_existence);
        int size = elements.size();
        //Log.info("Number of Draws for winning column: "+size);
        System.out.println("Number of Draws for winning column: "+size);
        while (size > 0) {
            selectNumbers(numbers);
            saveWinNumbersBtn();
            page.waitForTimeout(5000);

            //Generic.delay(5);
            selectInsertGameInput(game_id);
            page.waitForTimeout(5000);

            //Generic.delay(2);
            List exist = page.locator(available_draw_existence).elementHandles();
            //List<WebElement> exist = webDriver.findElements(available_draw_existence);
            size = exist.size();
        }
        return this;
    }
    public LotteryUIEnterWinningNumbers selectNumbersBingo(String game_id) throws InterruptedException {
        int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        selectInsertGameInput(game_id);
        page.waitForTimeout(2000);

        /*Generic.delay(2);
        await().atLeast(10000, TimeUnit.MILLISECONDS).and().atMost(130, TimeUnit.SECONDS).untilAsserted(() -> {
            Truth.assertThat(webDriver.findElements(available_draw_existence).size()).isGreaterThan(0);
        });
         */

        List elements = page.locator(available_draw_existence).elementHandles();

        //List<WebElement> elements = webDriver.findElements(available_draw_existence);
        int size = elements.size();
        //Log.info("Number of Draws for winning column: "+size);
        System.out.println("Number of Draws for winning column: "+size);
        while (size > 0) {
            selectNumbersOneBoard(numbers);
            if(game_id=="5134")
                selectNumbersSecondBoard(1);
            saveWinNumbersBtn();
            /*******Check if it requests for second user******/
            try{
                // Wait for the element to be present
                page.waitForSelector(second_user_requested, new Page.WaitForSelectorOptions().setTimeout(10000));

                // Perform your assertion
                boolean isElementPresent = page.locator(second_user_requested).evaluateHandle("el => el.textContent").jsonValue().toString().length() > 0;
                assert isElementPresent : "Element is not present";
                //await().atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
                    //Truth.assertThat(webDriver.findElements(second_user_requested).size()).isGreaterThan(0);
                //});
            }
            catch (Exception e)
            {
                //Log.info("2nd User is not needed");
                System.out.println("2nd User is not needed");
            }
            List second_user = page.locator(second_user_requested).elementHandles();

            //List<WebElement> second_user = webDriver.findElements(second_user_requested);
            int size_second_user = second_user.size();
            System.out.println("If second user exists: "+size_second_user);
            //Log.info("If second user exists: "+size_second_user);
            if(size_second_user>0){
                loginWithOtherUser(game_id);
            }
            /*******End of second user check******/
            page.waitForTimeout(2000);

            //Generic.delay(2);
            selectInsertGameInput(game_id);
            page.waitForTimeout(4000);

            //Generic.delay(4);
            List exist = page.locator(available_draw_existence).elementHandles();

            //List<WebElement> exist = webDriver.findElements(available_draw_existence);
            size = exist.size();
        }
        return this;
    }

    public LotteryUIEnterWinningNumbers selectNumbersBingoOnce(String game_id) throws InterruptedException {
        int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        selectInsertGameInput(game_id);
        page.waitForTimeout(2000);

        //Generic.delay(2);
        selectNumbersOneBoard(numbers);
        if(game_id=="5134")
            selectNumbersSecondBoard(1);
        saveWinNumbersBtn();
        page.waitForTimeout(2000);

        //Generic.delay(2);
        return this;
    }

    public LotteryUIEnterWinningNumbers selectNumbersOneBoard(int [] numbers) {
        for(int number : numbers) {
            page.waitForSelector("xpath=" + numbers_path(number,1)).click();

            //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                    //numbers_path(number,1));
            //button.click();
        }
        return this;
    }

    public LotteryUIEnterWinningNumbers selectNumbersSecondBoard(int number) throws InterruptedException {
        page.evaluate("arguments[0].scrollIntoView(true);");

        //WebElement element = webDriver.findElement(second_board_numbers_path(number,3));
        //((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        //Generic.delay(1);
        page.waitForTimeout(1000);

        page.waitForSelector("xpath=" + second_board_numbers_path(number,3)).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                //second_board_numbers_path(number,3));
        //button.click();
        return this;
    }


    public LotteryUIEnterWinningNumbers selectNumbers(List<Integer> numbers) {
        for(int number : numbers) {
            page.waitForSelector("xpath=" + numbers_path(number,number+1)).click();

            //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
            //        numbers_path(number,number+1));
            //button.click();
        }
        return this;
    }

    public LotteryUIEnterWinningNumbers saveWinNumbersBtn() throws InterruptedException {
        page.evaluate("arguments[0].scrollIntoView(true);");

        //WebElement element = webDriver.findElement(save_btn_path);
        //((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        //Generic.delay(1);
        page.waitForTimeout(1000);

        page.waitForSelector("xpath=" + save_btn_path).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        save_btn_path);
        //button.click();
        return this;
    }

    public LotteryUIEnterWinningNumbers clickLogout() throws InterruptedException {
        //Generic.delay(7);
        page.waitForTimeout(7000);

        page.waitForSelector("xpath=" + user_menu_path).click();

        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        user_menu_path);
        //button.click();
        page.waitForSelector("xpath=" + logout_path).click();

        //WebElement button2 = SeleniumWaits.visibilityOfElementLocated(webDriver,
        //        logout_path);
        //button2.click();
        return this;
    }

    public LotteryUIEnterWinningNumbers loginWithOtherUser(String game_id) throws InterruptedException {
        Properties properties = new Properties();
        clickLogout();
        LotteryUIAuthorizationRequest lUI = new LotteryUIAuthorizationRequest(page);
        lUI.lotteryUILogin(properties.getProperty("back.office.2nd.user"),properties.getProperty("back.office.2nd.password"));
        selectWinningNumbersTab().selectWinningColumnsTab();
        selectNumbersBingoOnce(game_id);
        clickLogout();
        lUI.lotteryUILogin(properties.getProperty("back.office.user"),properties.getProperty("back.office.password"));
        selectWinningNumbersTab().selectWinningColumnsTab();
        return this;
    }

}
