import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MainPage {

    @FindBy(id = "new-todo")
    private WebElement newItemField;

    @FindBy(linkText = "Completed")
    private WebElement completed;

    @FindBy(id = "clear-completed")
    private WebElement clearCompleted;

    @FindBy(linkText = "All")
    private WebElement all;

    @FindBy(id = "toggle-all")
    private WebElement toggleAll;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createNewTask(String taskName) {
        newItemField.sendKeys(taskName);
        newItemField.sendKeys(Keys.RETURN);
    }

    public void clickOnCompleted() {
        completed.click();
    }

    public void clickOnClearCompleted() {
        clearCompleted.click();
    }

    public void clickOnAll() {
        all.click();
    }

    public void clickOnToggleAll() {
        toggleAll.click();
    }

    public List<WebElement> createTaskList(WebDriver driver) {
        List<WebElement> allLabelsList = driver.findElements(By.xpath("//label"));
        List<WebElement> taskList = allLabelsList.subList(1, allLabelsList.size());
        return taskList;
    }

    public void checkCurrentTaskList(List<WebElement> taskList, ArrayList<String> taskNames) {
        for (int i = 0; i < taskList.size(); i++) {
            Assert.assertTrue(taskList.get(i).getText().equals(taskNames.get(i)));
        }
    }
}
