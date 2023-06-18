package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.commands.Should;
import com.codeborne.selenide.commands.ShouldBe;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class AppCardDeliveryTest1 {
    String generateDate(int daysToAdd, String pattern) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void webInterfaceTest() {
        //Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Тюмень");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Александров Александр Александрович");
        $("[data-test-id='phone'] input").setValue("+72345678912");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        String planerDate = generateDate(5, "dd.MM.yyyy");
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planerDate), Duration.ofSeconds(15));



    }
}
