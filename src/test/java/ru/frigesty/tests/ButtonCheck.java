package ru.frigesty.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ButtonCheck extends TestBase {
    @BeforeEach
    void openSite(){
        open("https://jut.su/");
    }
    @CsvSource({
            "Вода, Техники воды",
            "Молния, Техники молнии",
            "Земля, Техники земли",
            "Ветер, Техники ветра",
            "Огонь, Техники огня",
            "Чакра, Техники простой чакры"
    })
    @DisplayName("При нажатии на кнопку {0} в header мы поподаем на страницу с надписью {1}")
    @ParameterizedTest
    @Tag("Buttons")
    public void JutSuButtonCheck(String link, String header){
        Configuration.holdBrowserOpen = true;
        $(".top_nav_b ul").$(byText(link)).click();
        $(".content h1").shouldHave(text(header));
    }
}
