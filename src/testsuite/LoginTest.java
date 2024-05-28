package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    String baseUrl="http://the-internet.herokuapp.com/login";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials(){
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        String expected="Secure Area";
        String actual=driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith1");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        String expected="Your username is invalid!\n×";
        String actual=driver.findElement(By.xpath("//div[@id='flash']")).getText();
        System.out.println(actual);
        Assert.assertEquals("error is not matching",expected,actual);
    }
    @Test
    public void verifyThePasswordErrorMessage(){
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        String expected="Your password is invalid!\n" + "×";
        String actual=driver.findElement(By.xpath("//div//div[@id='flash']")).getText();
        System.out.println(actual);
        Assert.assertEquals("error is not matching",expected,actual);
    }
    @After
    public void tearDown(){
        //closeBrowser();

    }
}
