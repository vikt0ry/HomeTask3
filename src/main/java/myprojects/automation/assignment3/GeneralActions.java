package myprojects.automation.assignment3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     *
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.name("submitLogin")).click();
    }

    /**
     * Adds new category in Admin Panel.
     *
     * @param categoryName
     */
    public void createCategory(String categoryName) {
        openCategoryPage();
        waitForContentLoadElement("page-header-desc-category-new_category");
        openAddCategoryPage();
        waitForContentLoadElement("name_1");
        inputCategoryName(categoryName);
        saveNewCategory();
    }

    private void saveNewCategory() {
        driver.findElement(By.id("category_form_submit_btn")).click();
    }

    private void inputCategoryName(String categoryName) {
        driver.findElement(By.id("name_1")).sendKeys(categoryName);
    }

    private void openAddCategoryPage() {
        driver.findElement(By.id("page-header-desc-category-new_category")).click();
    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoadElement(String elementToWait) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementToWait)));
    }

    private void openCategoryPage() {
        waitForContentLoadElement("subtab-AdminCatalog");
        WebElement element = driver.findElement(By.id("subtab-AdminCatalog"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subtab-AdminCategories")));
        WebElement adminCategories = driver.findElement(By.id("subtab-AdminCategories"));
        actions.moveToElement(adminCategories).click().build().perform();
    }

    public void checkNewCategory(String catName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div .alert-success")));
        driver.findElement(By.name("categoryFilter_name")).sendKeys(catName);
        driver.findElement(By.id("submitFilterButtoncategory")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='table-category']")));
        driver.findElement(By.xpath("//table[@id ='table-category']//td[contains(text(), '" + catName + "')]"));
    }

    public void finish() {
//        driver.quit();


    }
}
