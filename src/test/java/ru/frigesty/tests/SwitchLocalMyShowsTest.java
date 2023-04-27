package ru.frigesty.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.frigesty.tests.domain.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SwitchLocalMyShowsTest {
    static Stream<Arguments> siteShouldContainAllButtonsAfterLanguageSelection() {
        return Stream.of(
                Arguments.of(Locale.UA, List.of("Серіали", "Фільми", "Новини", "Спільнота", "Рейтинги")),
                Arguments.of(Locale.RU, List.of("Сериалы", "Фильмы", "Новости", "Сообщество", "Рейтинги")),
                Arguments.of(Locale.EN, List.of("Shows", "Movies", "News", "Community", "Ratings"))
        );
    }

    @BeforeEach
    void openSite(){
        open("https://myshows.me/");
    }

    @MethodSource
    @DisplayName("Смена языка и отображение кнопок")
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    @Tag("Language")
    void siteShouldContainAllButtonsAfterLanguageSelection(Locale Locale, List<String> buttons) {
        $(".LangSwitcher-current").click();
        $$(".LangSwitcher-options .LangSwitcher-optionText").find(text(Locale.name())).click();
        $$(".Header-nav li a span").shouldHave(texts(buttons));
    }
}