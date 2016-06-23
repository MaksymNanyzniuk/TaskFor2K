import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class TestTask {

    private WebDriver driver;

    @BeforeMethod
    public void openSite() {
        driver = new FirefoxDriver();
        driver.get("https://todomvc4tasj.herokuapp.com/");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }


    @Test
    public void test() {
        MainPage page = new MainPage(driver);
        Actions action = new Actions(driver);
        int x;
        String newTaskName;
        List<WebElement> taskList;

// 1. create task1
// 2. create task2
// 3. create task3
// 4. create task4
        ArrayList<String> taskNames = new ArrayList<String>();
        taskNames.add("Task1");
        taskNames.add("Task2");
        taskNames.add("Task3");
        taskNames.add("Task4");
        for (int i = 0; i < taskNames.size(); i++) {
            page.createNewTask(taskNames.get(i));
        }

        taskList = page.createTaskList(driver);
        Assert.assertTrue(taskList.size() == taskNames.size());
        page.checkCurrentTaskList(taskList, taskNames);

// 5. delete task2
        x = 1;
        taskNames.remove(x);
        WebElement btnToMove = taskList.get(x);
        action.moveToElement(btnToMove).perform();
        List<WebElement> buttonDeleteTaskList = driver.findElements(By.xpath("//button[@class='destroy']"));
        Assert.assertTrue(buttonDeleteTaskList.size() == taskList.size());

        buttonDeleteTaskList.get(x).click();
        taskList = page.createTaskList(driver);
        Assert.assertTrue(taskList.size() == 3);
        page.checkCurrentTaskList(taskList, taskNames);

// 6. rename task3 to task03
        x = 1;
        newTaskName = "Task03";
        taskNames.remove(x);
        taskNames.add(x, newTaskName);
        WebElement forDoubleClick = taskList.get(x);
        action.doubleClick(forDoubleClick).perform();
        List<WebElement> arList = driver.findElements(By.cssSelector("input.edit"));
        arList.get(x).clear();
        arList.get(x).sendKeys(newTaskName);
        arList.get(x).sendKeys(Keys.RETURN);

        taskList = page.createTaskList(driver);
        page.checkCurrentTaskList(taskList, taskNames);

// 7. mark task4 as completed
        arList = driver.findElements(By.cssSelector("input.toggle"));
        Assert.assertTrue(arList.size() == taskList.size());
        arList.get(arList.size() - 1).click();
        page.clickOnCompleted();

// 8. clear completed
        page.clickOnClearCompleted();

// 9. mark all as completed
        page.clickOnAll();
        taskNames.remove(taskNames.size() - 1);
        taskList = page.createTaskList(driver);
        page.checkCurrentTaskList(taskList, taskNames);

        page.clickOnToggleAll();
        page.clickOnCompleted();

// 10. clear completed
        page.clickOnClearCompleted();
        Assert.assertTrue(page.createTaskList(driver).size() == 0);
    }
}
