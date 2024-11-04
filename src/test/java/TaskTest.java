import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TaskTest {

    public WebDriver acessar(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.navigate().to("http://localhost:8001/tasks");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }

    @Test
    public void deveSalvaTarefaComSucesso(){
        WebDriver webDriver = acessar();
        try {
            webDriver.findElement(By.id("addTodo")).click();
            webDriver.findElement(By.id("task")).sendKeys("123 test");
            webDriver.findElement(By.id("dueDate")).sendKeys("05/11/2024");
            webDriver.findElement(By.id("saveButton")).click();
            String message = webDriver.findElement(By.id("message")).getText();
            assertEquals("Sucess!", message);
        } finally {
            webDriver.quit();
        }
    }

    @Test
    public void naoDeveSalvaTarefaSemDescricao(){
        WebDriver webDriver = acessar();
        try {
            webDriver.findElement(By.id("addTodo")).click();
            webDriver.findElement(By.id("dueDate")).sendKeys("05/11/2024");
            webDriver.findElement(By.id("saveButton")).click();
            String message = webDriver.findElement(By.id("message")).getText();
            assertEquals("Fill the task description",message);
        }finally {
            webDriver.quit();
        }


    }

    @Test
    public void naoDeveSalvaTarefaSemData(){
        WebDriver webDriver = acessar();
        try {
            webDriver.findElement(By.id("addTodo")).click();
            webDriver.findElement(By.id("task")).sendKeys("123 test");
            webDriver.findElement(By.id("saveButton")).click();
            String message = webDriver.findElement(By.id("message")).getText();
            assertEquals("Fill the due date",message);
        }finally {
            webDriver.quit();
        }

    }

    @Test
    public void naoDeveSalvaTarefaComDataPassada(){
        WebDriver webDriver = acessar();
        try{
            webDriver.findElement(By.id("addTodo")).click();
            webDriver.findElement(By.id("task")).sendKeys("123 test");
            webDriver.findElement(By.id("dueDate")).sendKeys("05/11/2023");
            webDriver.findElement(By.id("saveButton")).click();
            String message = webDriver.findElement(By.id("message")).getText();
            assertEquals("Due date must not be in past",message);
        }finally {
            webDriver.quit();
        }

    }
}
