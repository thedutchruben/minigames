package dev.thedutchruben.minigamescore.modules.language;

import co.aurasphere.jyandex.Jyandex;
import co.aurasphere.jyandex.dto.Language;
import dev.thedutchruben.minigamescore.modules.language.commands.language.LanguageCommand;

import java.util.Arrays;
import java.util.List;

public class LanguageModule {
    private static List<String> languages = Arrays.asList(Language.DUTCH, Language.ENGLISH, Language.GERMAN, Language.FRENCH);
    private static Jyandex client = new Jyandex("trnsl.1.1.20190913T201832Z.81f193eb3978cac9.9e8e69d294c178a46a4201b6dc9e1a6dc21ab97b");

    public LanguageModule() {
        new LanguageCommand("language", "minigames.command.language");
    }

    public static String translate(String message, String language) {
        return client.translateText(message, language).getTranslatedText()[0];
    }

    public static List<String> getLanguages() {
        return languages;
    }
}
