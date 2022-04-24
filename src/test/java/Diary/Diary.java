package Diary;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Diary {
    private WebDriver driver;
    private WebDriverWait wait;
    Logger logger = LoggerFactory.getLogger("Test");

    @BeforeEach
    @DisplayName("Устанавливаем драйвер")
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\НовыйЯ\\IdeaProjects\\PatternLesson10_11\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Тест-кейс проверки почты Diary")
    public void firstTest() {
        driver.get("https://diary.ru/");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbar_user-collapse\"]/ul[1]/li[1]"))).click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"login-form\"]/p"), "Пожалуйста заполните поля для авторизации:"));
        driver.findElement(By.id("loginform-username")).sendKeys("TyrloTV");
        driver.findElement(By.id("loginform-password")).sendKeys("kfTeh9rrcu");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title=\"reCAPTCHA\"]")));
        driver.findElement(By.xpath("//div[@class=\"recaptcha-checkbox-border\"]")).click();
        driver.switchTo().parentFrame();
        driver.findElement(By.id("login_btn")).click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"page_content\"]/div[1]/h2"), "Популярное"));
        driver.findElement(By.id("drop_right_menu")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbar_user-collapse\"]/ul[1]/li[10]/div/div/div[1]/a[2]"))).click();
        logger.info("Тест выполнен успешно!");
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}