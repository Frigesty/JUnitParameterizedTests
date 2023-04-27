package ru.frigesty.tests;

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

public class SwitchLocalTest {
    static Stream<Arguments> siteShouldContainAllButtonsAfterLanguageSelection() {
        return Stream.of(
                Arguments.of(Locale.ua, List.of("Серіали", "Фільми", "Новини", "Спільнота", "Рейтинги")),
                Arguments.of(Locale.ru, List.of("Сериалы", "Фильмы", "Новости", "Сообщество", "Рейтинги")),
                Arguments.of(Locale.en, List.of("Shows", "Movies", "News", "Community", "Ratings"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    @Tag("Language")
    void siteShouldContainAllButtonsAfterLanguageSelection(Locale Locale, List<String> buttons) {
        open("https://myshows.me/");
        $(".LangSwitcher-current").click();
        $$(".LangSwitcher-options .LangSwitcher-optionText").find(text(Locale.name())).click();
        $$(".Header-nav li a span").shouldHave(texts(buttons));
    }
}
