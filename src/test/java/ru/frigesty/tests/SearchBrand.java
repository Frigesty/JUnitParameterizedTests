package ru.frigesty.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchBrand {

    @BeforeEach
    void openSite(){
        open("https://www.mvideo.ru/");
    }
    @CsvFileSource(resources = "/brandSearchMVideo.csv")
    @DisplayName("Отображение категории в поиске бренда")
    @ParameterizedTest(name = "Для бренда {0} отображается категория {1} на странице с результатом поиска")
    @Tag("Search")
    void brandSearchMVideo(String brandName, String categoryName){
        $("input.input__field").setValue(brandName).pressEnter();
        $(".filter-checkbox-list__container").shouldHave(text(categoryName));
    }
}
