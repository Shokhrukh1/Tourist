package co.example.ui.languages;

import java.util.List;

import co.example.core.BaseView;
import co.example.model.Language;

/**
 * Created by Crish on 28.12.2017.
 */

public interface LanguagesView extends BaseView {
    void showLanguages(List<Language> languages);
    void showLanguageSaved();
}
