package myprojects.automation.assignment3.tests;

import myprojects.automation.assignment3.BaseScript;
import myprojects.automation.assignment3.GeneralActions;
import myprojects.automation.assignment3.support.EventHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class CreateCategoryTest extends BaseScript {

    public static void main(String[] args) {
        // TODO prepare driver object
         WebDriver driver = getConfiguredDriver();
         ((EventFiringWebDriver) driver).register(new EventHandler());
        // ...
        GeneralActions generalActions = new GeneralActions(driver);
        // login
        generalActions.login("webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");
        // create category
        String catName = "Goods";
        generalActions.createCategory(catName);
        // check that new category appears in Categories table
        generalActions.checkNewCategory(catName);
        // finish script
        generalActions.finish();
    }
}
